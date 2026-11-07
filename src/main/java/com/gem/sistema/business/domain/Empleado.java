package com.gem.sistema.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TC_EMPLEADO")
@NamedQuery(name = "Empleado.findAll", query = "SELECT t FROM Empleado t")
public class Empleado extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    @Column(name = "ap_paterno")
    private String apPaterno;
    @Column(name = "ap_materno")
    private String apMaterno;
    private String rfc;
    private String curp;
    private String correo;
    @Column(name = "cuenta_bancaria")
    private String cuentaBancaria;
    private String banco;
    @Column(name = "clave_issemeyn")
    private String claveIssemym;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_egreso")
    private Date fechaEgreso;
    @OneToMany(mappedBy = "empleado")
    private List<Contrato> contratos;

    public Empleado() {
    }

    public Empleado(String nombre, String apPaterno, String apMaterno, String rfc, String curp, String correo, String cuentaBancaria, String banco, String claveIssemym, Date fechaIngreso, Date fechaEgreso) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.rfc = rfc;
        this.curp = curp;
        this.correo = correo;
        this.cuentaBancaria = cuentaBancaria;
        this.banco = banco;
        this.claveIssemym = claveIssemym;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getClaveIssemym() {
        return claveIssemym;
    }

    public void setClaveIssemym(String claveIssemym) {
        this.claveIssemym = claveIssemym;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
}
