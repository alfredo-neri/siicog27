package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TC_ADQUISICION_GIRO_SUBGIROS")
@NamedQuery(name = "TcAdquisicionGiroSubgiro.findAll", query = "SELECT t FROM TcAdquisicionGiroSubgiro t")
public class TcAdquisicionGiroSubgiro extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 3369796226040187176L;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SUBGIRO", nullable = true)
	private TcSubgiro subgiro;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ADQUISICION", nullable = true)
	private TcAdquisicion adquisicion;

	public TcAdquisicionGiroSubgiro() {

	}

	public TcAdquisicionGiroSubgiro(TcSubgiro subgiro, TcAdquisicion adquisicion) {
		this.subgiro = subgiro;
		this.adquisicion = adquisicion;
	}

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (adquisicion == null) {
			errors.add("Es nesesario seleccionar una adquisicion");
		}
		if (subgiro == null) {
			errors.add("Es nesesario seleccionar una adquisicion");
		}
		return errors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adquisicion == null) ? 0 : adquisicion.hashCode());
		result = prime * result + ((subgiro == null) ? 0 : subgiro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TcAdquisicionGiroSubgiro other = (TcAdquisicionGiroSubgiro) obj;
		if (adquisicion == null) {
			if (other.adquisicion != null)
				return false;
		} else if (!adquisicion.equals(other.adquisicion))
			return false;
		if (subgiro == null) {
			if (other.subgiro != null)
				return false;
		} else if (!subgiro.equals(other.subgiro))
			return false;
		return true;
	}

	public TcSubgiro getSubgiro() {
		return subgiro;
	}

	public void setSubgiro(TcSubgiro subgiro) {
		this.subgiro = subgiro;
	}

	public TcAdquisicion getAdquisicion() {
		return adquisicion;
	}

	public void setAdquisicion(TcAdquisicion adquisicion) {
		this.adquisicion = adquisicion;
	}

	@Override
	public String toString() {
		return "TcAdquisicionGiroSubgiro [subgiro=" + subgiro + ", adquisicion=" + adquisicion + "]";
	}

}