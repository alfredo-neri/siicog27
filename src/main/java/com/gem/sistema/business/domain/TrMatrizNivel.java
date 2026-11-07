package com.gem.sistema.business.domain;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Alfredo
 *
 */
@Entity
@Table(name = "TR_MATRIZ_NIVEL")
public class TrMatrizNivel extends AbstractEntity implements Serializable {

	@ManyToOne
	@JoinColumn(name = "ID_MATRIZ")
	private TcMatriz matriz;

	@ManyToOne
	@JoinColumn(name = "ID_NIVEL")
	private TcNivel nivel;

	@JsonIgnore
	@OneToOne(mappedBy = "matrizNivel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TcFicha ficha;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TcMatriz getMatriz() {
		return matriz;
	}

	public void setMatriz(TcMatriz matriz) {
		this.matriz = matriz;
	}

	public TcNivel getNivel() {
		return nivel;
	}

	public void setNivel(TcNivel nivel) {
		this.nivel = nivel;
	}

	public TcFicha getFicha() {
		return ficha;
	}

	public void setFicha(TcFicha ficha) {
		this.ficha = ficha;
	}

}
