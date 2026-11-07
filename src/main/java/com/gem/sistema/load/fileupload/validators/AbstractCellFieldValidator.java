package com.gem.sistema.load.fileupload.validators;



import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


// TODO: Auto-generated Javadoc
/**
 * <p>
 * <b> Validador de Celdas. </b>
 * </p>
 */
public abstract class AbstractCellFieldValidator extends AbstractSimpleFieldValidator<String[]> {

    /**
     * <p>
     * <b> LOGGER. </b>
     * </p>
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractSimpleFieldValidator.class);

    /**
     * <p>
     * <b> getContentFromCell. Metodo para obtener el valor de un token</b>
     * </p>
     *
     * @param fields            .
     * @param index the index
     * @return .
     */
    protected String getContentFromCell(final String[] fields, final Integer index) {
        final String cell = fields[index];
        

        if (null == cell) {
            return StringUtils.EMPTY;
        }


        AbstractCellFieldValidator.LOGGER.debug(index + ":" + cell);
        return cell;
    }
}
