package com.gem.sistema.business.service.catalogos;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.domain.Poliza;
import com.gem.sistema.business.domain.TcRetencione;

// TODO: Auto-generated Javadoc
/**
 * The Interface PolizaService.
 */
public interface PolizaService {

	/**
	 * Método para realizar el conteo de poliza segun el tipo, mes y rango de
	 * conpol.
	 *
	 * @param tipopol   Tipo poliza
	 * @param mespol    Mes de polizas
	 * @param minConpol min contador de poliza
	 * @param maxConpol max contador de poliza
	 * @param idSector  the id sector
	 * @return cantidad de polizas en ese rango.
	 */
	Long countByTipopolMespolConpol(String tipopol, Integer mespol, Integer minConpol, Integer maxConpol,
			Integer idSector);

	/**
	 * Método para realizar el pdf de poliza segun el tipo, mes y rango de conpol.
	 *
	 * @param pathJasper ruta del archivo jasper.
	 * @param reportName nombre del pdf de reporte.
	 * @param pathLogo   the path logo
	 * @param tipopol    Tipo poliza
	 * @param mespol     Mes de polizas
	 * @param minConpol  min contador de poliza
	 * @param maxConpol  max contador de poliza
	 * @param idSector   the id sector
	 * @param user       the user
	 * @return the report by tipopol mespol conpol
	 */
	InputStream getReportByTipopolMespolConpol(final String pathJasper, final String reportName, final String pathLogo,
			String tipopol, Integer mespol, Integer minConpol, Integer maxConpol, int idSector, String user);

	/**
	 * Método para realizar el pdf de poliza segun el tipo, mes y rango de conpol.
	 *
	 * @param pathJasper ruta del archivo jasper.
	 * @param reportName nombre del pdf de reporte.
	 * @param pathLogo   the path logo
	 * @param tipopol    Tipo poliza
	 * @param mespol     Mes de polizas
	 * @param minConpol  min contador de poliza
	 * @param maxConpol  max contador de poliza
	 * @param idSector   the id sector
	 * @param user       the user
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	String savePDFFile(final String pathJasper, final String reportName, final String pathLogo, String tipopol,
			Integer mespol, Integer minConpol, Integer maxConpol, int idSector, String user) throws IOException;

	/**
	 * Copy poliza.
	 *
	 * @param poliza     the poliza
	 * @param polizaDest the poliza dest
	 * @param userId     the user id
	 * @param idSector   the id sector
	 * @return the poliza
	 */
	Poliza copyPoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector);

	/**
	 * Inversa poliza.
	 *
	 * @param poliza     the poliza
	 * @param polizaDest the poliza dest
	 * @param userId     the user id
	 * @param idSector   the id sector
	 * @return the poliza
	 */
	Poliza inversaPoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector);

	/**
	 * Negative poliza.
	 *
	 * @param poliza     the poliza
	 * @param polizaDest the poliza dest
	 * @param userId     the user id
	 * @param idSector   the id sector
	 * @return the poliza
	 */
	Poliza negativePoliza(Poliza poliza, Poliza polizaDest, String userId, int idSector);

	/**
	 * Delete.
	 *
	 * @param polien   the polien
	 * @param idSector the id sector
	 * @param idUser   the id user
	 * @return the poliza
	 */
	Poliza delete(Polien polien, int idSector, String idUser);

	/**
	 * Cierre mensul.
	 *
	 * @param mes      the mes
	 * @param idUser   the id user
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza cierreMensul(Integer mes, String idUser, int idSector);

	/**
	 * Desafecta poliza.
	 *
	 * @param mes      the mes
	 * @param tipo     the tipo
	 * @param numero   the numero
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza desafectaPoliza(Integer mes, String tipo, Integer numero, Integer idSector);

	/**
	 * Desafecta.
	 *
	 * @param mes      the mes
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza desafecta(Integer mes, Integer idSector);

	/**
	 * Desafectacion precierre.
	 *
	 * @param mes      the mes
	 * @param idSector the id sector
	 * @return the poliza
	 */
	Poliza desafectacionPrecierre(Integer mes, Integer idSector);

	/**
	 * Afectacion precierre.
	 *
	 * @param mes      the mes
	 * @param idSector the id sector
	 * @param idUser   the id user
	 * @return the poliza
	 */
	Poliza afectacionPrecierre(Integer mes, Integer idSector, String idUser);

	/**
	 * Gets the max polien conpol.
	 *
	 * @param tippol   the tippol
	 * @param mespol   the mespol
	 * @param idSector the id sector
	 * @return the max polien conpol
	 */
	Integer getMaxPolienConpol(String tippol, Integer mespol, Integer idSector);

	/**
	 * @param tcRetencione
	 * @return
	 */
	TcRetencione saveRetenciones(TcRetencione tcRetencione);

	/**
	 * @param tcRetencione
	 * @return
	 */
	List<TcRetencione> findRetenciones(TcRetencione tcRetencione);

	/**
	 * @param tcRetencione
	 * @return
	 */
	TcRetencione findOneRetenciones(TcRetencione tcRetencione);

	/**
	 * @param idSector
	 * @param tipo
	 * @param mes
	 * @param conpol
	 */
	void executeReoder(Integer idSector, String tipo, Integer mes, Integer conpol);

	/**
	 * @param tcRetencione
	 */
	void deleteRetenciones(TcRetencione tcRetencione);
}
