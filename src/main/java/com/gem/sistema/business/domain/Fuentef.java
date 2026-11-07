package com.gem.sistema.business.domain;

import static com.gem.sistema.business.common.PreCondition.isTrue;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Fuentef.
 */
@Entity
@Table(name = "fuentef")
public class Fuentef extends AbstractEntity{
	
	
	
    /** The camf 1. */
    @Column(name = "camf1", length = Constants.MAX_LENGTH_cta)
    private String camf1;
    
    /** The camf 2. */
    @Column(name = "camf2", length = Constants.MAX_LENGTH_cta)
    private String camf2;
    
    /** The camf 3. */
    @Column(name = "camf3", length = Constants.MAX_LENGTH_cta)
    private String camf3;
    
    /** The camf 4. */
    @Column(name = "camf4", length = Constants.MAX_LENGTH_cta)
    private String camf4;
    
    /** The camf 5. */
    @Column(name = "camf5", length = Constants.MAX_LENGTH_cta)
    private String camf5;
    
    /** The capfuen. */
    @Column(name = "capfuen", length = Constants.MAX_LENGTH_capfuen)
    private String capfuen;
    
    /** The feccap. */
    @Column(name = "feccap")
    private Date feccap;
    
    /** The clvfte. */
    @Column(name = "clvfte" , length = Constants.MAX_LENGTH_clvfte)
    private String clvfte;
    
    /** The nombref. */
    @Column(name = "nombref", length = Constants.MAX_LENGTH_nombref)
    private String nombref;
    
    /** The nivfte. */
    @Column(name = "nivfte")
    private Integer nivfte;
    

	/** The id ref. */
	@Column(name = "ID_REF")
	private Long idRef;

	/** The idsector. */
	@Column(name = "IDSECTOR")
	private Integer idsector;
    
    /** The liga. */
    @Column(name = "liga", length = Constants.MAX_LENGTH_liga)
    private String liga;
    
    /** The userid. */
    @Column(name = "USERID")
	private String userid;
    
    

	/**
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return idRef;
	}

	/**
	 * Sets the id ref.
	 *
	 * @param idRef the new id ref
	 */
	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	/**
	 * Gets the idsector.
	 *
	 * @return the idsector
	 */
	public Integer getIdsector() {
		return idsector;
	}

	/**
	 * Sets the idsector.
	 *
	 * @param idsector the new idsector
	 */
	public void setIdsector(Integer idsector) {
		this.idsector = idsector;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * Instantiates a new fuentef.
	 *
	 * @param camf1 the camf 1
	 * @param camf2 the camf 2
	 * @param camf3 the camf 3
	 * @param camf4 the camf 4
	 * @param camf5 the camf 5
	 * @param capfuen the capfuen
	 * @param feccap the feccap
	 * @param clvfte the clvfte
	 * @param nombref the nombref
	 * @param nivfte the nivfte
	 * @param liga the liga
	 */
	public Fuentef(String camf1, String camf2, String camf3, String camf4, String camf5, String capfuen, Date feccap,
			String clvfte, String nombref, Integer nivfte, String liga) {
		this.camf1 = camf1;
		this.camf2 = camf2;
		this.camf3 = camf3;
		this.camf4 = camf4;
		this.camf5 = camf5;
		this.capfuen = capfuen;
		this.feccap = feccap;
		this.clvfte = clvfte;
		this.nombref = nombref;
		this.nivfte = nivfte;
		this.liga = liga;
	}

	/**
	 * Instantiates a new fuentef.
	 */
	public Fuentef(){
		
	}

	/**
	 * Gets the camf 1.
	 *
	 * @return the camf 1
	 */
	public String getCamf1() {
		return camf1;
	}

	/**
	 * Sets the camf 1.
	 *
	 * @param camf1 the new camf 1
	 */
	public void setCamf1(String camf1) {
		this.camf1 = camf1;
	}

	/**
	 * Gets the camf 2.
	 *
	 * @return the camf 2
	 */
	public String getCamf2() {
		return camf2;
	}

	/**
	 * Sets the camf 2.
	 *
	 * @param camf2 the new camf 2
	 */
	public void setCamf2(String camf2) {
		this.camf2 = camf2;
	}

	/**
	 * Gets the camf 3.
	 *
	 * @return the camf 3
	 */
	public String getCamf3() {
		return camf3;
	}

	/**
	 * Sets the camf 3.
	 *
	 * @param camf3 the new camf 3
	 */
	public void setCamf3(String camf3) {
		this.camf3 = camf3;
	}

	/**
	 * Gets the camf 4.
	 *
	 * @return the camf 4
	 */
	public String getCamf4() {
		return camf4;
	}

	/**
	 * Sets the camf 4.
	 *
	 * @param camf4 the new camf 4
	 */
	public void setCamf4(String camf4) {
		this.camf4 = camf4;
	}

	/**
	 * Gets the camf 5.
	 *
	 * @return the camf 5
	 */
	public String getCamf5() {
		return camf5;
	}

	/**
	 * Sets the camf 5.
	 *
	 * @param camf5 the new camf 5
	 */
	public void setCamf5(String camf5) {
		this.camf5 = camf5;
	}

	/**
	 * Gets the capfuen.
	 *
	 * @return the capfuen
	 */
	public String getCapfuen() {
		return capfuen;
	}

	/**
	 * Sets the capfuen.
	 *
	 * @param capfuen the new capfuen
	 */
	public void setCapfuen(String capfuen) {
		this.capfuen = capfuen;
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
	 * Gets the nombref.
	 *
	 * @return the nombref
	 */
	@ColumnFile(indexColumn=3)
	public String getNombref() {
		return nombref;
	}

	/**
	 * Sets the nombref.
	 *
	 * @param nombref the new nombref
	 */
	public void setNombref(String nombref) {
		this.nombref = nombref;
	}

	/**
	 * Gets the nivfte.
	 *
	 * @return the nivfte
	 */
	@ColumnFile(indexColumn=4)
	public Integer getNivfte() {
		return nivfte;
	}

	/**
	 * Sets the nivfte.
	 *
	 * @param nivfte the new nivfte
	 */
	public void setNivfte(Integer nivfte) {
		this.nivfte = nivfte;
	}

	/**
	 * Gets the liga.
	 *
	 * @return the liga
	 */
	@ColumnFile(indexColumn=1)
	public String getLiga() {
		return liga;
	}

	/**
	 * Sets the liga.
	 *
	 * @param liga the new liga
	 */
	public void setLiga(String liga) {
		this.liga = liga;
	}
    
	/**
	 * Gets the clvfte.
	 *
	 * @return the clvfte
	 */
	@ColumnFile(indexColumn=2)
    public String getClvfte() {
		return clvfte;
	}

	/**
	 * Sets the clvfte.
	 *
	 * @param clvfte the new clvfte
	 */
	public void setClvfte(String clvfte) {
		this.clvfte = clvfte;
	}

	/**
	 * Require valid clvfte.
	 *
	 * @param clvfte the clvfte
	 */
	public void requireValidClvfte(String clvfte) {
        isTrue(clvfte.length() <= Constants.MAX_LENGTH_clvfte,
                "La máxima longitud de la clave es de <%d> caracteres.",
                Constants.MAX_LENGTH_clvfte
        );
    }
	
	/**
	 * Require valid nombref.
	 *
	 * @param nombref the nombref
	 */
	public void requireValidNombref(String nombref) {
        isTrue(nombref.length() <= Constants.MAX_LENGTH_nombref,
                "La máxima longitud del nombre es de <%d> caracteres.",
                Constants.MAX_LENGTH_nombref
        );
    }
    
	/**
	 * Require valid liga.
	 *
	 * @param liga the liga
	 */
	public void requireValidLiga(String liga) {
        isTrue(liga.length() <= Constants.MAX_LENGTH_liga,
                "La máxima longitud de la liga es de <%d> caracteres.",
                Constants.MAX_LENGTH_liga
        );
    }
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.business.domain.AbstractEntity#toString()
	 */
	@Override
	public String toString() {
        return new ToStringBuilder(this)    
        		.append("id", this.getId())
        		.append("id", this.getIndex())
                .append("camf1", this.camf1)
                .append("camf2", this.camf2)               
                .append("camf3", this.camf3)
                .append("camf4", this.camf4)
                .append("camf5", this.camf5)
                .append("capfuen", this.capfuen)
                .append("feccap", this.feccap)
                .append("clvfte", this.clvfte)
                .append("nombref", this.nombref)
                .append("nivfte", this.nivfte)
                .append("liga", this.liga)
                .toString();
	}

}
