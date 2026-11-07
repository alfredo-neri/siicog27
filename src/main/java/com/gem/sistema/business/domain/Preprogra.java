package com.gem.sistema.business.domain;

import java.math.BigDecimal;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PREPROGRA database table.
 *
 */
@Entity
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(name = "acumula_preprogra",
                             procedureName="SP_ACUMULA_PREPROGRA",
                             parameters = {
                               @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_SECTOR", type = Integer.class),
                               @StoredProcedureParameter(mode = ParameterMode.IN, name = "USER_ID", type = String.class)
                             })
})
@NamedQuery(name="Preprogra.findAll", query="SELECT p FROM Preprogra p")
public class Preprogra extends AbstractEntity {

  /** The id. */
  @Column(name="\"ID\"")
  private long id;

  /** The cveprog. */
  @Column(name="CVEPROG")
  private String cveprog;

  /** The cvedep. */
  @Column(name="CVEDEP")
  private String cvedep;

  /** The fecini. */
  @Column(name="FECINI")
  private Date fecini;

  /** The fecter. */
  @Column(name="FECTER")
  private Date fecter;

  /** The locben. */
  @Column(name="LOCBBEN")
  private String locben;

  /** The pobben. */
  @Column(name="POBBEN")
  private Integer pobben;

  /** The clvfin. */
  @Column(name="CLVFIN")
  private String clvfin;

  /** The clvfun. */
  @Column(name="CLVFUN")
	private String clvfun;

  /** The cantidad. */
  @Column(name="CANTIDAD")
  private BigDecimal cantidad;

  /** The id ref. */
  @Column(name="ID_REF")
  private Long idRef;

  /** The userid. */
  @Column(name="USERID")
  private String userid;

  /** The idsector. */
  @Column(name = "IDSECTOR")
	private Integer idsector;

  /**
   * Gets the id ref.
   *
   * @return the idRef
   */
  public Long getIdRef() {
    return idRef;
  }

  /**
   * Sets the id ref.
   *
   * @param idRef            the idRef to set
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
   * @param idsector            the idsector to set
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
   * @param userid            the userid to set
   */
  public void setUserid(String userid) {
    this.userid = userid;
  }



}
