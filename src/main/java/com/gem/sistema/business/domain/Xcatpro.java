package com.gem.sistema.business.domain;
import static com.gem.sistema.business.common.PreCondition.isTrue;
import static com.gem.sistema.business.common.PreCondition.notNull;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.gem.sistema.annotation.ColumnFile;
import com.gem.sistema.annotation.IgnoredQuery;
// TODO: Auto-generated Javadoc
/**
 * The persistent class for the XCATPRO database table.
 * 
 */
@Entity
@NamedQuery(name="Xcatpro.findAll", query="SELECT x FROM Xcatpro x")
public class Xcatpro extends AbstractEntity implements Serializable  {
	
	/** The Constant serialVersionUID. */
	@IgnoredQuery
	private static final long serialVersionUID = 1L;
	
	/** The Constant MAX_LENGTH_clvpro. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvpro = 24;
	
	/** The Constant MAX_LENGTH_nompro. */
	@IgnoredQuery
	static final int MAX_LENGTH_nompro = 250;
	
	/** The Constant MAX_LENGTH_clvdep. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvdep = 6;
	
	/** The Constant MAX_LENGTH_clvfun. */
	@IgnoredQuery
	static final int MAX_LENGTH_clvfun = 12;	
	

	/** The cappro. */
	private String cappro;
	
	/** The clvdep. */
	@Column(name = "clvdep", nullable = false, length = MAX_LENGTH_clvdep)
	private String clvdep;

	/** The clvfin. */
	private String clvfin;
	
	/** The clvfun. */
	@Column(name = "clvfun", length = MAX_LENGTH_clvfun)
	private String clvfun;
	
	/** The clvpro. */
	@Column(name = "clvpro", nullable = false, length = MAX_LENGTH_clvpro)
	private String clvpro;

	/** The feccap. */
	@Temporal(TemporalType.DATE)
	private Date feccap;

	/** The id ref. */
	@Column(name="ID_REF")
	private Long idRef;

	/** The ini. */
	private String ini;
	
	/** The nompro. */
	@Column(name = "nompro", nullable = false, length = MAX_LENGTH_nompro)
	private String nompro;
//
//	private String programa;

	/** The sectorid. */
@Column(name = "SECTORID")
	private Integer sectorid;

	/** The ultniv. */
	private String ultniv;

	/** The userid. */
	@Column(name="USERID")
	private String userid;
	
	/** The campo 0. */
	@Transient
	@IgnoredQuery
	private String campo0;
	
	/** The campo 1. */
	@Transient
	@IgnoredQuery
	private String campo1;
	
	/** The campo 2. */
	@Transient
	@IgnoredQuery
	private String campo2;
	
	/** The campo 3. */
	@Transient
	@IgnoredQuery
	private String campo3;
	
	/** The campo 4. */
	@Transient
	@IgnoredQuery
	private String campo4;
	
	/** The campo 5. */
	@Transient
	@IgnoredQuery
	private String campo5;

	/**
	 * Instantiates a new xcatpro.
	 */
	public Xcatpro() {
	}

	/**
	 * Gets the cappro.
	 *
	 * @return the cappro
	 */
	public String getCappro() {
		return this.cappro;
	}

	/**
	 * Sets the cappro.
	 *
	 * @param cappro the new cappro
	 */
	public void setCappro(String cappro) {
		this.cappro = cappro;
	}

	/**
	 * Gets the clvdep.
	 *
	 * @return the clvdep
	 */
	@ColumnFile(indexColumn=1)
	public String getClvdep() {
		return this.clvdep;
	}

	/**
	 * Sets the clvdep.
	 *
	 * @param clvdep the new clvdep
	 */
	public void setClvdep(String clvdep) {
		this.clvdep = clvdep;
	}

	/**
	 * Gets the clvfin.
	 *
	 * @return the clvfin
	 */
	@ColumnFile(indexColumn=3)
	public String getClvfin() {
		return this.clvfin;
	}

	/**
	 * Sets the clvfin.
	 *
	 * @param clvfin the new clvfin
	 */
	public void setClvfin(String clvfin) {
		this.clvfin = clvfin;
	}

	/**
	 * Gets the clvfun.
	 *
	 * @return the clvfun
	 */
	@ColumnFile(indexColumn=2)
	public String getClvfun() {
		return this.clvfun;
	}

	/**
	 * Sets the clvfun.
	 *
	 * @param clvfun the new clvfun
	 */
	public void setClvfun(String clvfun) {
		this.clvfun = clvfun;
	}

	/**
	 * Gets the clvpro.
	 *
	 * @return the clvpro
	 */
	public String getClvpro() {
		return this.clvpro;
	}

	/**
	 * Sets the clvpro.
	 *
	 * @param clvpro the new clvpro
	 */
	public void setClvpro(String clvpro) {
		this.clvpro = clvpro;
	}

	/**
	 * Gets the feccap.
	 *
	 * @return the feccap
	 */
	public Date getFeccap() {
		return this.feccap;
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
	 * Gets the id ref.
	 *
	 * @return the id ref
	 */
	public Long getIdRef() {
		return this.idRef;
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
	 * Gets the ini.
	 *
	 * @return the ini
	 */
	public String getIni() {
		return this.ini;
	}

	/**
	 * Sets the ini.
	 *
	 * @param ini the new ini
	 */
	public void setIni(String ini) {
		this.ini = ini;
	}

	/**
	 * Gets the nompro.
	 *
	 * @return the nompro
	 */
	@ColumnFile(indexColumn=4)
	public String getNompro() {
		return this.nompro;
	}

	/**
	 * Sets the nompro.
	 *
	 * @param nompro the new nompro
	 */
	public void setNompro(String nompro) {
		this.nompro = nompro;
	}

	/**
	 * Gets the sectorid.
	 *
	 * @return the sectorid
	 */
	public Integer getSectorid() {
		return this.sectorid;
	}

	/**
	 * Sets the sectorid.
	 *
	 * @param sectorid the new sectorid
	 */
	public void setSectorid(Integer sectorid) {
		this.sectorid = sectorid;
	}

//	public String getPrograma() {
//		return this.programa;
//	}
//
//	public void setPrograma(String programa) {
//		this.programa = programa;
//	}

	/**
 * Gets the ultniv.
 *
 * @return the ultniv
 */
public String getUltniv() {
		return this.ultniv;
	}

	/**
	 * Sets the ultniv.
	 *
	 * @param ultniv the new ultniv
	 */
	public void setUltniv(String ultniv) {
		this.ultniv = ultniv;
	}
	
	/**
	 * Gets the campo 0.
	 *
	 * @return the campo 0
	 */
	public String getCampo0() {
		return campo0;
	}

	/**
	 * Sets the campo 0.
	 *
	 * @param campo0 the campo0 to set
	 */
	public void setCampo0(String campo0) {
		this.campo0 = campo0;
	}

	/**
	 * Gets the campo 1.
	 *
	 * @return the campo1
	 */
	public String getCampo1() {
		return campo1;
	}

	/**
	 * Sets the campo 1.
	 *
	 * @param campo1 the campo1 to set
	 */
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	/**
	 * Gets the campo 2.
	 *
	 * @return the campo2
	 */
	public String getCampo2() {
		return campo2;
	}

	/**
	 * Sets the campo 2.
	 *
	 * @param campo2 the campo2 to set
	 */
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	/**
	 * Gets the campo 3.
	 *
	 * @return the campo3
	 */
	public String getCampo3() {
		return campo3;
	}

	/**
	 * Sets the campo 3.
	 *
	 * @param campo3 the campo3 to set
	 */
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	/**
	 * Gets the campo 4.
	 *
	 * @return the campo4
	 */
	public String getCampo4() {
		return campo4;
	}

	/**
	 * Sets the campo 4.
	 *
	 * @param campo4 the campo4 to set
	 */
	public void setCampo4(String campo4) {
		this.campo4 = campo4;
	}

	/**
	 * Gets the campo 5.
	 *
	 * @return the campo5
	 */
	public String getCampo5() {
		return campo5;
	}

	/**
	 * Sets the campo 5.
	 *
	 * @param campo5 the campo5 to set
	 */
	public void setCampo5(String campo5) {
		this.campo5 = campo5;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return this.userid;
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
	 * Instantiates a new xcatpro.
	 *
	 * @param clvpro the clvpro
	 * @param nompro the nompro
	 * @param cappro the cappro
	 * @param feccap the feccap
	 * @param ultniv the ultniv
	 * @param clvdep the clvdep
	 * @param clvfun the clvfun
	 * @param clvfin the clvfin
	 */
	public Xcatpro(String clvpro, String nompro, String cappro, Date feccap, String ultniv, String clvdep,
			String clvfun, String clvfin) {
		this.clvpro = clvpro;
		this.nompro = nompro;
		this.cappro = cappro;
		this.feccap = feccap;
		this.ultniv = ultniv;
		this.clvdep = clvdep;
		this.clvfun = clvfun;
		this.clvfin = clvfin;	
	}
	
	
	/**
	 * Instantiates a new xcatpro.
	 *
	 * @param record the record
	 */
	/*"" "UNIVERSIDAD MEXIQUENSE DEL BICENTENARIO" "" ? "S" "205BO00001" "010304010101102" "" ""*/
	public Xcatpro(final CSVRecord record) {
		this.clvpro = record.get(0);
		this.nompro = record.get(1);
		this.ultniv = record.get(4);
		this.clvdep = record.get(5);
		this.clvfun = record.get(6);
	}		
	
	/**
	 * Require valid clvpro.
	 *
	 * @param clvpro the clvpro
	 */
	public void requireValidClvpro(String clvpro) {
        notNull(clvpro, "clvpro no puede ser nulo.");
        isTrue(clvpro.length() <= MAX_LENGTH_clvpro,
                "La máxima longitud de clvpro es de <%d> caracteres.",
                MAX_LENGTH_clvpro
        );
    }
    
    /**
     * Require valid nompro.
     *
     * @param nompro the nompro
     */
    public void requireValidNompro(String nompro) {
        notNull(nompro, "nompro no puede ser nulo.");
        isTrue(nompro.length() <= MAX_LENGTH_nompro,
                "La máxima longitud de nompro es de <%d> caracteres.",
                MAX_LENGTH_nompro
        );
    }
    
    /**
     * Require validclvdep.
     *
     * @param clvdep the clvdep
     */
    public void requireValidclvdep(String clvdep) {
        notNull(clvdep, "clvdep no puede ser nulo.");
        isTrue(clvdep.length() <= MAX_LENGTH_clvdep,
                "La máxima longitud de clvdep es de <%d> caracteres.",
                MAX_LENGTH_clvdep
        );
    }
    
    /**
     * Require validclvfun.
     *
     * @param clvfun the clvfun
     */
    public void requireValidclvfun(String clvfun) {
        notNull(clvfun, "clvfun no puede ser nulo.");
        isTrue(clvfun.length() <= MAX_LENGTH_clvfun,
                "La máxima longitud de clvfun es de <%d> caracteres.",
                MAX_LENGTH_clvfun
        );
    }

    /* (non-Javadoc)
     * @see com.gem.sistema.business.domain.AbstractEntity#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)      
        		.append("id", this.getId())
        		.append("index", this.getIndex())
                .append("clvpro", this.clvpro)
                .append("nompro", this.nompro)               
                .append("cappro", this.cappro)
                .append("feccap", this.feccap)
                .append("ultniv", this.ultniv)
                .append("clvdep", this.clvdep)
                .append("clvfun", this.clvfun)
                .append("clvfin", this.clvfin)
                .append("campo5", this.campo5)
                .append("campo0", this.campo0)
                .append("campo1", this.campo1)
                .append("campo2", this.campo2)
                .append("campo3", this.campo3)
                .append("campo4", this.campo4)
                .toString();
    }  

}