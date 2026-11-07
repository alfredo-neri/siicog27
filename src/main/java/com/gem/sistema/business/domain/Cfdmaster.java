package com.gem.sistema.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Cfdmaster.
 */
@Entity
@Table(name = "cfdmaster")
public class Cfdmaster extends AbstractEntity {


  /** The serie. */
  @Column(name="SERIE")
  private String serie;

  /** The folio. */
  @Column(name="FOLIO")
  private Long folio;

  /** The tipcomp. */
  @Column(name="TIPCOMP")
  private String tipcomp;

  /** The formapago. */
  @Column(name="FORMAPAGO")
  private String formapago;

  /** The subtotal. */
  @Column(name="SUBTOTAL")
  private BigDecimal subtotal;

  /** The iva. */
  @Column(name="IVA")
  private BigDecimal iva;

  /** The total. */
  @Column(name="TOTAL")
  private BigDecimal total;

  /** The rfcemi. */
  @Column(name="RFCEMI")
  private String rfcemi;

  /** The nomemi. */
  @Column(name="NOMEMI")
  private String nomemi;

  /** The callenumemi. */
  @Column(name="CALLENUMEMI")
  private String callenumemi;

  /** The ciudademi. */
  @Column(name="CIUDADEMI")
  private String ciudademi;

  /** The coloniaemi. */
  @Column(name="COLONIAEMI")
  private String coloniaemi;

  /** The delegemi. */
  @Column(name="DELEGEMI")
  private String delegemi;

  /** The estadoemi. */
  @Column(name="ESTADOEMI")
  private String estadoemi;

  /** The paisemi. */
  @Column(name="PAISEMI")
  private String paisemi;

  /** The rfcrec. */
  @Column(name="RFCREC")
  private String rfcrec;

  /** The nomrec. */
  @Column(name="NOMREC")
  private String nomrec;

  /** The callenumrec. */
  @Column(name="CALLENUMREC")
  private String callenumrec;

  /** The ciudadrec. */
  @Column(name="CIUDADREC")
  private String ciudadrec;

  /** The coloniarec. */
  @Column(name="COLONIAREC")
  private String coloniarec;

  /** The delegrec. */
  @Column(name="DELEGREC")
  private String delegrec;

  /** The estadorec. */
  @Column(name="ESTADOREC")
  private String estadorec;

  /** The paisrec. */
  @Column(name="PAISREC")
  private String paisrec;

  /** The cadorig. */
  @Column(name="CADORIG")
  private String cadorig;

  /** The fechacap. */
  @Column(name="FECHACAP")
  private Date fechacap;

  /** The capturo. */
  @Column(name="CAPTURO")
  private String capturo;

  /** The fechacancel. */
  @Column(name="FECHACANCEL")
  private Date fechacancel;

  /** The cancelacion. */
  @Column(name="CANCELACION")
  private String cancelacion;

  /** The totletra. */
  @Column(name="TOTLETRA")
  private String totletra;

  /** The folinicio. */
  @Column(name="FOLINICIO")
  private Long folinicio;

  /** The folfinal. */
  @Column(name="FOLFINAL")
  private Long folfinal;

  /** The cpemi. */
  @Column(name="CPEMI")
  private String cpemi;

  /** The cprec. */
  @Column(name="CPREC")
  private String cprec;

  /** The nocertif. */
  @Column(name="NOCERTIF")
  private String nocertif;

  /** The fecha. */
  @Column(name="FECHA")
  private Date fecha;

  /** The hora. */
  @Column(name="HORA")
  private String hora;

  /** The userid. */
  @Column(name="USERID")
  private String userid;

  /** The idsector. */
  @Column(name="IDSECTOR")
  private Long idsector;

  /** The idref. */
  @Column(name="ID_REF")
  private Long idref;

  /**
   * Instantiates a new cfdmaster.
   */
  public Cfdmaster(){}

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
   * Gets the tipcomp.
   *
   * @return the tipcomp
   */
  public String getTipcomp(){
    return tipcomp;
  }
  
  /**
   * Sets the tipcomp.
   *
   * @param tipcomp the new tipcomp
   */
  public void setTipcomp(String tipcomp){
    this.tipcomp = tipcomp;
  }
  
  /**
   * Gets the formapago.
   *
   * @return the formapago
   */
  public String getFormapago(){
    return formapago;
  }
  
  /**
   * Sets the formapago.
   *
   * @param formapago the new formapago
   */
  public void setFormapago(String formapago){
    this.formapago = formapago;
  }
  
  /**
   * Gets the subtotal.
   *
   * @return the subtotal
   */
  public BigDecimal getSubtotal(){
    return subtotal;
  }
  
  /**
   * Sets the subtotal.
   *
   * @param subtotal the new subtotal
   */
  public void setSubtotal(BigDecimal subtotal){
    this.subtotal = subtotal;
  }
  
  /**
   * Gets the iva.
   *
   * @return the iva
   */
  public BigDecimal getIva(){
    return iva;
  }
  
  /**
   * Sets the iva.
   *
   * @param iva the new iva
   */
  public void setIva(BigDecimal iva){
    this.iva = iva;
  }
  
  /**
   * Gets the total.
   *
   * @return the total
   */
  public BigDecimal getTotal(){
    return total;
  }
  
  /**
   * Sets the total.
   *
   * @param total the new total
   */
  public void setTotal(BigDecimal total){
    this.total = total;
  }
  
  /**
   * Gets the rfcemi.
   *
   * @return the rfcemi
   */
  public String getRfcemi(){
    return rfcemi;
  }
  
  /**
   * Sets the rfcemi.
   *
   * @param rfcemi the new rfcemi
   */
  public void setRfcemi(String rfcemi){
    this.rfcemi = rfcemi;
  }
  
  /**
   * Gets the nomemi.
   *
   * @return the nomemi
   */
  public String getNomemi(){
    return nomemi;
  }
  
  /**
   * Sets the nomemi.
   *
   * @param nomemi the new nomemi
   */
  public void setNomemi(String nomemi){
    this.nomemi = nomemi;
  }
  
  /**
   * Gets the callenumemi.
   *
   * @return the callenumemi
   */
  public String getCallenumemi(){
    return callenumemi;
  }
  
  /**
   * Sets the callenumemi.
   *
   * @param callenumemi the new callenumemi
   */
  public void setCallenumemi(String callenumemi){
    this.callenumemi = callenumemi;
  }
  
  /**
   * Gets the ciudademi.
   *
   * @return the ciudademi
   */
  public String getCiudademi(){
    return ciudademi;
  }
  
  /**
   * Sets the ciudademi.
   *
   * @param ciudademi the new ciudademi
   */
  public void setCiudademi(String ciudademi){
    this.ciudademi = ciudademi;
  }
  
  /**
   * Gets the coloniaemi.
   *
   * @return the coloniaemi
   */
  public String getColoniaemi(){
    return coloniaemi;
  }
  
  /**
   * Sets the coloniaemi.
   *
   * @param coloniaemi the new coloniaemi
   */
  public void setColoniaemi(String coloniaemi){
    this.coloniaemi = coloniaemi;
  }
  
  /**
   * Gets the delegemi.
   *
   * @return the delegemi
   */
  public String getDelegemi(){
    return delegemi;
  }
  
  /**
   * Sets the delegemi.
   *
   * @param delegemi the new delegemi
   */
  public void setDelegemi(String delegemi){
    this.delegemi = delegemi;
  }
  
  /**
   * Gets the estadoemi.
   *
   * @return the estadoemi
   */
  public String getEstadoemi(){
    return estadoemi;
  }
  
  /**
   * Sets the estadoemi.
   *
   * @param estadoemi the new estadoemi
   */
  public void setEstadoemi(String estadoemi){
    this.estadoemi = estadoemi;
  }
  
  /**
   * Gets the paisemi.
   *
   * @return the paisemi
   */
  public String getPaisemi(){
    return paisemi;
  }
  
  /**
   * Sets the paisemi.
   *
   * @param paisemi the new paisemi
   */
  public void setPaisemi(String paisemi){
    this.paisemi = paisemi;
  }
  
  /**
   * Gets the rfcrec.
   *
   * @return the rfcrec
   */
  public String getRfcrec(){
    return rfcrec;
  }
  
  /**
   * Sets the rfcrec.
   *
   * @param rfcrec the new rfcrec
   */
  public void setRfcrec(String rfcrec){
    this.rfcrec = rfcrec;
  }
  
  /**
   * Gets the nomrec.
   *
   * @return the nomrec
   */
  public String getNomrec(){
    return nomrec;
  }
  
  /**
   * Sets the nomrec.
   *
   * @param nomrec the new nomrec
   */
  public void setNomrec(String nomrec){
    this.nomrec = nomrec;
  }
  
  /**
   * Gets the callenumrec.
   *
   * @return the callenumrec
   */
  public String getCallenumrec(){
    return callenumrec;
  }
  
  /**
   * Sets the callenumrec.
   *
   * @param callenumrec the new callenumrec
   */
  public void setCallenumrec(String callenumrec){
    this.callenumrec = callenumrec;
  }
  
  /**
   * Gets the ciudadrec.
   *
   * @return the ciudadrec
   */
  public String getCiudadrec(){
    return ciudadrec;
  }
  
  /**
   * Sets the ciudadrec.
   *
   * @param ciudadrec the new ciudadrec
   */
  public void setCiudadrec(String ciudadrec){
    this.ciudadrec = ciudadrec;
  }
  
  /**
   * Gets the coloniarec.
   *
   * @return the coloniarec
   */
  public String getColoniarec(){
    return coloniarec;
  }
  
  /**
   * Sets the coloniarec.
   *
   * @param coloniarec the new coloniarec
   */
  public void setColoniarec(String coloniarec){
    this.coloniarec = coloniarec;
  }
  
  /**
   * Gets the delegrec.
   *
   * @return the delegrec
   */
  public String getDelegrec(){
    return delegrec;
  }
  
  /**
   * Sets the delegrec.
   *
   * @param delegrec the new delegrec
   */
  public void setDelegrec(String delegrec){
    this.delegrec = delegrec;
  }
  
  /**
   * Gets the estadorec.
   *
   * @return the estadorec
   */
  public String getEstadorec(){
    return estadorec;
  }
  
  /**
   * Sets the estadorec.
   *
   * @param estadorec the new estadorec
   */
  public void setEstadorec(String estadorec){
    this.estadorec = estadorec;
  }
  
  /**
   * Gets the paisrec.
   *
   * @return the paisrec
   */
  public String getPaisrec(){
    return paisrec;
  }
  
  /**
   * Sets the paisrec.
   *
   * @param paisrec the new paisrec
   */
  public void setPaisrec(String paisrec){
    this.paisrec = paisrec;
  }
  
  /**
   * Gets the cadorig.
   *
   * @return the cadorig
   */
  public String getCadorig(){
    return cadorig;
  }
  
  /**
   * Sets the cadorig.
   *
   * @param cadorig the new cadorig
   */
  public void setCadorig(String cadorig){
    this.cadorig = cadorig;
  }
  
  /**
   * Gets the fechacap.
   *
   * @return the fechacap
   */
  public Date getFechacap(){
    return fechacap;
  }
  
  /**
   * Sets the fechacap.
   *
   * @param fechacap the new fechacap
   */
  public void setFechacap(Date fechacap){
    this.fechacap = fechacap;
  }
  
  /**
   * Gets the capturo.
   *
   * @return the capturo
   */
  public String getCapturo(){
    return capturo;
  }
  
  /**
   * Sets the capturo.
   *
   * @param capturo the new capturo
   */
  public void setCapturo(String capturo){
    this.capturo = capturo;
  }
  
  /**
   * Gets the fechacancel.
   *
   * @return the fechacancel
   */
  public Date getFechacancel(){
    return fechacancel;
  }
  
  /**
   * Sets the fechacancel.
   *
   * @param fechacancel the new fechacancel
   */
  public void setFechacancel(Date fechacancel){
    this.fechacancel = fechacancel;
  }
  
  /**
   * Gets the cancelacion.
   *
   * @return the cancelacion
   */
  public String getCancelacion(){
    return cancelacion;
  }
  
  /**
   * Sets the cancelacion.
   *
   * @param cancelacion the new cancelacion
   */
  public void setCancelacion(String cancelacion){
    this.cancelacion = cancelacion;
  }
  
  /**
   * Gets the totletra.
   *
   * @return the totletra
   */
  public String getTotletra(){
    return totletra;
  }
  
  /**
   * Sets the totletra.
   *
   * @param totletra the new totletra
   */
  public void setTotletra(String totletra){
    this.totletra = totletra;
  }
  
  /**
   * Gets the folinicio.
   *
   * @return the folinicio
   */
  public Long getFolinicio(){
    return folinicio;
  }
  
  /**
   * Sets the folinicio.
   *
   * @param folinicio the new folinicio
   */
  public void setFolinicio(Long folinicio){
    this.folinicio = folinicio;
  }
  
  /**
   * Gets the folfinal.
   *
   * @return the folfinal
   */
  public Long getFolfinal(){
    return folfinal;
  }
  
  /**
   * Sets the folfinal.
   *
   * @param folfinal the new folfinal
   */
  public void setFolfinal(Long folfinal){
    this.folfinal = folfinal;
  }
  
  /**
   * Gets the cpemi.
   *
   * @return the cpemi
   */
  public String getCpemi(){
    return cpemi;
  }
  
  /**
   * Sets the cpemi.
   *
   * @param cpemi the new cpemi
   */
  public void setCpemi(String cpemi){
    this.cpemi = cpemi;
  }
  
  /**
   * Gets the cprec.
   *
   * @return the cprec
   */
  public String getCprec(){
    return cprec;
  }
  
  /**
   * Sets the cprec.
   *
   * @param cprec the new cprec
   */
  public void setCprec(String cprec){
    this.cprec = cprec;
  }
  
  /**
   * Gets the nocertif.
   *
   * @return the nocertif
   */
  public String getNocertif(){
    return nocertif;
  }
  
  /**
   * Sets the nocertif.
   *
   * @param nocertif the new nocertif
   */
  public void setNocertif(String nocertif){
    this.nocertif = nocertif;
  }
  
  /**
   * Gets the fecha.
   *
   * @return the fecha
   */
  public Date getFecha(){
    return fecha;
  }
  
  /**
   * Sets the fecha.
   *
   * @param fecha the new fecha
   */
  public void setFecha(Date fecha){
    this.fecha = fecha;
  }
  
  /**
   * Gets the hora.
   *
   * @return the hora
   */
  public String getHora(){
    return hora;
  }
  
  /**
   * Sets the hora.
   *
   * @param hora the new hora
   */
  public void setHora(String hora){
    this.hora = hora;
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
   * Gets the idsector.
   *
   * @return the idsector
   */
  public Long getIdsector(){
    return idsector;
  }
  
  /**
   * Sets the idsector.
   *
   * @param idsector the new idsector
   */
  public void setIdsector(Long idsector){
    this.idsector = idsector;
  }
  
  /**
   * Gets the idref.
   *
   * @return the idref
   */
  public Long getIdref(){
    return idref;
  }
  
  /**
   * Sets the idref.
   *
   * @param idref the new idref
   */
  public void setIdref(Long idref){
    this.idref = idref;
  }

}
