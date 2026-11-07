package com.gem.sistema.business.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * VO para la conciliacion de polizas de Diario (D) contra Egreso (E) que
 * comparten la misma referencia (REFPOL) en cuentas de orden 82xx.
 *
 * Cada instancia representa UNA referencia del periodo, con la poliza D y la
 * poliza E colocadas lado a lado. Cuando alguna de las dos no existe en el mes
 * consultado se busca en el mes inmediato anterior; el mes en que se encontro
 * cada una se conserva en mesD y mesE, y el estatus lo distingue.
 *
 * cuentasD y cuentasE concentran las cuentas de orden 82xx de los movimientos de
 * cada poliza, tomadas de la poliza que realmente quedo seleccionada, sea del mes
 * consultado o del anterior.
 *
 * Existe una regla adicional: una poliza de Egreso sin poliza de Diario se
 * considera conciliada cuando sus movimientos registran las cuatro etapas
 * presupuestarias 822x, 824x, 825x y 827x, porque en ese caso el egreso recorre
 * el ciclo completo en un solo asiento y no requiere poliza de Diario. La 826x
 * queda deliberadamente fuera del conjunto.
 *
 * @author Alfredo Neri
 */
public class ConciliacionPolizaDEVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** La referencia tiene poliza de Diario y de Egreso, ambas en el mes consultado. */
	public static final String ESTATUS_CONCILIADA = "CONCILIADA";

	/** La referencia concilia, pero una de las polizas esta en el mes anterior. */
	public static final String ESTATUS_CONCILIADA_PREVIO = "CONCILIADA EN MES PREVIO";

	/** Existe la poliza de Diario pero no la de Egreso. */
	public static final String ESTATUS_SIN_EGRESO = "SIN EGRESO";

	/**
	 * La poliza de Egreso no tiene Diario, pero sus movimientos cubren las cuatro
	 * etapas presupuestarias, por lo que se considera conciliada.
	 */
	public static final String ESTATUS_CONCILIADA_MOVIMIENTOS = "CONCILIADA POR MOVIMIENTOS";

	/** Existe la poliza de Egreso pero no la de Diario. */
	public static final String ESTATUS_SIN_DIARIO = "SIN DIARIO";

	/**
	 * Prefijos de cuenta que, presentes TODOS en los movimientos de la poliza de
	 * Egreso, la dan por conciliada aun sin poliza de Diario.
	 *
	 * Corresponden a Presupuesto de Egresos por Ejercer (822x), Comprometido
	 * (824x), Devengado (825x) y Pagado (827x). La 826x (Ejercido) no forma parte
	 * de la regla.
	 */
	private static final String[] PREFIJOS_CICLO_PRESUPUESTAL = { "822", "824", "825", "827" };

	private Integer anopol;

	/** Mes consultado en la pantalla, contra el que se comparan mesD y mesE. */
	private Integer mespol;

	private String refpol;

	private Integer mesD;
	private String tipoD;
	private Integer conpolD;
	private Date fecpolD;

	private Integer mesE;
	private String tipoE;
	private Integer conpolE;
	private Date fecpolE;

	/** Numero de polizas D distintas que comparten la referencia en el mes de mesD. */
	private Integer numD;

	/** Numero de polizas E distintas que comparten la referencia en el mes de mesE. */
	private Integer numE;

	/** Cuentas de orden 82xx de los movimientos de la poliza de Diario. */
	private String cuentasD;

	/** Cuentas de orden 82xx de los movimientos de la poliza de Egreso. */
	private String cuentasE;

	public ConciliacionPolizaDEVO() {
	}

	/**
	 * Indica si la referencia tiene poliza de Diario.
	 *
	 * @return true cuando existe al menos una poliza D
	 */
	public boolean isTieneD() {
		return this.numD != null && this.numD > 0;
	}

	/**
	 * Indica si la referencia tiene poliza de Egreso.
	 *
	 * @return true cuando existe al menos una poliza E
	 */
	public boolean isTieneE() {
		return this.numE != null && this.numE > 0;
	}

	/**
	 * Indica si la conciliacion se logro con una poliza de un mes anterior al
	 * consultado.
	 *
	 * @return true cuando concilia y alguna de las dos polizas no es del mes
	 *         consultado
	 */
	public boolean isConciliadaEnMesPrevio() {
		if (!isTieneD() || !isTieneE() || this.mespol == null) {
			return false;
		}
		return (this.mesD != null && !this.mespol.equals(this.mesD))
				|| (this.mesE != null && !this.mespol.equals(this.mesE));
	}

	/**
	 * Indica si la poliza de Egreso se da por conciliada por si sola, sin poliza de
	 * Diario, porque sus movimientos registran las cuatro etapas presupuestarias.
	 *
	 * Solo aplica cuando existe poliza de Egreso y no existe la de Diario: una
	 * referencia que ya concilia por ambas polizas no necesita esta regla.
	 *
	 * @return true cuando la regla de las cuatro cuentas se cumple
	 */
	public boolean isConciliadaPorMovimientos() {
		if (isTieneD() || !isTieneE() || this.cuentasE == null) {
			return false;
		}
		for (String prefijo : PREFIJOS_CICLO_PRESUPUESTAL) {
			if (!contieneCuentaConPrefijo(this.cuentasE, prefijo)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Busca en la lista concentrada de cuentas alguna que inicie con el prefijo
	 * dado.
	 *
	 * La lista viene separada por comas desde la consulta, por lo que se evalua
	 * cuenta por cuenta y no como subcadena del texto completo: asi una cuenta como
	 * 1822 no se confunde con el prefijo 822.
	 *
	 * @param listaCuentas
	 *            cuentas separadas por coma
	 * @param prefijo
	 *            prefijo buscado
	 * @return true cuando alguna cuenta inicia con el prefijo
	 */
	private boolean contieneCuentaConPrefijo(String listaCuentas, String prefijo) {
		for (String cuenta : listaCuentas.split(",")) {
			if (cuenta.trim().startsWith(prefijo)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Estatus de conciliacion derivado de la presencia de cada tipo de poliza, del
	 * mes en que se encontro cada una y de la regla de las cuatro etapas
	 * presupuestarias.
	 *
	 * @return CONCILIADA, CONCILIADA EN MES PREVIO, CONCILIADA POR MOVIMIENTOS, SIN
	 *         EGRESO o SIN DIARIO
	 */
	public String getEstatus() {
		if (isTieneD() && isTieneE()) {
			return isConciliadaEnMesPrevio() ? ESTATUS_CONCILIADA_PREVIO : ESTATUS_CONCILIADA;
		}
		if (isConciliadaPorMovimientos()) {
			return ESTATUS_CONCILIADA_MOVIMIENTOS;
		}
		return isTieneD() ? ESTATUS_SIN_EGRESO : ESTATUS_SIN_DIARIO;
	}

	/**
	 * Indica si la referencia esta conciliada por cualquiera de las tres vias.
	 *
	 * @return true cuando no requiere seguimiento
	 */
	public boolean isConciliada() {
		return (isTieneD() && isTieneE()) || isConciliadaPorMovimientos();
	}

	/**
	 * Indica si la referencia esta repetida en mas de una poliza del mismo tipo
	 * dentro del mes en que se tomo.
	 *
	 * Cuando esto ocurre las columnas de consecutivo y fecha muestran una sola de
	 * ellas, por lo que el renglon requiere revision manual.
	 *
	 * @return true cuando hay mas de una poliza D o mas de una E
	 */
	public boolean isMultiple() {
		return (this.numD != null && this.numD > 1) || (this.numE != null && this.numE > 1);
	}

	/**
	 * Clase CSS del renglon segun su estatus, para resaltar en la vista.
	 *
	 * @return nombre de la clase de estilo
	 */
	public String getEstiloEstatus() {
		if (isMultiple()) {
			return "conciliacion-multiple";
		}
		if (isTieneD() && isTieneE()) {
			return isConciliadaEnMesPrevio() ? "conciliacion-previo" : "conciliacion-ok";
		}
		if (isConciliadaPorMovimientos()) {
			return "conciliacion-movimientos";
		}
		return "conciliacion-pendiente";
	}

	public Integer getAnopol() {
		return anopol;
	}

	public void setAnopol(Integer anopol) {
		this.anopol = anopol;
	}

	public Integer getMespol() {
		return mespol;
	}

	public void setMespol(Integer mespol) {
		this.mespol = mespol;
	}

	public String getRefpol() {
		return refpol;
	}

	public void setRefpol(String refpol) {
		this.refpol = refpol;
	}

	public Integer getMesD() {
		return mesD;
	}

	public void setMesD(Integer mesD) {
		this.mesD = mesD;
	}

	public String getTipoD() {
		return tipoD;
	}

	public void setTipoD(String tipoD) {
		this.tipoD = tipoD;
	}

	public Integer getConpolD() {
		return conpolD;
	}

	public void setConpolD(Integer conpolD) {
		this.conpolD = conpolD;
	}

	public Date getFecpolD() {
		return fecpolD;
	}

	public void setFecpolD(Date fecpolD) {
		this.fecpolD = fecpolD;
	}

	public Integer getMesE() {
		return mesE;
	}

	public void setMesE(Integer mesE) {
		this.mesE = mesE;
	}

	public String getTipoE() {
		return tipoE;
	}

	public void setTipoE(String tipoE) {
		this.tipoE = tipoE;
	}

	public Integer getConpolE() {
		return conpolE;
	}

	public void setConpolE(Integer conpolE) {
		this.conpolE = conpolE;
	}

	public Date getFecpolE() {
		return fecpolE;
	}

	public void setFecpolE(Date fecpolE) {
		this.fecpolE = fecpolE;
	}

	public Integer getNumD() {
		return numD;
	}

	public void setNumD(Integer numD) {
		this.numD = numD;
	}

	public Integer getNumE() {
		return numE;
	}

	public void setNumE(Integer numE) {
		this.numE = numE;
	}

	public String getCuentasD() {
		return cuentasD;
	}

	public void setCuentasD(String cuentasD) {
		this.cuentasD = cuentasD;
	}

	public String getCuentasE() {
		return cuentasE;
	}

	public void setCuentasE(String cuentasE) {
		this.cuentasE = cuentasE;
	}

}
