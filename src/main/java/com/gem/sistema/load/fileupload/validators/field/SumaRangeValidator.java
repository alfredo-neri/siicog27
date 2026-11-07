/**
 * 
 */
package com.gem.sistema.load.fileupload.validators.field;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import com.gem.sistema.load.fileupload.exceptions.ValidateTokenException;
import com.gem.sistema.load.fileupload.validators.AbstractCellFieldValidator;




// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> Clase que valida si la fecha inicial es mayor que la fecha final. </b>
 * </p>
 */
public class SumaRangeValidator extends AbstractCellFieldValidator {

    /**
     * <p>
     * <b> indice a iniciar la sumatorioa campo a validar. </b>
     * </p>
     */
    private List<Integer>  indexToSum;
    
    
    /** The decimal format. */
    private DecimalFormat decimalFormat =  new DecimalFormat("################.##");
   

    /**
     * Validate.
     *
     * @param fields the fields
     * @throws ValidateTokenException .
     * @see com.hsbc.contrac.filetransfer.fileupload.validators.FieldValidator#validate
     *      (java.lang.Object)
     */
    public void validate(final String[] fields) throws ValidateTokenException {
        final String sumaActual = getContentFromCell(fields, this.getIndex());
        
        double suma=0.0;
        for (int i : getIndexToSum()) {
        	Number numero;
        	String val = getContentFromCell(fields, i);
			try {
				
				numero = decimalFormat.parse(val);
			} catch (ParseException e) {
				 throw new ValidateTokenException(String.format( "Columna %1$s [%2$s] no contiene un valor numerico",i+1,val));
			}
        	
        	suma += numero.doubleValue();
		}
        
        if( suma != Double.valueOf(sumaActual).doubleValue() ){
        	  throw new ValidateTokenException(String.format(this.getMessageError(),sumaActual, decimalFormat.format(suma)));
        }

    }



	/**
	 * Gets the index to sum.
	 *
	 * @return the index to sum
	 */
	public List<Integer> getIndexToSum() {
		return indexToSum;
	}



	/**
	 * Sets the index to sum.
	 *
	 * @param indexToSum the new index to sum
	 */
	public void setIndexToSum(List<Integer> indexToSum) {
		this.indexToSum = indexToSum;
	}





    
    
}
