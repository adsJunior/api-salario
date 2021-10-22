package br.com.andre.apisalario.domain.model;

import java.math.BigDecimal;

public class AliquotaIrrfModel {

	private BigDecimal limiteAliquota;
	
	private BigDecimal percentualAliquota;
	
	private BigDecimal parcelaDedutivel;
	
	public AliquotaIrrfModel() { 
		super();
	}
	
	public AliquotaIrrfModel(BigDecimal limiteAliquota, BigDecimal percentualAliquota, BigDecimal parcelaDedutivel) {
		this.limiteAliquota = limiteAliquota;
		this.parcelaDedutivel = parcelaDedutivel;
		this.percentualAliquota = percentualAliquota;
	}

	public BigDecimal getLimiteAliquota() {
		return limiteAliquota;
	}

	public void setLimiteAliquota(BigDecimal limiteAliquota) {
		this.limiteAliquota = limiteAliquota;
	}

	public BigDecimal getPercentualAliquota() {
		return percentualAliquota;
	}

	public void setPercentualAliquota(BigDecimal percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}

	public BigDecimal getParcelaDedutivel() {
		return parcelaDedutivel;
	}

	public void setParcelaDedutivel(BigDecimal parcelaDedutivel) {
		this.parcelaDedutivel = parcelaDedutivel;
	}
}
