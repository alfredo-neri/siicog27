package com.gem.sistema.business.dto;

public class TcValoresDTO {
	private Object valor;
	private Integer idRow;
	private String eiqueta;

	public TcValoresDTO() {
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public Integer getIdRow() {
		return idRow;
	}

	public void setIdRow(Integer idRow) {
		this.idRow = idRow;
	}

	public String getEiqueta() {
		return eiqueta;
	}

	public void setEiqueta(String eiqueta) {
		this.eiqueta = eiqueta;
	}

	public TcValoresDTO(Object valor, Integer idRow, String eiqueta) {
		super();
		this.valor = valor;
		this.idRow = idRow;
		this.eiqueta = eiqueta;
	}

	@Override
	public String toString() {
		return "TcValoresDTO [valor=" + valor + ", idRow=" + idRow + ", eiqueta=" + eiqueta + "]";
	}

	

}
