package br.com.spdata.domain.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.spdata.domain.dto.DescontosDto;

@Service
public class SalarioService {

	public BigDecimal calculaSalarioLiquido(BigDecimal salarioBruto, DescontosDto descontos) {
		
		BigDecimal salarioLiquido = new BigDecimal("0.00").add(salarioBruto);
		
		BigDecimal inss = new BigDecimal("0.00");
		
		salarioLiquido = salarioLiquido.subtract(inss);
		
		salarioLiquido = subtrairDescontos(descontos, salarioLiquido);
		return salarioLiquido;
	}

	private BigDecimal subtrairDescontos(DescontosDto descontos, BigDecimal salarioLiquido) {

		if (descontos.getAssistenciaMedica() != null) {
			salarioLiquido = salarioLiquido.subtract(descontos.getAssistenciaMedica());
		}

		if (descontos.getPrevidenciaPrivada() != null) {
			salarioLiquido = salarioLiquido.subtract(descontos.getPrevidenciaPrivada());
		}

		if (descontos.getValeAlimentacao() != null) {
			salarioLiquido = salarioLiquido.subtract(descontos.getValeAlimentacao());
		}

		if (descontos.getValeTransporte() != null) {
			salarioLiquido = salarioLiquido.subtract(descontos.getValeTransporte());
		}
		return salarioLiquido;
	}
}
