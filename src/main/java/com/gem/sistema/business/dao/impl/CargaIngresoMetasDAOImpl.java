package com.gem.sistema.business.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.CargaIngresoMetasDAO;
import com.gem.sistema.business.domain.TcIngresosPropio;
import com.gem.sistema.business.domain.TwMetasIngreso;
import com.gem.sistema.business.predicates.TcIngresosPropiosPredicate;
import com.gem.sistema.business.repository.catalogs.TcIngresoPropioRepository;
import com.gem.sistema.business.repository.catalogs.TwMetasIngresoRepository;

@Repository
public class CargaIngresoMetasDAOImpl implements CargaIngresoMetasDAO {

	private static final String PATH_FILE = "/gem/errores/";
	private static final Sort SORT_ORDER = new Sort(Sort.Direction.ASC, "tcIngresosPropio");

	@Autowired
	private TwMetasIngresoRepository twMetasIngresoRepository;

	@Autowired
	private TcIngresoPropioRepository ingresosPropiosRepository;

	private TcIngresosPropio ingresosPropios;

	@Override
	public List<TwMetasIngreso> cargaMetas(String fileName) {
		this.twMetasIngresoRepository.deleteAll();
		readFile(fileName);
		List<TwMetasIngreso> listMetsas = (List<TwMetasIngreso>) this.twMetasIngresoRepository.findAll(SORT_ORDER);
		if (CollectionUtils.isEmpty(listMetsas))
			return null;
		return listMetsas;
	}

	private void readFile(String file) {
		BufferedReader bf = null;
		TwMetasIngreso twMetasIngreso = null;
		try {
			bf = new BufferedReader(new FileReader(file));
			String sCadena = StringUtils.EMPTY;

			while ((sCadena = bf.readLine()) != null) {
				String data[] = sCadena.split(",");
				twMetasIngreso = new TwMetasIngreso();

				TcIngresosPropio ingresosPropios = this.ingresosPropiosRepository
						.findOne(TcIngresosPropiosPredicate.findByClave(Integer.valueOf(data[0])));
				if (null != ingresosPropios) {
					twMetasIngreso.setTcIngresosPropio(ingresosPropios);

					twMetasIngreso.setAutorizado1(convertToDecimal(data[2]));
					twMetasIngreso.setAutorizado2(convertToDecimal(data[3]));
					twMetasIngreso.setAutorizado3(convertToDecimal(data[4]));
					twMetasIngreso.setAutorizado4(convertToDecimal(data[5]));
					twMetasIngreso.setAutorizado5(convertToDecimal(data[6]));
					twMetasIngreso.setAutorizado6(convertToDecimal(data[7]));
					twMetasIngreso.setAutorizado7(convertToDecimal(data[8]));
					twMetasIngreso.setAutorizado8(convertToDecimal(data[9]));
					twMetasIngreso.setAutorizado9(convertToDecimal(data[10]));
					twMetasIngreso.setAutorizado10(convertToDecimal(data[11]));
					twMetasIngreso.setAutorizado11(convertToDecimal(data[12]));
					twMetasIngreso.setAutorizado12(convertToDecimal(data[13]));
					this.twMetasIngresoRepository.save(twMetasIngreso);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private BigDecimal convertToDecimal(String cad) {
		return new BigDecimal(new Double(cad));
	}

	@Override
	public List<TwMetasIngreso> consultaMetas() {

		return (List<TwMetasIngreso>) this.twMetasIngresoRepository.findAll();

	}

	public TwMetasIngresoRepository getTwMetasIngresoRepository() {
		return twMetasIngresoRepository;
	}

	public void setTwMetasIngresoRepository(TwMetasIngresoRepository twMetasIngresoRepository) {
		this.twMetasIngresoRepository = twMetasIngresoRepository;
	}

	public TcIngresoPropioRepository getIngresosPropiosRepository() {
		return ingresosPropiosRepository;
	}

	public void setIngresosPropiosRepository(TcIngresoPropioRepository ingresosPropiosRepository) {
		this.ingresosPropiosRepository = ingresosPropiosRepository;
	}

	@Override
	public String validaIngresoPropios(String fileName) {

		BufferedReader bf = null;
		String pathFull = StringUtils.EMPTY;
		List<Integer> list = new ArrayList<Integer>();

		try {
			bf = new BufferedReader(new FileReader(fileName));
			String sCadena = StringUtils.EMPTY;

			while ((sCadena = bf.readLine()) != null) {
				String data[] = sCadena.split(",");

				ingresosPropios = this.ingresosPropiosRepository
						.findOne(TcIngresosPropiosPredicate.findByClave(Integer.valueOf(data[0])));
				if (null == ingresosPropios)
					list.add(Integer.valueOf(data[0]));

			}
			if (CollectionUtils.isNotEmpty(list))
				pathFull = generaArchivErrores(list);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return pathFull;
	}

	private String generaArchivErrores(List<Integer> listId) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		String fulPath = PATH_FILE + UUID.randomUUID() + "" + "errores.csv";
		try {
			fichero = new FileWriter(fulPath);
			pw = new PrintWriter(fichero);

			for (Integer id : listId)
				pw.println(" Clave ===> " + id + " No se encuentra en la base de datos");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return fulPath;
	}

}
