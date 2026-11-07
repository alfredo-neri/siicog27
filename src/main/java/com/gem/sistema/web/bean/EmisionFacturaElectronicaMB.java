package com.gem.sistema.web.bean;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import com.gem.sistema.business.service.catalogos.CfdmasterService;
import com.gem.sistema.business.service.catalogos.CfddetailsService;
import com.gem.sistema.business.domain.Cfdmaster;
import com.gem.sistema.business.domain.Cfddetails;

import com.roonin.utils.NumberToLetterConverter;


// TODO: Auto-generated Javadoc
/**
 * The Class EmisionFacturaElectronicaMB.
 */
@ManagedBean(name = "emisionFacturaElectronica")
@ViewScoped
public class EmisionFacturaElectronicaMB extends AbstractMB {

  /**     linked view props. */
  private String noCertificado;
  
  /** The folio inicial. */
  private Long folioInicial;
  
  /** The folio final. */
  private Long folioFinal;
  
  /** The serie. */
  private String serie;
  
  /** The folio. */
  private Long folio;
  
  /** The tipo comprobante. */
  private String tipoComprobante;
  
  /** The forma de pago. */
  private String formaDePago;
  
  /** The fecha. */
  private Date fecha;
  
  /** The emisor razon social. */
  private String emisorRazonSocial;
  
  /** The emisor RFC. */
  private String emisorRFC;
  
  /** The emisor calle numero. */
  private String emisorCalleNumero;
  
  /** The emisor ciudad. */
  private String emisorCiudad;
  
  /** The emisor colonia. */
  private String emisorColonia;
  
  /** The emisor delegacion. */
  private String emisorDelegacion;
  
  /** The emisor estado. */
  private String emisorEstado;
  
  /** The emisor CP. */
  private String emisorCP;
  
  /** The emisor pais. */
  private String emisorPais;
  
  /** The receptor razon social. */
  private String receptorRazonSocial;
  
  /** The receptor RFC. */
  private String receptorRFC;
  
  /** The receptor calle numero. */
  private String receptorCalleNumero;
  
  /** The receptor ciudad. */
  private String receptorCiudad;
  
  /** The receptor colonia. */
  private String receptorColonia;
  
  /** The receptor delegacion. */
  private String receptorDelegacion;
  
  /** The receptor estado. */
  private String receptorEstado;
  
  /** The receptor CP. */
  private String receptorCP;
  
  /** The receptor pais. */
  private String receptorPais;
  
  /** The cantidad. */
  private Long cantidad;
  
  /** The concepto. */
  private String concepto;
  
  /** The precio unitario. */
  private Double precioUnitario;
  
  /** The importe. */
  private Double importe;
  
  /** The subtotal. */
  private Double subtotal;
  
  /** The iva. */
  private Double iva;
  
  /** The total. */
  private Double total;

  /**     linked status. */

  public Boolean disabled = Boolean.TRUE;
  
  /** The cfd disabled. */
  public Boolean cfdDisabled = Boolean.TRUE;

  /**     Services. */
  @ManagedProperty("#{cfdmasterService}")
  CfdmasterService cfdmasterService;

  /** The cfddetails service. */
  @ManagedProperty("#{cfddetailsService}")
  CfddetailsService cfddetailsService;

  /**
   * Gets the no certificado.
   *
   * @return the no certificado
   */
  public String getNoCertificado(){
    return noCertificado;
  }
  
  /**
   * Sets the no certificado.
   *
   * @param noCertificado the new no certificado
   */
  public void setNoCertificado(String noCertificado){
    this.noCertificado = noCertificado;
  }

  /**
   * Gets the folio inicial.
   *
   * @return the folio inicial
   */
  public Long getFolioInicial(){
    return folioInicial;
  }
  
  /**
   * Sets the folio inicial.
   *
   * @param folioInicial the new folio inicial
   */
  public void setFolioInicial(Long folioInicial){
    this.folioInicial = folioInicial;
  }

  /**
   * Gets the folio final.
   *
   * @return the folio final
   */
  public Long getFolioFinal(){
    return folioFinal;
  }
  
  /**
   * Sets the folio final.
   *
   * @param folioFinal the new folio final
   */
  public void setFolioFinal(Long folioFinal){
    this.folioFinal = folioFinal;
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
   * Gets the tipo comprobante.
   *
   * @return the tipo comprobante
   */
  public String getTipoComprobante(){
    return tipoComprobante;
  }
  
  /**
   * Sets the tipo comprobante.
   *
   * @param tipoComprobante the new tipo comprobante
   */
  public void setTipoComprobante(String tipoComprobante){
    this.tipoComprobante = tipoComprobante;
  }

  /**
   * Gets the forma de pago.
   *
   * @return the forma de pago
   */
  public String getFormaDePago(){
    return formaDePago;
  }
  
  /**
   * Sets the forma de pago.
   *
   * @param formaDePago the new forma de pago
   */
  public void setFormaDePago(String formaDePago){
    this.formaDePago = formaDePago;
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
   * Gets the emisor razon social.
   *
   * @return the emisor razon social
   */
  public String getEmisorRazonSocial(){
    return emisorRazonSocial;
  }
  
  /**
   * Sets the emisor razon social.
   *
   * @param emisorRazonSocial the new emisor razon social
   */
  public void setEmisorRazonSocial(String emisorRazonSocial){
    this.emisorRazonSocial = emisorRazonSocial;
  }

  /**
   * Gets the emisor RFC.
   *
   * @return the emisor RFC
   */
  public String getEmisorRFC(){
    return emisorRFC;
  }
  
  /**
   * Sets the emisor RFC.
   *
   * @param emisorRFC the new emisor RFC
   */
  public void setEmisorRFC(String emisorRFC){
    this.emisorRFC = emisorRFC;
  }

  /**
   * Gets the emisor calle numero.
   *
   * @return the emisor calle numero
   */
  public String getEmisorCalleNumero(){
    return emisorCalleNumero;
  }
  
  /**
   * Sets the emisor calle numero.
   *
   * @param emisorCalleNumero the new emisor calle numero
   */
  public void setEmisorCalleNumero(String emisorCalleNumero){
    this.emisorCalleNumero = emisorCalleNumero;
  }

  /**
   * Gets the emisor ciudad.
   *
   * @return the emisor ciudad
   */
  public String getEmisorCiudad(){
    return emisorCiudad;
  }
  
  /**
   * Sets the emisor ciudad.
   *
   * @param emisorCiudad the new emisor ciudad
   */
  public void setEmisorCiudad(String emisorCiudad){
    this.emisorCiudad = emisorCiudad;
  }

  /**
   * Gets the emisor colonia.
   *
   * @return the emisor colonia
   */
  public String getEmisorColonia(){
    return emisorColonia;
  }
  
  /**
   * Sets the emisor colonia.
   *
   * @param emisorColonia the new emisor colonia
   */
  public void setEmisorColonia(String emisorColonia){
    this.emisorColonia = emisorColonia;
  }

  /**
   * Gets the emisor delegacion.
   *
   * @return the emisor delegacion
   */
  public String getEmisorDelegacion(){
    return emisorDelegacion;
  }
  
  /**
   * Sets the emisor delegacion.
   *
   * @param emisorDelegacion the new emisor delegacion
   */
  public void setEmisorDelegacion(String emisorDelegacion){
    this.emisorDelegacion = emisorDelegacion;
  }

  /**
   * Gets the emisor estado.
   *
   * @return the emisor estado
   */
  public String getEmisorEstado(){
    return emisorEstado;
  }
  
  /**
   * Sets the emisor estado.
   *
   * @param emisorEstado the new emisor estado
   */
  public void setEmisorEstado(String emisorEstado){
    this.emisorEstado = emisorEstado;
  }

  /**
   * Gets the emisor CP.
   *
   * @return the emisor CP
   */
  public String getEmisorCP(){
    return emisorCP;
  }
  
  /**
   * Sets the emisor CP.
   *
   * @param emisorCP the new emisor CP
   */
  public void setEmisorCP(String emisorCP){
    this.emisorCP = emisorCP;
  }

  /**
   * Gets the emisor pais.
   *
   * @return the emisor pais
   */
  public String getEmisorPais(){
    return emisorPais;
  }
  
  /**
   * Sets the emisor pais.
   *
   * @param emisorPais the new emisor pais
   */
  public void setEmisorPais(String emisorPais){
    this.emisorPais = emisorPais;
  }

  /**
   * Gets the receptor razon social.
   *
   * @return the receptor razon social
   */
  public String getReceptorRazonSocial(){
    return receptorRazonSocial;
  }
  
  /**
   * Sets the receptor razon social.
   *
   * @param receptorRazonSocial the new receptor razon social
   */
  public void setReceptorRazonSocial(String receptorRazonSocial){
    this.receptorRazonSocial = receptorRazonSocial;
  }

  /**
   * Gets the receptor RFC.
   *
   * @return the receptor RFC
   */
  public String getReceptorRFC(){
    return receptorRFC;
  }
  
  /**
   * Sets the receptor RFC.
   *
   * @param receptorRFC the new receptor RFC
   */
  public void setReceptorRFC(String receptorRFC){
    this.receptorRFC = receptorRFC;
  }

  /**
   * Gets the receptor calle numero.
   *
   * @return the receptor calle numero
   */
  public String getReceptorCalleNumero(){
    return receptorCalleNumero;
  }
  
  /**
   * Sets the receptor calle numero.
   *
   * @param receptorCalleNumero the new receptor calle numero
   */
  public void setReceptorCalleNumero(String receptorCalleNumero){
    this.receptorCalleNumero = receptorCalleNumero;
  }

  /**
   * Gets the receptor ciudad.
   *
   * @return the receptor ciudad
   */
  public String getReceptorCiudad(){
    return receptorCiudad;
  }
  
  /**
   * Sets the receptor ciudad.
   *
   * @param receptorCiudad the new receptor ciudad
   */
  public void setReceptorCiudad(String receptorCiudad){
    this.receptorCiudad = receptorCiudad;
  }

  /**
   * Gets the receptor colonia.
   *
   * @return the receptor colonia
   */
  public String getReceptorColonia(){
    return receptorColonia;
  }
  
  /**
   * Sets the receptor colonia.
   *
   * @param receptorColonia the new receptor colonia
   */
  public void setReceptorColonia(String receptorColonia){
    this.receptorColonia = receptorColonia;
  }

  /**
   * Gets the receptor delegacion.
   *
   * @return the receptor delegacion
   */
  public String getReceptorDelegacion(){
    return receptorDelegacion;
  }
  
  /**
   * Sets the receptor delegacion.
   *
   * @param receptorDelegacion the new receptor delegacion
   */
  public void setReceptorDelegacion(String receptorDelegacion){
    this.receptorDelegacion = receptorDelegacion;
  }

  /**
   * Gets the receptor estado.
   *
   * @return the receptor estado
   */
  public String getReceptorEstado(){
    return receptorEstado;
  }
  
  /**
   * Sets the receptor estado.
   *
   * @param receptorEstado the new receptor estado
   */
  public void setReceptorEstado(String receptorEstado){
    this.receptorEstado = receptorEstado;
  }

  /**
   * Gets the receptor CP.
   *
   * @return the receptor CP
   */
  public String getReceptorCP(){
    return receptorCP;
  }
  
  /**
   * Sets the receptor CP.
   *
   * @param receptorCP the new receptor CP
   */
  public void setReceptorCP(String receptorCP){
    this.receptorCP = receptorCP;
  }

  /**
   * Gets the receptor pais.
   *
   * @return the receptor pais
   */
  public String getReceptorPais(){
    return receptorPais;
  }
  
  /**
   * Sets the receptor pais.
   *
   * @param receptorPais the new receptor pais
   */
  public void setReceptorPais(String receptorPais){
    this.receptorPais = receptorPais;
  }

  /**
   * Gets the cantidad.
   *
   * @return the cantidad
   */
  public Long getCantidad(){
    return cantidad;
  }
  
  /**
   * Sets the cantidad.
   *
   * @param cantidad the new cantidad
   */
  public void setCantidad(Long cantidad){
    this.cantidad = cantidad;
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
   * Gets the precio unitario.
   *
   * @return the precio unitario
   */
  public Double getPrecioUnitario(){
    return precioUnitario;
  }
  
  /**
   * Sets the precio unitario.
   *
   * @param precioUnitario the new precio unitario
   */
  public void setPrecioUnitario(Double precioUnitario){
    this.precioUnitario = precioUnitario;
  }

  /**
   * Gets the importe.
   *
   * @return the importe
   */
  public Double getImporte(){
    return importe;
  }
  
  /**
   * Sets the importe.
   *
   * @param importe the new importe
   */
  public void setImporte(Double importe){
    this.importe = importe;
  }

  /**
   * Gets the subtotal.
   *
   * @return the subtotal
   */
  public Double getSubtotal(){
    return subtotal;
  }
  
  /**
   * Sets the subtotal.
   *
   * @param subtotal the new subtotal
   */
  public void setSubtotal(Double subtotal){
    this.subtotal = subtotal;
  }

  /**
   * Gets the iva.
   *
   * @return the iva
   */
  public Double getIva(){
    return iva;
  }
  
  /**
   * Sets the iva.
   *
   * @param iva the new iva
   */
  public void setIva(Double iva){
    this.iva = iva;
  }

  /**
   * Gets the total.
   *
   * @return the total
   */
  public Double getTotal(){
    return total;
  }
  
  /**
   * Sets the total.
   *
   * @param total the new total
   */
  public void setTotal(Double total){
    this.total = total;
  }

  /**
   *     linked status getter.
   *
   * @return the disabled
   */
  public Boolean getDisabled(){
    return disabled;
  }

  /**
   * Gets the cfd disabled.
   *
   * @return the cfd disabled
   */
  public Boolean getcfdDisabled(){
    return cfdDisabled;
  }

  /**
   *     Service setters/getters.
   *
   * @return the cfdmaster service
   */
  public CfdmasterService getCfdmasterService(){
    return cfdmasterService;
  }

  /**
   * Sets the cfdmaster service.
   *
   * @param cfdmasterService the new cfdmaster service
   */
  public void setCfdmasterService(CfdmasterService cfdmasterService){
    this.cfdmasterService = cfdmasterService;
  }

  /**
   * Gets the cfddetails service.
   *
   * @return the cfddetails service
   */
  public CfddetailsService getCfddetailsService(){
    return cfddetailsService;
  }

  /**
   * Sets the cfddetails service.
   *
   * @param cfddetailsService the new cfddetails service
   */
  public void setCfddetailsService(CfddetailsService cfddetailsService){
    this.cfddetailsService = cfddetailsService;
  }
  
  /**
   *     Utility methods.
   */
  private void cleanFields(){
    noCertificado = StringUtils.EMPTY;
    folioInicial = null;
    folioFinal = null;
    serie = StringUtils.EMPTY;
    folio = null;
    tipoComprobante = StringUtils.EMPTY;
    formaDePago = StringUtils.EMPTY;
    fecha = null;
    emisorRazonSocial = StringUtils.EMPTY;
    emisorRFC = StringUtils.EMPTY;
    emisorCalleNumero = StringUtils.EMPTY;
    emisorCiudad = StringUtils.EMPTY;
    emisorColonia = StringUtils.EMPTY;
    emisorDelegacion = StringUtils.EMPTY;
    emisorEstado = StringUtils.EMPTY;
    emisorCP = StringUtils.EMPTY;
    emisorPais = StringUtils.EMPTY;
    receptorRazonSocial = StringUtils.EMPTY;
    receptorRFC = StringUtils.EMPTY;
    receptorCalleNumero = StringUtils.EMPTY;
    receptorCiudad = StringUtils.EMPTY;
    receptorColonia = StringUtils.EMPTY;
    receptorDelegacion = StringUtils.EMPTY;
    receptorEstado = StringUtils.EMPTY;
    receptorCP = StringUtils.EMPTY;
    receptorPais = StringUtils.EMPTY;
    cantidad = null;
    concepto = StringUtils.EMPTY;
    precioUnitario = null;
    importe = null;
    subtotal = null;
    iva = null;
    total = null;
  }

  /**
   * Clear conceptos.
   */
  public void clearConceptos(){
    cantidad = null;
    concepto = StringUtils.EMPTY;
    precioUnitario = null;
    importe = null;
  }
  
  /**
   *     linked commands for buttons.
   *
   * @param event the event
   */
  public void cantidadPrecioChanged(AjaxBehaviorEvent event){
    if(getCantidad() == null || getPrecioUnitario() == null){
      importe = null;
    }else{
      importe = getCantidad() * getPrecioUnitario();
    }
  }

  /**
   * New comm.
   */
  public void newComm(){
    this.disabled = Boolean.FALSE;

    Cfdmaster cfdmaster = cfdmasterService.findFirst();
    if(cfdmaster != null){
      cfdDisabled = Boolean.TRUE;
      displayInfoMessage("Ya hay un registro");

      cfdmaster = cfdmasterService.findLast();
      if(cfdmaster != null){
        setFolio(cfdmaster.getFolio() + 1);
        setNoCertificado(cfdmaster.getNocertif());
        setSerie(cfdmaster.getSerie());
        setFolioInicial(cfdmaster.getFolinicio());
        setFolioFinal(cfdmaster.getFolfinal());
      }

      if(defaultNumIfNull(getFolio(),0L) > defaultNumIfNull(getFolioFinal(),0L)){
        displayErrorMessage("Excedes el limite de facturas");
        cfdDisabled = Boolean.TRUE;
        disabled = Boolean.TRUE;
      }else{
        cfdDisabled = Boolean.TRUE;
        disabled = Boolean.FALSE;
      }
    }else{
      cfdDisabled = Boolean.FALSE;
      disabled = Boolean.FALSE;
    }

  }

  /**
   * Adds the record.
   */
  public void addRecord(){
    System.out.println("Adding record");

    if(isBlank(getCantidad()) || StringUtils.isBlank(getConcepto())
    || isBlank(getPrecioUnitario()) || isBlank(getImporte())){
      displayErrorMessage("Falta llenar algun campo");
    }else{
      getCfddetailsService().save(new Cfddetails(getCantidad(), getConcepto(),
                                  new BigDecimal(getPrecioUnitario()),
                                  new BigDecimal(getImporte()), getSerie(),
                                  getFolio(), getIdSectorForCurrentUser()));
    }

    BigDecimal importeTotal = new BigDecimal(0);
    for (Cfddetails detail :
           getCfddetailsService()
            .findBySerieAndFolioAndIdSector(getSerie(),
                                            getFolio(),
                                            getIdSectorForCurrentUser())){
      System.out.println(detail);
      importeTotal = importeTotal.add(detail.getImporte());
    }

    subtotal = importeTotal.doubleValue();
    BigDecimal totalIva = importeTotal.multiply(new BigDecimal(0.16));
    iva = totalIva.doubleValue();
    total = importeTotal.add(totalIva).doubleValue();
    clearConceptos();
  }

  /**
   * Save.
   */
  public void save(){
    System.out.println("Guardando factura: "+getSubtotal());
    if (getTotal() != null && getSubtotal() != null){

      Cfdmaster master = new Cfdmaster();
      master.setSerie(getSerie());
      master.setFechacap(new Date());
      master.setFolio(getFolio());
      master.setTipcomp(getTipoComprobante());
      master.setFormapago(getFormaDePago());
      master.setFecha(getFecha());
      master.setHora(new SimpleDateFormat("HH:mm:ss").format(new Date()));
      master.setNomemi(getEmisorRazonSocial());
      master.setRfcemi(getEmisorRFC());
      master.setCallenumemi(getEmisorCalleNumero());
      master.setCiudademi(getEmisorCiudad());
      master.setColoniaemi(getEmisorColonia());
      master.setDelegemi(getEmisorDelegacion());
      master.setEstadoemi(getEmisorEstado());
      master.setCpemi(getEmisorCP());
      master.setPaisemi(getEmisorPais());
      master.setNomrec(getReceptorRazonSocial());
      master.setRfcrec(getReceptorRFC());
      master.setCallenumrec(getReceptorCalleNumero());
      master.setCiudadrec(getReceptorCiudad());
      master.setColoniarec(getReceptorColonia());
      master.setDelegrec(getReceptorDelegacion());
      master.setEstadorec(getReceptorEstado());
      master.setCprec(getReceptorCP());
      master.setPaisrec(getReceptorPais());
      master.setSubtotal(new BigDecimal(getSubtotal()));
      master.setIva(new BigDecimal(getIva()));
      master.setTotal(new BigDecimal(getTotal()));
      master.setNocertif(getNoCertificado());
      master.setFolinicio(getFolioInicial());
      master.setFolfinal(getFolioFinal());
      master.setIdsector(getIdSectorForCurrentUser());


      // Double total = getTotal();
      // if(total != null){
      String [] num = total.toString().split("\\.");
      String letNum = NumberToLetterConverter.convertNumberToLetter(num[0]);
      master.setTotletra("(***" + letNum + " " + truncate(num[1], 2) + "/100 M.N." + "***)");
      cfdmasterService.save(master);
      if(!isBlank(getCantidad()) && !StringUtils.isBlank(getConcepto())
         && !isBlank(getPrecioUnitario()) && !isBlank(getImporte())){

        Cfddetails detail = new Cfddetails(getCantidad(), getConcepto(),
                                          new BigDecimal(getPrecioUnitario()),
                                          new BigDecimal(getImporte()),
                                          null, null, getIdSectorForCurrentUser());
        cfddetailsService.save(detail);
      }

      displayInfoMessage("Sus datos han sido guardados.");
      cleanFields();
      cfdDisabled = Boolean.TRUE;
      disabled = Boolean.TRUE;
    }else{
      displayErrorMessage("No hay informacion para guardar");
    }
  }

  /**
   * Truncate.
   *
   * @param str the str
   * @param length the length
   * @return the string
   */
  public String truncate(String str, int length){
    return str.length() > length ? str.substring(0, length) : str;
  }

  /**
   * Checks if is blank.
   *
   * @param num the num
   * @return true, if is blank
   */
  public boolean isBlank(Number num){
    return num == null || num.intValue() == 0;
  }

  /**
   * Default num if null.
   *
   * @param val the val
   * @param def the def
   * @return the long
   */
  public Long defaultNumIfNull(Long val, Long def){
    return val == null ? def : val;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString(){
    return new StringBuffer("EmisionFacturaElectronicaMB {")
        .append("noCertificado: ")
        .append(noCertificado)
        .append(", folioInicial: ")
        .append(folioInicial)
        .append(", folioFinal: ")
        .append(folioFinal)
        .append(", serie: ")
        .append(serie)
        .append(", folio: ")
        .append(folio)
        .append(", tipoComprobante: ")
        .append(tipoComprobante)
        .append(", formaDePago: ")
        .append(formaDePago)
        .append(", fecha: ")
        .append(fecha)
        .append(", emisorRazonSocial: ")
        .append(emisorRazonSocial)
        .append(", emisorRFC: ")
        .append(emisorRFC)
        .append(", emisorCalleNumero: ")
        .append(emisorCalleNumero)
        .append(", emisorCiudad: ")
        .append(emisorCiudad)
        .append(", emisorColonia: ")
        .append(emisorColonia)
        .append(", emisorDelegacion: ")
        .append(emisorDelegacion)
        .append(", emisorEstado: ")
        .append(emisorEstado)
        .append(", emisorCP: ")
        .append(emisorCP)
        .append(", emisorPais: ")
        .append(emisorPais)
        .append(", rRazonSocial: ")
        .append(receptorRazonSocial)
        .append(", rRFC: ")
        .append(receptorRFC)
        .append(", rCalleNumero: ")
        .append(receptorCalleNumero)
        .append(", rCiudad: ")
        .append(receptorCiudad)
        .append(", rColonia: ")
        .append(receptorColonia)
        .append(", rDelegacion: ")
        .append(receptorDelegacion)
        .append(", rEstado: ")
        .append(receptorEstado)
        .append(", rCP: ")
        .append(receptorCP)
        .append(", rPais: ")
        .append(receptorPais)
        .append(", cantidad: ")
        .append(cantidad)
        .append(", concepto: ")
        .append(concepto)
        .append(", precioUnitario: ")
        .append(precioUnitario)
        .append(", importe: ")
        .append(importe)
        .append(", subtotal: ")
        .append(subtotal)
        .append(", iva: ")
        .append(iva)
        .append(", total: ")
        .append(total)
        .append("}")
        .toString();
  }

}
