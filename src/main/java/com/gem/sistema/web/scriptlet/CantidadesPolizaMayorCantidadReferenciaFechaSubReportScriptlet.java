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
 * The Class CantidadesPolizaMayorCantidadReferenciaFechaSubReportScriptlet.
 *
 * @author Ariel Landaida (landaida.1@gmail.com)
 */
public class CantidadesPolizaMayorCantidadReferenciaFechaSubReportScriptlet extends JRDefaultScriptlet
{
	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRDefaultScriptlet#afterGroupInit(java.lang.String)
	 */
	@Override
	public void afterGroupInit(String groupName) throws JRScriptletException
	{
		BigDecimal saldoInicial = BigDecimal.ZERO;
		BigDecimal saldoFinal = BigDecimal.ZERO;

		if(groupName.equals("GRUPO_CUENTA") && getFieldValue("CUENTA") != null){
			saldoInicial = fieldValueBigDecimal("SALINI");
			for (int i = 1; i < (Integer)getParameterValue("P_MES"); i++) {
				if(getFieldValue("NATCTA").equals("D")){
					saldoInicial = saldoInicial.add(fieldValueBigDecimal("CARGOS_"+i)).subtract(fieldValueBigDecimal("ABONOS_"+i));
				}else{
					saldoInicial = saldoInicial.subtract(fieldValueBigDecimal("CARGOS_"+i)).add(fieldValueBigDecimal("ABONOS_"+i));
				}
			}
			BigDecimal deber = fieldValueBigDecimal("CARGOS_"+getParameterValue("P_MES"));
			BigDecimal haber = fieldValueBigDecimal("ABONOS_"+getParameterValue("P_MES"));
			
			if(saldoInicial.compareTo(BigDecimal.ZERO) != 0
				|| deber.compareTo(BigDecimal.ZERO) != 0
				|| haber.compareTo(BigDecimal.ZERO) != 0
				|| getFieldValue("MESPOL").equals(getParameterValue("P_MES"))
				)
			{
				if(getFieldValue("NATCTA").equals("D")){
					saldoFinal = saldoInicial.add(deber).subtract(haber);
				}else{
					saldoFinal = saldoInicial.subtract(deber).add(haber);
				}
			}
			
			BigDecimal cargo = BigDecimal.ZERO;
			BigDecimal abono = BigDecimal.ZERO;
			if(fieldValueBigDecimal("CANPOL").compareTo(BigDecimal.ZERO) > 0){
				cargo = fieldValueBigDecimal("CANPOL");
			}else{
				abono = fieldValueBigDecimal("CANPOLH");
			}
			setVariableValue("debe", cargo);
			setVariableValue("haber", abono);


			//making summaries
			setVariableValue("total_saldo_inicial", variableValueBigDecimal("total_saldo_inicial").add(saldoInicial));
			setVariableValue("total_deber", variableValueBigDecimal("total_deber").add(deber));
			setVariableValue("total_haber", variableValueBigDecimal("total_haber").add(haber));
			setVariableValue("total_saldo_final", saldoFinal);
			
		}
	}
	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRDefaultScriptlet#afterDetailEval()
	 */
	@Override
	public void afterDetailEval() throws JRScriptletException
	{
	}

	/**
	 * Variable value big decimal.
	 *
	 * @param variableName the variable name
	 * @return the big decimal
	 */
	private BigDecimal variableValueBigDecimal(String variableName){
		BigDecimal retorno = BigDecimal.ZERO;
		try {
			Object value = getVariableValue(variableName);
			if(!Objects.isNull(value)){
				retorno = (BigDecimal)value;
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
	private BigDecimal fieldValueBigDecimal(String fieldName){
		BigDecimal retorno = BigDecimal.ZERO;
		try {
			Object value = getFieldValue(fieldName);
			if(!Objects.isNull(value) && value.getClass().equals(BigDecimal.class)){
				retorno = (BigDecimal)value;
			}
		} catch (JRScriptletException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	/**
	 * Field value integer.
	 *
	 * @param fieldName the field name
	 * @return the integer
	 */
	private Integer fieldValueInteger(String fieldName){
		Integer retorno = 0;
		try {
			Object value = getFieldValue(fieldName);
			if(!Objects.isNull(value)){
				retorno = Integer.valueOf((String) value);
			}
		} catch (JRScriptletException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRAbstractScriptlet#getFieldValue(java.lang.String)
	 */
	@Override
	public Object getFieldValue(String fieldName) throws JRScriptletException {
		Object retorno = super.getFieldValue(fieldName);
		return Objects.isNull(retorno) ? new Object() : retorno;
	}

}
