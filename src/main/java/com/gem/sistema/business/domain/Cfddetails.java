package com.gem.sistema.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Cfddetails.
 */
@Entity
@Table(name = "cfddetails")
public class Cfddetails extends AbstractEntity {
  // ID

  /** The serie. */
  @Column(name = "SERIE")
  private String serie;

  /** The folio. */
  @Column(name = "FOLIO")
  private Long folio;

  /** The concepto. */
  @Column(name = "CONCEPTO")
  private String concepto;

  /** The cant 2. */
  @Column(name = "CANT2")
  private String cant2;

  /** The preciounit. */
  @Column(name = "PRECIOUNIT")
  private BigDecimal preciounit;

  /** The importe. */
  @Column(name = "IMPORTE")
  private BigDecimal importe;

  /** The cant. */
  @Column(name = "CANT")
  private Long cant;

  /** The userid. */
  @Column(name = "USERID")
  private String userid;

  /** The id sector. */
  @Column(name = "IDSECTOR")
  private Long idSector;

  /** The id ref. */
  @Column(name = "ID_REF")
  private Long idRef;


  /**
   * Instantiates a new cfddetails.
   */
  public Cfddetails(){}

  /**
   * Instantiates a new cfddetails.
   *
   * @param cant the cant
   * @param concepto the concepto
   * @param preciounit the preciounit
   * @param importe the importe
   * @param serie the serie
   * @param folio the folio
   * @param idSector the id sector
   */
  public Cfddetails(Long cant, String concepto, BigDecimal preciounit,
                    BigDecimal importe, String serie, Long folio, Long idSector){
    this.cant = cant;
    this.concepto = concepto;
    this.preciounit = preciounit;
    this.importe = importe;
    this.serie = serie;
    this.folio = folio;
    this.idSector = idSector;
  }

  /**
   * Gets the serie.
   *
   * @return the serie
   */
  public String getSerie(){
    return serie;
  }
  
  /**
   * Sets the serie.
   *
   * @param serie the new serie
   */
  public void setSerie(String serie){
    this.serie = serie;
  }

  /**
   * Gets the folio.
   *
   * @return the folio
   */
  public Long getFolio(){
    return folio;
  }
  
  /**
   * Sets the folio.
   *
   * @param folio the new folio
   */
  public void setFolio(Long folio){
    this.folio = folio;
  }

  /**
   * Gets the concepto.
   *
   * @return the concepto
   */
  public String getConcepto(){
    return concepto;
  }
  
  /**
   * Sets the concepto.
   *
   * @param concepto the new concepto
   */
  public void setConcepto(String concepto){
    this.concepto = concepto;
  }

  /**
   * Gets the cant 2.
   *
   * @return the cant 2
   */
  public String getCant2(){
    return cant2;
  }
  
  /**
   * Sets the cant 2.
   *
   * @param cant2 the new cant 2
   */
  public void setCant2(String cant2){
    this.cant2 = cant2;
  }

  /**
   * Gets the preciounit.
   *
   * @return the preciounit
   */
  public BigDecimal getPreciounit(){
    return preciounit;
  }
  
  /**
   * Sets the preciounit.
   *
   * @param preciounit the new preciounit
   */
  public void setPreciounit(BigDecimal preciounit){
    this.preciounit = preciounit;
  }

  /**
   * Gets the importe.
   *
   * @return the importe
   */
  public BigDecimal getImporte(){
    return importe;
  }
  
  /**
   * Sets the importe.
   *
   * @param importe the new importe
   */
  public void setImporte(BigDecimal importe){
    this.importe = importe;
  }

  /**
   * Gets the cant.
   *
   * @return the cant
   */
  public Long getCant(){
    return cant;
  }
  
  /**
   * Sets the cant.
   *
   * @param cant the new cant
   */
  public void setCant(Long cant){
    this.cant = cant;
  }

  /**
   * Gets the userid.
   *
   * @return the userid
   */
  public String getUserid(){
    return userid;
  }
  
  /**
   * Sets the userid.
   *
   * @param userid the new userid
   */
  public void setUserid(String userid){
    this.userid = userid;
  }

  /**
   * Gets the id sector.
   *
   * @return the id sector
   */
  public Long getIdSector(){
    return idSector;
  }
  
  /**
   * Sets the id sector.
   *
   * @param idSector the new id sector
   */
  public void setIdSector(Long idSector){
    this.idSector = idSector;
  }

  /**
   * Gets the id ref.
   *
   * @return the id ref
   */
  public Long getIdRef(){
    return idRef;
  }
  
  /**
   * Sets the id ref.
   *
   * @param idRef the new id ref
   */
  public void setIdRef(Long idRef){
    this.idRef = idRef;
  }

  /* (non-Javadoc)
   * @see com.gem.sistema.business.domain.AbstractEntity#toString()
   */
  public String toString(){
    return new StringBuffer("Cfddetails {")
        .append("serie: ")
        .append(serie)
        .append(",folio: ")
        .append(folio)
        .append(",concepto: ")
        .append(concepto)
        .append(",cant2: ")
        .append(cant2)
        .append(",preciounit: ")
        .append(preciounit)
        .append(",importe: ")
        .append(importe)
        .append(",cant: ")
        .append(cant)
        .append(",userid: ")
        .append(userid)
        .append(",idSector: ")
        .append(idSector)
        .append(",idRef: ")
        .append(idRef)
        .append("}").toString();
  }

}
