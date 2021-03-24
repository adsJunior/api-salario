package br.com.spdata.apisalario.api.model;

import java.math.BigDecimal;

public class DescontosModel {

	private BigDecimal valeTransporte;
	
	private BigDecimal valeAlimentacao;
	
	private BigDecimal assistenciaMedica;
	
	private BigDecimal previdenciaPrivada;

	public BigDecimal getValeTransporte() {
		return valeTransporte;
	}

	public void setValeTransporte(BigDecimal valeTransporte) {
		this.valeTransporte = valeTransporte;
	}

	public BigDecimal getValeAlimentacao() {
		return valeAlimentacao;
	}

	public void setValeAlimentacao(BigDecimal valeAlimentacao) {
		this.valeAlimentacao = valeAlimentacao;
	}

	public BigDecimal getAssistenciaMedica() {
		return assistenciaMedica;
	}

	public void setAssistenciaMedica(BigDecimal assistenciaMedica) {
		this.assistenciaMedica = assistenciaMedica;
	}

	public BigDecimal getPrevidenciaPrivada() {
		return previdenciaPrivada;
	}

	public void setPrevidenciaPrivada(BigDecimal previdenciaPrivada) {
		this.previdenciaPrivada = previdenciaPrivada;
	}
}
