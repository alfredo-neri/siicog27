package com.gem.sistema.business.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.gem.sistema.annotation.IgnoredQuery;

/**
 * The persistent class for the TC_UNIDADES_MEDIDA database table.
 * <p>
 * Catalogo de unidades de medida de los articulos de una solicitud de
 * adquisicion. Sustituye a la lista que estaba fija en
 * {@code SolicitudesAdquisicionesMB} ("Kilos", "Litros", "Metros").
 */
@Entity
@Table(name = "TC_UNIDADES_MEDIDA")
@NamedQuery(name = "TcUnidadMedida.findAll", query = "SELECT t FROM TcUnidadMedida t")
public class TcUnidadMedida extends AbstractEntity implements Serializable {

	@IgnoredQuery
	private static final long serialVersionUID = -2148832069117441603L;

	/** Valor de ACTIVO para una unidad vigente */
	public static final Short ACTIVO = Short.valueOf((short) 1);
	/** Valor de ACTIVO para una unidad dada de baja */
	public static final Short INACTIVO = Short.valueOf((short) 0);

	/** Longitud maxima de la clave, igual que en la base */
	public static final int LONGITUD_CLAVE = 10;
	/** Longitud maxima de la descripcion, igual que en la base */
	public static final int LONGITUD_DESCRIPCION = 100;
	/** Longitud maxima de la abreviatura, igual que en la base */
	public static final int LONGITUD_ABREVIATURA = 20;

	/** Clave corta de negocio, no se repite */
	private String clave;

	/** Nombre de la unidad tal como se captura y se imprime */
	private String descripcion;

	/** Simbolo o abreviatura para columnas angostas y reportes */
	private String abreviatura;

	/** 1 = disponible para capturar, 0 = solo historico */
	private Short activo;

	public TcUnidadMedida() {
		this.activo = ACTIVO;
	}

	public List<String> isValid() {
		List<String> errors = new ArrayList<String>();
		if (clave == null || clave.trim().equals("")) {
			errors.add("La clave es requerida");
		} else if (clave.trim().length() > LONGITUD_CLAVE) {
			errors.add("La clave no puede tener mas de " + LONGITUD_CLAVE + " caracteres");
		}
		if (descripcion == null || descripcion.trim().equals("")) {
			errors.add("La descripción es requerida");
		} else if (descripcion.trim().length() > LONGITUD_DESCRIPCION) {
			errors.add("La descripción no puede tener mas de " + LONGITUD_DESCRIPCION + " caracteres");
		}
		if (abreviatura == null || abreviatura.trim().equals("")) {
			errors.add("La abreviatura es requerida");
		} else if (abreviatura.trim().length() > LONGITUD_ABREVIATURA) {
			errors.add("La abreviatura no puede tener mas de " + LONGITUD_ABREVIATURA + " caracteres");
		}
		return errors;
	}

	/**
	 * Normaliza clave y descripcion antes de grabar. La descripcion es la que
	 * valida el cargador de Excel y la que se copia al texto del articulo, por eso
	 * se guarda siempre en mayusculas y sin espacios de sobra.
	 */
	@PrePersist
	@PreUpdate
	public void normaliza() {
		if (clave != null) {
			clave = clave.trim().toUpperCase();
		}
		if (descripcion != null) {
			descripcion = descripcion.trim().toUpperCase();
		}
		if (abreviatura != null) {
			abreviatura = abreviatura.trim();
		}
		if (activo == null) {
			activo = ACTIVO;
		}
	}

	/** Etiqueta para los combos: "PZA - PIEZA" */
	public String getEtiqueta() {
		return (clave == null ? "" : clave) + " - " + (descripcion == null ? "" : descripcion);
	}

	/** true cuando la unidad esta vigente */
	public boolean isVigente() {
		return ACTIVO.equals(activo);
	}

	public String busquedaSimple() {
		return "" + clave + descripcion + abreviatura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((abreviatura == null) ? 0 : abreviatura.hashCode());
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
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
		TcUnidadMedida other = (TcUnidadMedida) obj;
		if (abreviatura == null) {
			if (other.abreviatura != null)
				return false;
		} else if (!abreviatura.equals(other.abreviatura))
			return false;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		return true;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public Short getActivo() {
		return activo;
	}

	public void setActivo(Short activo) {
		this.activo = activo;
	}
}
