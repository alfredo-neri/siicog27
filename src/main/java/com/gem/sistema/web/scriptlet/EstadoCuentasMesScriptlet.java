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
 * The Class EstadoCuentasMesScriptlet.
 *
 * @author Ariel Landaida (landaida.1@gmail.com)
 */
public class EstadoCuentasMesScriptlet extends JRDefaultScriptlet
{
	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRDefaultScriptlet#afterGroupInit(java.lang.String)
	 */
	@Override
	public void afterGroupInit(String groupName) throws JRScriptletException
	{
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
			if(!Objects.isNull(value)){
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

}
