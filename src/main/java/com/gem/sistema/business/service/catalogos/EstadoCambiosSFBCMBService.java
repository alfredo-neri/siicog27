package com.gem.sistema.business.service.catalogos;

// TODO: Auto-generated Javadoc
/**
 * The Interface EstadoCambiosSFBCMBService.
 *
 * @author Dalia Tovar
 */
public interface EstadoCambiosSFBCMBService {

	/**
	 * Genera query activo.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	String generaQueryActivo(Integer idsector, Integer mesInicial, Integer mesFinal);

	/**
	 * Genera query pasivo.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	String generaQueryPasivo(Integer idsector, Integer mesInicial, Integer mesFinal);

	/**
	 * Genera query 3211.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	String generaQuery3211(Integer idsector, Integer mesInicial, Integer mesFinal);

	String generaQueryActivo(Integer idsector, Integer mesInicial, Integer mesFinal, Boolean mesAnteriorAcumulado);

	String generaQueryPasivo(Integer idsector, Integer mesInicial, Integer mesFinal, Boolean mesAnteriorAcumulado);
	
	String generaQueryHacienda(Integer idsector, Integer mesInicial, Integer mesFinal, Boolean mesAnteriorAcumulado);

	String generaQuery3211(Integer idsector, Integer mesInicial, Integer mesFinal, Boolean mesAnteriorAcumulado);
}
