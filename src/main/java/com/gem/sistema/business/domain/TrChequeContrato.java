package com.gem.sistema.business.domain;


import javax.persistence.*;



@Entity
@Table(name = "TR_CHEQUE_CONTRATO")
public class TrChequeContrato extends AbstractEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name = "ID_CHEQUE")
	private Long idChque;

	
	@Column(name = "ID_CONTRATO")
	private Long idContrato;


	public Long getIdChque() {
		return idChque;
	}


	public void setIdChque(Long idChque) {
		this.idChque = idChque;
	}


	public Long getIdContrato() {
		return idContrato;
	}


	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}


}
