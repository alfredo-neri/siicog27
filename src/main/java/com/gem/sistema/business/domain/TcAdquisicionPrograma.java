package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "TC_ADQUISICION_PROGRAMAS")
@NamedQuery(name = "TcAdquisicionPrograma.findAll", query = "SELECT a FROM TcAdquisicionPrograma a")
public class TcAdquisicionPrograma extends AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7130959665173093302L;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUENTA", nullable = true)
	private Cuenta cuenta;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CATDEP", nullable = true)
	private Catdep catdep;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MUNINEP", nullable = true)
	private Muninep muninep;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FUENTEF", nullable = true)
	private FuentefGobierno fuentefGobierno;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_NATGAS", nullable = true)
	private Natgas natgas;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ADQUISICION", nullable = true)
	private TcAdquisicion adquisicion;

	private BigDecimal monto;

	public TcAdquisicionPrograma() {
	}

	public TcAdquisicion getAdquisicion() {
		return adquisicion;
	}

	public void setAdquisicion(TcAdquisicion adquisicion) {
		this.adquisicion = adquisicion;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Catdep getCatdep() {
		return catdep;
	}

	public void setCatdep(Catdep catdep) {
		this.catdep = catdep;
	}

	public Muninep getMuninep() {
		return muninep;
	}

	public void setMuninep(Muninep muninep) {
		this.muninep = muninep;
	}

	public FuentefGobierno getFuentefGobierno() {
		return fuentefGobierno;
	}

	public void setFuentefGobierno(FuentefGobierno fuentefGobierno) {
		this.fuentefGobierno = fuentefGobierno;
	}

	public Natgas getNatgas() {
		return natgas;
	}

	public void setNatgas(Natgas natgas) {
		this.natgas = natgas;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adquisicion == null) ? 0 : adquisicion.hashCode());
		result = prime * result + ((catdep == null) ? 0 : catdep.hashCode());
		result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
		result = prime * result + ((fuentefGobierno == null) ? 0 : fuentefGobierno.hashCode());
		result = prime * result + ((monto == null) ? 0 : monto.hashCode());
		result = prime * result + ((muninep == null) ? 0 : muninep.hashCode());
		result = prime * result + ((natgas == null) ? 0 : natgas.hashCode());
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
		TcAdquisicionPrograma other = (TcAdquisicionPrograma) obj;
		if (adquisicion == null) {
			if (other.adquisicion != null)
				return false;
		} else if (!adquisicion.equals(other.adquisicion))
			return false;
		if (catdep == null) {
			if (other.catdep != null)
				return false;
		} else if (!catdep.equals(other.catdep))
			return false;
		if (cuenta == null) {
			if (other.cuenta != null)
				return false;
		} else if (!cuenta.equals(other.cuenta))
			return false;
		if (fuentefGobierno == null) {
			if (other.fuentefGobierno != null)
				return false;
		} else if (!fuentefGobierno.equals(other.fuentefGobierno))
			return false;
		if (monto == null) {
			if (other.monto != null)
				return false;
		} else if (!monto.equals(other.monto))
			return false;
		if (muninep == null) {
			if (other.muninep != null)
				return false;
		} else if (!muninep.equals(other.muninep))
			return false;
		if (natgas == null) {
			if (other.natgas != null)
				return false;
		} else if (!natgas.equals(other.natgas))
			return false;
		return true;
	}

}