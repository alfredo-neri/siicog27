package com.gem.sistema.business.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TC_TRAMITES database table.
 * 
 */
@Entity
@Table(name = "TC_TRAMITES")
@NamedQuery(name = "TcTramite.findAll", query = "SELECT t FROM TcTramite t")
public class TcTramite extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 2828824940943367484L;
	private String tramite;

	public TcTramite() {
	}

	public String getTramite() {
		return this.tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	@Override
	public int hashCode() {
		return new Long(this.getId() + TcTramite.serialVersionUID).intValue();
	}
}