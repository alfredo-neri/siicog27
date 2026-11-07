package com.gem.sistema.business.service.catalogos;

import static com.gem.sistema.util.CopyFileUtil.copyFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.TcTabularSueldosTrimestral;
import com.gem.sistema.business.repository.catalogs.TcTabuladorSueldosTrimestralRepository;

@Service(value = "tcTabuladorSueldosTrimestralService")
public class TcTabuladorSueldosTrimestralServiceImpl implements TcTabuladorSueldosTrimestralService {

	private static final String PATH = "/gem/upfiles";

	@Autowired
	private TcTabuladorSueldosTrimestralRepository tabuladorRepositoy;

	@Override
	public void saveFile(String fileName, InputStream input) {
		try {
			copyFile(fileName, input, PATH);
			File doc = new File(PATH + "/" + fileName);
			tabuladorRepositoy.deleteAll();
			String strng;
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(doc))) {
				while ((strng = bufferedReader.readLine()) != null) {
					String[] array = strng.split(",");
					TcTabularSueldosTrimestral tab = new TcTabularSueldosTrimestral();
					tab.setPuestoFuncional(array[0]);
					tab.setNivelSalarial(array[1]);
					tab.setNoPlaza(Integer.valueOf(array[2]));
					tab.setCategoriaConfianza(Integer.valueOf(array[3]));
					tab.setCategoriaSindicalizado(Integer.valueOf(array[4]));
					tab.setCategoriaEventual(Integer.valueOf(array[5]));
					tab.setDietas(new BigDecimal(array[6]));
					tab.setHaberes(new BigDecimal(array[7]));
					tab.setSueldoBase(new BigDecimal(array[8]));
					tab.setSueldoMagisterio(new BigDecimal(array[9]));
					tab.setHorasClase(new BigDecimal(array[10]));
					tab.setCarreraMagisterial(new BigDecimal(array[11]));
					tab.setCarreraDocente(new BigDecimal(array[12]));
					tab.setLaboralExtranjero(new BigDecimal(array[13]));
					tab.setHonorarios(new BigDecimal(array[14]));
					tab.setInterinato(new BigDecimal(array[15]));
					tab.setPersonalEventual(new BigDecimal(array[16]));
					tab.setMedicosResidentes(new BigDecimal(array[17]));
					tab.setServicioSocial(new BigDecimal(array[18]));
					tab.setRepresentante(new BigDecimal(array[19]));
					tab.setAniosServicio(new BigDecimal(array[20]));
					tab.setAntiguedad(new BigDecimal(array[21]));
					tab.setPermanencia(new BigDecimal(array[22]));
					tab.setPrimaVacacional(new BigDecimal(array[23]));
					tab.setAguinaldo(new BigDecimal(array[24]));
					tab.setAguinaldoEventual(new BigDecimal(array[25]));
					tab.setVacacionesFiniquito(new BigDecimal(array[26]));
					tab.setPrimaDominical(new BigDecimal(array[27]));
					tab.setHorasExtraordinarias(new BigDecimal(array[28]));
					tab.setCompensacion(new BigDecimal(array[29]));
					tab.setServiciosEspeciales(new BigDecimal(array[30]));
					tab.setRiesgoProfesional(new BigDecimal(array[31]));
					tab.setRetabulacion(new BigDecimal(array[32]));
					tab.setGratificacion(new BigDecimal(array[33]));
					tab.setGratificacionConvenio(new BigDecimal(array[34]));
					tab.setGratificacionProductiva(new BigDecimal(array[35]));
					tab.setLaboresDocente(new BigDecimal(array[36]));
					tab.setEstudiosSuperiores(new BigDecimal(array[37]));
					tab.setSobrehaberes(new BigDecimal(array[38]));
					tab.setTecnicoEspecial(new BigDecimal(array[39]));
					tab.setHorariosEspeciales(new BigDecimal(array[40]));
					tab.setCustodiaValores(new BigDecimal(array[41]));
					tab.setIsste(new BigDecimal(array[42]));
					tab.setServicioSalud(new BigDecimal(array[43]));
					tab.setSolidarioReparto(new BigDecimal(array[44]));
					tab.setCapitalizacionIndividual(new BigDecimal(array[45]));
					tab.setIssemym(new BigDecimal(array[46]));
					tab.setRiesgoTrabajo(new BigDecimal(array[47]));
					tab.setVejez(new BigDecimal(array[48]));
					tab.setFovissste(new BigDecimal(array[49]));
					tab.setAhorroRetiro(new BigDecimal(array[50]));
					tab.setSegurosFianzas(new BigDecimal(array[51]));
					tab.setFondoRetiro(new BigDecimal(array[52]));
					tab.setSeparacionIndividual(new BigDecimal(array[53]));
					tab.setAccidenteTrabajo(new BigDecimal(array[54]));
					tab.setSueldoSalarioCaidos(new BigDecimal(array[55]));
					tab.setJubilacion(new BigDecimal(array[56]));
					tab.setTrabajadoresSindicalizados(new BigDecimal(array[57]));
					tab.setCivicosEconomicos(new BigDecimal(array[58]));
					tab.setMagisterio(new BigDecimal(array[59]));
					tab.setServidorPublico(new BigDecimal(array[60]));
					tab.setEstudiosPostgrado(new BigDecimal(array[61]));
					tab.setDerivadoConvenio(new BigDecimal(array[62]));
					tab.setSidicalizados(new BigDecimal(array[63]));
					tab.setBecas(new BigDecimal(array[64]));
					tab.setProfesionalizacion(new BigDecimal(array[65]));
					tab.setTesis(new BigDecimal(array[66]));
					tab.setSeguroVida(new BigDecimal(array[67]));
					tab.setViaticos(new BigDecimal(array[68]));
					tab.setDiferenciaEscuelas(new BigDecimal(array[69]));
					tab.setDespensa(new BigDecimal(array[70]));
					tab.setSeguridadSocial(new BigDecimal(array[71]));
					tab.setReconocimiento(new BigDecimal(array[72]));
					tab.setPuntualidad(new BigDecimal(array[73]));
					tab.setRecompensas(new BigDecimal(array[74]));
					tab.setTotal(new BigDecimal(array[75]));
					tabuladorRepositoy.save(tab);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<TcTabularSueldosTrimestral> findAll() {
		// TODO Auto-generated method stub
		return (List<TcTabularSueldosTrimestral>) tabuladorRepositoy.findAll();
	}

	@Override
	public void saveObj(TcTabularSueldosTrimestral tabulador) {
		tabuladorRepositoy.save(tabulador);

	}

	@Override
	public void delteAll() {
		tabuladorRepositoy.deleteAll();

	}

	@Override
	public void update(TcTabularSueldosTrimestral tabulador) {
		tabuladorRepositoy.save(tabulador);

	}

	@Override
	public void deleteById(Long id) {
		this.tabuladorRepositoy.delete(id);
		
	}

}
