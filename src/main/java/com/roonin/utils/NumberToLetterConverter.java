/**
 * NumberToLetterConverter.java is part of Roonin Utils Project.
 *
 * Copyright 2011,2012,2013,2014,2015,2016 Roonin Labs S. de R.L. de C.V.
 *
 * You may distribute under the terms of either the GNU General Public
 * License or the Artistic License, as specified in the README file.
 *
 * @author Roonin Labs
 * @see <a href="https://github.com/Roonin-mx/Roonin-mx-PortletsDI/blob/master/ContractsPortlet/ContractsPortlet-portlet/src/main/java/com/roonin/utils/NumberToLetterConverter.java">Roonin NumberToLetterConverter</a>
*/

package com.roonin.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.apache.log4j.Logger;


// TODO: Auto-generated Javadoc
/**
 * The Class NumberToLetterConverter.
 */
public class NumberToLetterConverter {

  /** The log. */
  private static Logger _log = Logger.getLogger(NumberToLetterConverter.class);

  /** The Constant UNIDADES. */
  private static final String[] UNIDADES = { "", "UN ", "DOS ", "TRES ",
        "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
        "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS ",
        "DIECISIETE ", "DIECIOCHO ", "DIECINUEVE ", "VEINTE " };

  /** The Constant DECENAS. */
  private static final String[] DECENAS = { "VENTI", "TREINTA ", "CUARENTA ",
        "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ",
        "CIEN " };

  /** The Constant CENTENAS. */
  private static final String[] CENTENAS = { "CIENTO ", "DOSCIENTOS ",
        "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
        "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS " };

  /**
   * Convierte a letras un numero de la forma $123,456.32
   *
   * @param number            Numero en representacion texto
   * @return Numero en letras
   * @throws NumberFormatException             Si valor del numero no es valido (fuera de rango o )
   */
  public static String convertNumberToLetter(String number)
  throws NumberFormatException {
    return convertNumberToLetter(Double.parseDouble(number));
  }

  /**
   * Convierte un numero en representacion numerica a uno en representacion de
   * texto. El numero es valido si esta entre 0 y 999'999.999
   *
   * @param doubleNumber the double number
   * @return Numero convertido a texto
   * @throws NumberFormatException             Si el numero esta fuera del rango
   */
  public static String convertNumberToLetter(double doubleNumber)
  throws NumberFormatException {
    _log.debug("converting number -> "+doubleNumber);

    StringBuilder converted = new StringBuilder();

    String patternThreeDecimalPoints = "#.00";

    DecimalFormat format = new DecimalFormat(patternThreeDecimalPoints,
    new DecimalFormatSymbols(){{
      setDecimalSeparator('.');
      setGroupingSeparator(',');
    }});

    format.setRoundingMode(RoundingMode.DOWN);

    // formateamos el numero, para ajustarlo a el formato de tres puntos
    // decimales
    String formatedDouble = format.format(doubleNumber);

    // Validamos que sea un numero legal
    if (doubleNumber > 999999999)
    throw new NumberFormatException("El numero es mayor de 999'999.999, "
                                    + "no es posible convertirlo");

                                    if (doubleNumber < 0)
    throw new NumberFormatException("El numero debe ser positivo");

    String splitNumber[] = formatedDouble.split("\\.");


    int millon = Integer.parseInt(
                    new StringBuilder(getDigitFrom(
                        new StringBuilder(splitNumber[0]).reverse().toString(), 6)).reverse().toString());
    if (millon == 1){
      converted.append("UN MILLON ");
    }else if (millon > 1){
      converted.append(convertNumber(String.valueOf(millon))+ "MILLONES ");
    }

    int miles = Integer.parseInt(
                    new StringBuilder(getDigitFromTo(
                        new StringBuilder(splitNumber[0]).reverse().toString(), 3,6)).reverse().toString());
    if (miles == 1)
      converted.append("MIL ");
    else if (miles > 1)
      converted.append(convertNumber(String.valueOf(miles)) + "MIL ");

    int cientos = Integer.parseInt(
                    new StringBuilder(getDigitFromTo(
                        new StringBuilder(splitNumber[0]).reverse().toString(), 0,3)).reverse().toString());

    if (cientos == 1)
      converted.append("UN");
    if (millon + miles + cientos == 0)
      converted.append("CERO");
    if (cientos > 1)
      converted.append(convertNumber(String.valueOf(cientos)));

    converted.append("PESOS");

    // Descompone los centavos

    int centavos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[1], 2))
            + String.valueOf(getDigitAt(splitNumber[1], 1))
            + String.valueOf(getDigitAt(splitNumber[1], 0)));

    _log.debug("Centavos ="+centavos);

    if (centavos == 1)
      converted.append(" CON UN CENTAVO");
    else if (centavos > 1)
      converted.append(" CON " + convertNumber(String.valueOf(centavos))
                + "CENTAVOS");

    return converted.toString();
  }

  /**
  * Convierte los trios de numeros que componen las unidades, las decenas y
  * las centenas del numero.
  *
  * @param number
  *            Numero a convetir en digitos
  * @return Numero convertido en letras
  */
  private static String convertNumber(String number) {

    if (number.length() > 3)
      throw new NumberFormatException("La longitud maxima debe ser 3 digitos");

    // Caso especial con el 100
    if (number.equals("100")) {
      return "CIEN";
    }

    StringBuilder output = new StringBuilder();
    if (getDigitAt(number, 2) != 0)
      output.append(CENTENAS[getDigitAt(number, 2) - 1]);

    int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1))
            + String.valueOf(getDigitAt(number, 0)));

    if (k <= 20)
      output.append(UNIDADES[k]);
    else if (k > 30 && getDigitAt(number, 0) != 0)
      output.append(DECENAS[getDigitAt(number, 1) - 2] + "Y "
                  + UNIDADES[getDigitAt(number, 0)]);
    else
      output.append(DECENAS[getDigitAt(number, 1) - 2]
                + UNIDADES[getDigitAt(number, 0)]);
    return output.toString();
  }

  /**
   * Retorna el digito numerico en la posicion indicada de derecha a izquierda.
   *
   * @param origin            Cadena en la cual se busca el digito
   * @param position            Posicion de derecha a izquierda a retornar
   * @return Digito ubicado en la posicion indicada
   */
  private static int getDigitAt(String origin, int position) {
    if (origin.length() > position && position >= 0)
      return origin.charAt(origin.length() - position - 1) - 48;
    return 0;
  }

  /**
   * Gets the digit from.
   *
   * @param origin the origin
   * @param pos the pos
   * @return the digit from
   */
  private static String getDigitFrom(String origin, int pos){
    return (origin.length() > pos && pos >= 0) ? origin.substring(pos) : "0";
  }

  /**
   * Gets the digit from to.
   *
   * @param origin the origin
   * @param begin the begin
   * @param end the end
   * @return the digit from to
   */
  private static String getDigitFromTo(String origin, int begin, int end){
    String result = (origin.length() > begin && begin < end)
            ? origin.substring(begin,
                             origin.length() < end ? origin.length() : end)
            : "0";
    return result;
  }

}
