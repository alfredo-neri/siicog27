package com.gem.sistema.web.scriptlet;

import java.math.BigDecimal;
import java.util.Objects;

/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2016 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoAvancePresupuestalEgresosFinalidadFuncionSubFuncionScriptlet.
 *
 * @author Ariel Landaida (landaida.1@gmail.com)
 */
public class EstadoAvancePresupuestalEgresosFinalidadFuncionSubFuncionScriptlet extends JRDefaultScriptlet {
	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRDefaultScriptlet#afterGroupInit(java.lang.String)
	 */
	@Override
	public void afterGroupInit(String groupName) throws JRScriptletException {
		if (groupName.equals("GROUP_PARTIDA") && getFieldValue("PARTIDA") != null) {
			BigDecimal aprobado = BigDecimal.ZERO;
			BigDecimal padre_aprobado = BigDecimal.ZERO;
			BigDecimal pagado = BigDecimal.ZERO;
			BigDecimal devengado = BigDecimal.ZERO;
			BigDecimal ejercido = BigDecimal.ZERO;
			BigDecimal ampliacion = BigDecimal.ZERO;
			BigDecimal reduccion = BigDecimal.ZERO;
			BigDecimal comprometido = BigDecimal.ZERO;
			BigDecimal porejercer = BigDecimal.ZERO;
			BigDecimal modificado = BigDecimal.ZERO;

			BigDecimal total_pagado = BigDecimal.ZERO;
			BigDecimal total_devengado = BigDecimal.ZERO;
			BigDecimal total_ejercido = BigDecimal.ZERO;
			BigDecimal total_ampliacion = BigDecimal.ZERO;
			BigDecimal total_reduccion = BigDecimal.ZERO;
			BigDecimal total_comprometido = BigDecimal.ZERO;
			BigDecimal total_porejercer = BigDecimal.ZERO;
			BigDecimal total_modificado = BigDecimal.ZERO;

			for (int i = 1; i <= 12; i++) {
				aprobado = aprobado.add(fieldValueBigDecimal("AUTO1_" + i));
				if (getFieldValue("PARTIDA").toString().substring(1, 4).equals("000")) {
					padre_aprobado = padre_aprobado.add(fieldValueBigDecimal("AUTO1_" + i));
					total_pagado = total_pagado.add(fieldValueBigDecimal("EJPA1_" + i));
					total_devengado = total_devengado.add(fieldValueBigDecimal("EJXPA1_" + i));
					total_ejercido = total_ejercido.add(fieldValueBigDecimal("TOEJE1_" + i));
					total_ampliacion = total_ampliacion.add(fieldValueBigDecimal("AMPLI1_" + i));
					total_reduccion = total_reduccion.add(fieldValueBigDecimal("REDU1_" + i));
					total_comprometido = total_comprometido.add(fieldValueBigDecimal("COMPRO1_" + i));

				}
				if (i <= (Integer) getParameterValue("P_MES")) {
					pagado = pagado.add(fieldValueBigDecimal("EJPA1_" + i));
					devengado = devengado.add(fieldValueBigDecimal("EJXPA1_" + i));
					ejercido = ejercido.add(fieldValueBigDecimal("TOEJE1_" + i));
					ampliacion = ampliacion.add(fieldValueBigDecimal("AMPLI1_" + i));
					reduccion = reduccion.add(fieldValueBigDecimal("REDU1_" + i));
					comprometido = comprometido.add(fieldValueBigDecimal("COMPRO1_" + i));
				}
			}
			modificado = (aprobado.add(ampliacion).subtract(reduccion));
			porejercer = (aprobado.add(ampliacion).subtract(reduccion)).subtract(ejercido);
			
			if (getFieldValue("PARTIDA").toString().substring(1, 4).equals("000")) {
				total_porejercer = total_porejercer.add(porejercer);
				total_modificado = total_modificado.add(modificado);
			}

			// making group fields
			setVariableValue("aprobado", aprobado);
			setVariableValue("pagado", pagado);
			setVariableValue("devengado", devengado);
			setVariableValue("ejercido", ejercido);
			setVariableValue("ampliacion", ampliacion);
			setVariableValue("reduccion", reduccion);
			setVariableValue("comprometido", comprometido);
			setVariableValue("porejercer", porejercer);
			setVariableValue("modificado", modificado);

			// making summaries
			setVariableValue("total_aprobado", variableValueBigDecimal("total_aprobado").add(padre_aprobado));
			setVariableValue("total_pagado", variableValueBigDecimal("total_pagado").add(total_pagado));
			setVariableValue("total_devengado", variableValueBigDecimal("total_devengado").add(total_devengado));
			setVariableValue("total_ejercido", variableValueBigDecimal("total_ejercido").add(total_ejercido));
			setVariableValue("total_ampliacion", variableValueBigDecimal("total_ampliacion").add(total_ampliacion));
			setVariableValue("total_reduccion", variableValueBigDecimal("total_reduccion").add(total_reduccion));
			setVariableValue("total_comprometido",
					variableValueBigDecimal("total_comprometido").add(total_comprometido));
			setVariableValue("total_porejercer", variableValueBigDecimal("total_porejercer").add(total_porejercer));
			setVariableValue("total_modificado", variableValueBigDecimal("total_modificado").add(total_modificado));
		}
	}

	/**
	 * Variable value big decimal.
	 *
	 * @param variableName the variable name
	 * @return the big decimal
	 */
	private BigDecimal variableValueBigDecimal(String variableName) {
		BigDecimal retorno = BigDecimal.ZERO;
		try {
			Object value = getVariableValue(variableName);
			if (!Objects.isNull(value)) {
				retorno = (BigDecimal) value;
			}
		} catch (JRScriptletException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * Field value big decimal.
	 *
	 * @param fieldName the field name
	 * @return the big decimal
	 */
	private BigDecimal fieldValueBigDecimal(String fieldName) {
		BigDecimal retorno = BigDecimal.ZERO;
		try {
			Object value = getFieldValue(fieldName);
			if (!Objects.isNull(value)) {
				retorno = (BigDecimal) value;
			}
		} catch (JRScriptletException e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
