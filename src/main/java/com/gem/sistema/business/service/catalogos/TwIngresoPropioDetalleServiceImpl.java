package com.gem.sistema.business.service.catalogos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.TcIngresosPropio;
import com.gem.sistema.business.domain.TwIngresoPropiosDetalle;
import com.gem.sistema.business.dto.IngresoPropioDTO;
import com.gem.sistema.business.dto.IngresosPropiosDetalleDTO;
import com.gem.sistema.business.dto.IngresosPropiosPolizaDTO;
import com.gem.sistema.business.repository.catalogs.TcIngresoPropioRepository;
import com.gem.sistema.business.repository.catalogs.TwIngresoPropioDetalleRepository;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;
import com.gem.sistemas.row.mapper.IngresosPropiosDetalleRowMapper;
import com.gem.sistemas.row.mapper.IngresosPropiosPolizaRowMapper;

@Service(value = "twIngresoPropioDetalleService")
public class TwIngresoPropioDetalleServiceImpl implements TwIngresoPropioDetalleService {

	private static final String DEFAULT_PATH_OUTVALID = "/gem/ingresos/";

	@Autowired
	private EntityManager em;

	@Autowired
	@Qualifier("twIngresosPropioDetalleRepository")
	private TwIngresoPropioDetalleRepository detalleRepository;

	@Autowired
	@Qualifier("tcIngresosPropioRepository")
	private TcIngresoPropioRepository catRepository;

	@Autowired
	@Qualifier("ingresosPropiosValidator")
	FileContentValidator ingresosPropiosrValidator;

	@Autowired
	@Qualifier("parametroServiceImpl")
	private ParametroService parametroService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private List<TcIngresosPropio> listIngresos;

	@Override
	public IngresoPropioDTO cargaIngresoPropio(File newFile, Polide polide, BigDecimal totalIngresos) {
		IngresoPropioDTO salida = new IngresoPropioDTO();
		salida.setCodError(0);
		final FileUpload fileUpload = new FileUpload();
		fileUpload.setFile(newFile);
		fileUpload.setNameOriginal(newFile.getName());
		fileUpload.setNameReal(newFile.getName());
		fileUpload.setErrorPath(DEFAULT_PATH_OUTVALID);
		fileUpload.setOutPutPath(DEFAULT_PATH_OUTVALID);
		try {
			if (this.ingresosPropiosrValidator.isValid(fileUpload)) {
				salida.setCodError(1);
				salida = this.cargaArchivo(newFile, polide, salida, totalIngresos);
			} else {
				salida.addMsn("El archivo contine errores de formato, Descargar archivo de errores ");
				salida.setCodError(2);
			}
		} catch (Exception e) {
			salida.setCodError(0);
			salida.addMsn("Ha ocurrido un error valida el tipo de archivo");
			e.printStackTrace();
		}
		return salida;
	}

	@Override
	public List<TwIngresoPropiosDetalle> getByPolide(Polide polide) {
		return detalleRepository.getAllByMesAndTipoAndConpolAndRenpol(polide.getMespol(), polide.getTippol(),
				polide.getConpol(), polide.getRenpol());
	}

	private IngresoPropioDTO cargaArchivo(File file, Polide polide, IngresoPropioDTO salida, BigDecimal totalIngresos) {
		cargaCatalogoIngresos();
		BufferedReader br = null;
		String line;
		String tokens[];
		List<TwIngresoPropiosDetalle> detalle = new ArrayList<TwIngresoPropiosDetalle>();
		List<TcIngresosPropio> ingreso = null;
		List<Integer> errors = new ArrayList<Integer>();
		Integer ind = 1;
		BigDecimal total = new BigDecimal(0);
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			while ((line = br.readLine()) != null) {
				tokens = line.split(",");
				String clave = tokens[0];
				ingreso = this.listIngresos.stream().filter(i -> i.getClave().equals(Integer.valueOf(clave)))
						.collect(Collectors.toList());
				Long idIngreso = CollectionUtils.isNotEmpty(ingreso) ? ingreso.get(0).getId() : null;
				total = total.add(new BigDecimal(tokens[1]));
				if (idIngreso == null) {
					errors.add(ind);
				} else {
					detalle.add(new TwIngresoPropiosDetalle(idIngreso, polide, new BigDecimal(tokens[1])));
				}
				ind++;
			}
		} catch (Exception e) {
			salida.setCodError(0);
			salida.addMsn("Ha ocurrido un error valida el tipo de archivo");
			e.printStackTrace();
		}
		salida.setTotal(total);
		if (!errors.isEmpty()) {
			salida.setCodError(0);
			salida.addMsn("No se encontro \"CLAVE DE INGRESO\" para los renglon(s): "
					+ errors.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(" ,")));
		}
		if (totalIngresos.compareTo(salida.getTotal()) != 0) {
			salida.setCodError(0);
			salida.addMsn("La suma del archivo no coincide con el total de ingreso ");
		}
		if (salida.getCodError() == 1) {
			eliminaIngresoByPolide(polide);
			List<TwIngresoPropiosDetalle> aCargar = detalle.stream()
					.filter(in -> in.getMonto().compareTo(new BigDecimal(0)) != 0).collect(Collectors.toList());
			detalleRepository.save(aCargar);
			aCargar = aCargar.stream().map(in -> {
				in.setIdIngreso(Long.valueOf(listIngresos.stream().filter(d -> d.getId() == in.getIdIngreso())
						.collect(Collectors.toList()).get(0).getClave()));
				return in;
			}).collect(Collectors.toList());

			salida.setCargados(aCargar);
			salida.addMsn("Se cargo correctamente la información ");
			salida.setCodError(1);
		}
		return salida;

	}

	@Override
	public void eliminaIngresoByPolide(Polide polide) {
		List<TwIngresoPropiosDetalle> entitys = this.getByPolide(polide);
		detalleRepository.delete(entitys);
	}

	private void cargaCatalogoIngresos() {
		listIngresos = catRepository.findAll();
	}

	@Override
	public List<IngresosPropiosPolizaDTO> getPolizasWithTotalIngreso(String mes, Integer anopol, Integer idSector) {
		String query = "SELECT P.TIPPOL,P.CONPOL,P.MESPOL,	MAX(DECODE(P.SSSCTA,'0001',P.RENPOL,0)) RENPOL, "
				+ "	P.CUENTA,P.SCTA,P.SSCTA,'0001' SSSCTA, " + "	SUM(P.CANPOLH) INGRESOS, "
				+ "	SUM(DECODE(P.SSSCTA,'0001',	P.CANPOLH,0)) CANPOLH " + "FROM POLIDE P  " + "	WHERE P.MESPOL= " + mes
				+ " AND P.TIPPOL='I' AND P.ANOPOL = " + anopol + " AND P.IDSECTOR = " + idSector + " AND "
				+ "		P.CUENTA='4223' AND	P.SCTA='000000000000001' AND	P.SSCTA='000000000000000004' AND "
				+ "		P.SSSCTA IN('0001','0002') AND P.SSSSCTA=''  "
				+ "	GROUP BY P.TIPPOL,P.CONPOL,	P.MESPOL,P.CUENTA,P.SCTA,P.SSCTA " + "	ORDER BY P.CONPOL ";
		return jdbcTemplate.query(query, new IngresosPropiosPolizaRowMapper());
	}

	@Override
	public List<IngresosPropiosDetalleDTO> getIngresosWithNombre(IngresosPropiosPolizaDTO polide) {
		String query = "SELECT C.CLAVE,C.NOMBRE, I.MONTO FROM "
				+ "	TC_INGRESOS_PROPIOS C ,TW_INGRESO_PROPIOS_DETALLE I " + "WHERE I.MESPOL=" + polide.getMespol()
				+ " AND I.CONPOL=" + polide.getConpol() + " AND I.TIPPOL='" + polide.getTippol() + "' AND	RENPOL="
				+ polide.getRenpol() + " AND  " + "	I.ID_INGRESO=C.ID ";
		return jdbcTemplate.query(query, new IngresosPropiosDetalleRowMapper());
	}

	@Override
	public Double getTotalIngresoByMes(Integer mes) {
		return detalleRepository.getTotalByMes(mes);
	}

	@Override
	public Double getTotalCuentaByMes(Integer mes, Integer sector) {
		String query = "SELECT SUM(C.ABONOS_" + mes + ") FROM Cuenta C  WHERE C.cuenta='4223' AND  "
				+ "	C.scta='000000000000001' AND  " + "	C.SSCTA='000000000000000004' AND  C.SSSCTA IN('0001','0002') AND  "
				+ "	C.sssscta='' AND  C.idSector=" + sector +" GROUP BY CUENTA";
		Double salida=jdbcTemplate.queryForObject(query, Double.class);
		return salida;
	}

}
