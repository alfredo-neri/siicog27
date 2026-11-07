package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class Localidades.
 */
@Entity
@Table(name = "localidades")
public class Localidades extends AbstractEntity{
	
	/** The Constant MAX_LENGTH_nomLoc. */
	@IgnoredQuery
	static final int MAX_LENGTH_nomLoc = 300;
	
	/** The Constant MAX_LENGTH_nomMun. */
	@IgnoredQuery
	static final int MAX_LENGTH_nomMun = 300;
	
	/** The Constant MAX_LENGTH_capturo. */
	@IgnoredQuery
	static final int MAX_LENGTH_capturo = 50;
	
	/** The Constant MAX_LENGTH_xcvemun. */
	@IgnoredQuery
	static final int MAX_LENGTH_xcvemun = 16;
	
	/** The Constant MAX_LENGTH_xcveloc. */
	@IgnoredQuery
	static final int MAX_LENGTH_xcveloc = 16;
	
    /** The cve mun. */
    @Column(name = "cveMun")
    private Integer cveMun;
    
    /** The cve loc. */
    @Column(name = "cveLoc")
    private Integer cveLoc;
    
    /** The nom loc. */
    @Column(name = "nomLoc", length = MAX_LENGTH_nomLoc)
    private String nomLoc;
    
    /** The nom mun. */
    @Column(name = "nomMun", length = MAX_LENGTH_nomMun)
    private String nomMun;
    
    /** The capturo. */
    @Column(name = "capturo", length = MAX_LENGTH_capturo)
    private String capturo;
    
	/** The feccap. */
	@Column(name = "feccap")
	private Date feccap;
	
    /** The xcvemun. */
    @Column(name = "xcvemun", length = MAX_LENGTH_xcvemun)
    private String xcvemun;
    
    /** The xcveloc. */
    @Column(name = "xcveloc", length = MAX_LENGTH_xcveloc)
    private String xcveloc;

	/**
	 * Instantiates a new localidades.
	 *
	 * @param cveMun the cve mun
	 * @param cveLoc the cve loc
	 * @param nomLoc the nom loc
	 * @param nomMun the nom mun
	 * @param capturo the capturo
	 * @param feccap the feccap
	 * @param xcvemun the xcvemun
	 * @param xcveloc the xcveloc
	 */
	public Localidades(Integer cveMun, Integer cveLoc, String nomLoc, String nomMun, String capturo, Date feccap,
			String xcvemun, String xcveloc) {
		this.cveMun = cveMun;
		this.cveLoc = cveLoc;
		this.nomLoc = nomLoc;
		this.nomMun = nomMun;
		this.capturo = capturo;
		this.feccap = feccap;
		this.xcvemun = xcvemun;
		this.xcveloc = xcveloc;
	}
	
	/**
	 * Instantiates a new localidades.
	 */
	public Localidades(){}

	/**
	 * Gets the cve mun.
	 *
	 * @return the cve mun
	 */
	@ColumnFile(indexColumn=1)
	public Integer getCveMun() {
		return cveMun;
	}

	/**
	 * Sets the cve mun.
	 *
	 * @param cveMun the new cve mun
	 */
	public void setCveMun(Integer cveMun) {
		this.cveMun = cveMun;
	}

	/**
	 * Gets the cve loc.
	 *
	 * @return the cve loc
	 */
	@ColumnFile(indexColumn=2)
	public Integer getCveLoc() {
		return cveLoc;
	}

	/**
	 * Sets the cve loc.
	 *
	 * @param cveLoc the new cve loc
	 */
	public void setCveLoc(Integer cveLoc) {
		this.cveLoc = cveLoc;
	}

	/**
	 * Gets the nom loc.
	 *
	 * @return the nom loc
	 */
	@ColumnFile(indexColumn=3)
	public String getNomLoc() {
		return nomLoc;
	}

	/**
	 * Sets the nom loc.
	 *
	 * @param nomLoc the new nom loc
	 */
	public void setNomLoc(String nomLoc) {
		this.nomLoc = nomLoc;
	}

	/**
	 * Gets the nom mun.
	 *
	 * @return the nom mun
	 */
	public String getNomMun() {
		return nomMun;
	}

	/**
	 * Sets the nom mun.
	 *
	 * @param nomMun the new nom mun
	 */
	public void setNomMun(String nomMun) {
		this.nomMun = nomMun;
	}

	/**
	 * Gets the capturo.
	 *
	 * @return the capturo
	 */
	public String getCapturo() {
		return capturo;
	}

	/**
	 * Sets the capturo.
	 *
	 * @param capturo the new capturo
	 */
	public void setCapturo(String capturo) {
		this.capturo = capturo;
	}

	/**
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	public Date getFeccap() {
		return feccap;
	}

	/**
	 * Sets the feccap.
	 *
	 * @param feccap the new feccap
	 */
	public void setFeccap(Date feccap) {
		this.feccap = feccap;
	}

	/**
	 * Gets the xcvemun.
	 *
	 * @return the xcvemun
	 */
	public String getXcvemun() {
		return xcvemun;
	}

	/**
	 * Sets the xcvemun.
	 *
	 * @param xcvemun the new xcvemun
	 */
	public void setXcvemun(String xcvemun) {
		this.xcvemun = xcvemun;
	}

	/**
	 * Gets the xcveloc.
	 *
	 * @return the xcveloc
	 */
	public String getXcveloc() {
		return xcveloc;
	}

	/**
	 * Sets the xcveloc.
	 *
	 * @param xcveloc the new xcveloc
	 */
	public void setXcveloc(String xcveloc) {
		this.xcveloc = xcveloc;
	}
	
    /**
     * Require valid nom loc.
     *
     * @param nomLoc the nom loc
     */
    public void requireValidNomLoc(String nomLoc) {
        isTrue(nomLoc.length() <= MAX_LENGTH_nomLoc,
                "La máxima longitud del nombre de la localidad es de <%d> caracteres.",
                MAX_LENGTH_nomLoc
        );
    }
    
    /**
     * Require valid nom mun.
     *
     * @param nomMun the nom mun
     */
    public void requireValidNomMun(String nomMun) {
        isTrue(nomMun.length() <= MAX_LENGTH_nomMun,
                "La máxima longitud del nombre de la localidad es de <%d> caracteres.",
                MAX_LENGTH_nomMun
        );
    }
    
    /**
     * Require valid xcvemun.
     *
     * @param xcvemun the xcvemun
     */
    public void requireValidXcvemun(String xcvemun) {
        isTrue(xcvemun.length() <= MAX_LENGTH_xcvemun,
                "La máxima longitud para la clave de municipio es de <%d> caracteres.",
                MAX_LENGTH_xcvemun
        );
    }
    
    /**
     * Require valid xcveloc.
     *
     * @param xcveloc the xcveloc
     */
    public void requireValidXcveloc(String xcveloc) {
        isTrue(xcveloc.length() <= MAX_LENGTH_xcveloc,
                "La máxima longitud para la clave de localidad es de <%d> caracteres.",
                MAX_LENGTH_xcveloc
        );
    }
    
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
        return new ToStringBuilder(this)               
                .append("cveMun", this.cveMun)
                .append("cveLoc", this.cveLoc)               
                .append("nomLoc", this.nomLoc)
                .append("nomMun", this.nomMun)
                .append("capturo", this.capturo)
                .append("feccap", this.feccap)
                .append("xcvemun", this.xcvemun)
                .append("xcveloc", this.xcveloc)
                .toString();
	}


}
