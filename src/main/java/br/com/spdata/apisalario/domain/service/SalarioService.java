package br.com.spdata.apisalario.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import br.com.spdata.apisalario.api.model.ParametrosModel;
import br.com.spdata.apisalario.domain.model.AliquotaIrrfModel;

@Service
public class SalarioService {

	public BigDecimal calculaSalarioLiquido(BigDecimal salarioBruto, ParametrosModel parametros) {
		
		BigDecimal salarioLiquido = new BigDecimal("0.00").add(salarioBruto);
		
		BigDecimal inss = obterDescontoInss(salarioBruto);
		
		salarioLiquido = salarioLiquido.subtract(inss);
		
		BigDecimal irrf = obterDescontoImpostoDeRenda(salarioLiquido, parametros.getNumeroDependentes());
		
		salarioLiquido = salarioLiquido.subtract(irrf);
		
		salarioLiquido = subtrairDescontos(parametros, salarioLiquido);
		return salarioLiquido.setScale(2, RoundingMode.HALF_EVEN);
	}

	private BigDecimal obterDescontoInss(BigDecimal salarioBruto) {
		
		BigDecimal aliquotaFinal = new BigDecimal("6433.57");
		if(salarioBruto.compareTo(aliquotaFinal) > 0) {
			return new BigDecimal("751.99");
		}
		else {
			BigDecimal inss = new BigDecimal("0.00");
			Map<BigDecimal, BigDecimal> aliquotas = gerarAliquotasInss(aliquotaFinal);
			
			BigDecimal aliquotaAnterior = null;
			for(Entry<BigDecimal, BigDecimal> aliquota : aliquotas.entrySet()) {
				BigDecimal valorAliquotaInss = obterValorAliquotaInss(salarioBruto, aliquota, aliquotaAnterior);
				inss = inss.add(valorAliquotaInss);
				aliquotaAnterior = aliquota.getValue();
			}
			return inss.setScale(2, RoundingMode.HALF_EVEN);
		}
	}

	private Map<BigDecimal, BigDecimal> gerarAliquotasInss(BigDecimal aliquotaFinal) {
		
		Map<BigDecimal, BigDecimal> aliquotas = new TreeMap<>();
		aliquotas.put(new BigDecimal("0.075"), new BigDecimal("1100.00"));
		aliquotas.put(new BigDecimal("0.09"), new BigDecimal("2203.48"));
		aliquotas.put(new BigDecimal("0.12"), new BigDecimal("3305.22"));
		aliquotas.put(new BigDecimal("0.14"), aliquotaFinal);
		return aliquotas;
	}
	
	private BigDecimal obterValorAliquotaInss(BigDecimal salarioBruto, Entry<BigDecimal, BigDecimal> aliquota, 
			BigDecimal aliquotaAnterior) {
		
		if(aliquotaAnterior == null) {
			aliquotaAnterior = new BigDecimal("0.00");
		}
		if (aliquota.getValue().compareTo(salarioBruto) < 0) {
			return aliquota.getValue().subtract(aliquotaAnterior).multiply(aliquota.getKey());
		}
		else if(salarioBruto.compareTo(aliquotaAnterior) > 0) {
			return salarioBruto.subtract(aliquotaAnterior).multiply(aliquota.getKey());
		}
		return new BigDecimal("0.00");
	}
	
	private BigDecimal obterDescontoImpostoDeRenda(BigDecimal salarioLiquido, int numeroDependentes) {
		
		BigDecimal impostoDeRenda = new BigDecimal("0.00");
		BigDecimal deducaoDependentes = new BigDecimal("189.59").multiply(new BigDecimal(numeroDependentes));
		
		BigDecimal salarioBase = salarioLiquido.subtract(deducaoDependentes);
		
		if(salarioBase.compareTo(new BigDecimal("1903.98")) < 0) {
			return impostoDeRenda;
		}
		
		List<AliquotaIrrfModel> aliquotas = obterAliquotasIrrf();
		
		AliquotaIrrfModel aliquotaAnterior = null;
		
		for(var aliquota : aliquotas) {
			if(salarioBase.compareTo(aliquota.getLimiteAliquota()) < 0) {
				return calculaIrrf(salarioBase, aliquotaAnterior).setScale(2, RoundingMode.HALF_EVEN);
			}
			aliquotaAnterior = aliquota;
		}
		return calculaIrrf(salarioBase, aliquotaAnterior).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	private BigDecimal calculaIrrf(BigDecimal salarioBase, AliquotaIrrfModel aliquota) {
		
		if(aliquota == null) {
			return new BigDecimal("0.00");
		}
		return salarioBase.multiply(aliquota.getPercentualAliquota()).subtract(aliquota.getParcelaDedutivel());
	}
	
	private List<AliquotaIrrfModel> obterAliquotasIrrf() {
		
		List<AliquotaIrrfModel> aliquotas = new ArrayList<>();

		aliquotas.add(new AliquotaIrrfModel(new BigDecimal("1903.98"), new BigDecimal("0.075"), new BigDecimal("142.80")));
		aliquotas.add(new AliquotaIrrfModel(new BigDecimal("2826.65"), new BigDecimal("0.15"), new BigDecimal("354.80")));
		aliquotas.add(new AliquotaIrrfModel(new BigDecimal("3751.05"), new BigDecimal("0.225"), new BigDecimal("636.13")));
		aliquotas.add(new AliquotaIrrfModel(new BigDecimal("4664.68"), new BigDecimal("0.275"), new BigDecimal("869.36")));
		
		return aliquotas;
	}

	private BigDecimal subtrairDescontos(ParametrosModel parametros, BigDecimal salarioLiquido) {

		if (parametros.getAssistenciaMedica() != null) {
			salarioLiquido = salarioLiquido.subtract(parametros.getAssistenciaMedica());
		}

		if (parametros.getPrevidenciaPrivada() != null) {
			salarioLiquido = salarioLiquido.subtract(parametros.getPrevidenciaPrivada());
		}

		if (parametros.getValeAlimentacao() != null) {
			salarioLiquido = salarioLiquido.subtract(parametros.getValeAlimentacao());
		}

		if (parametros.getValeTransporte() != null) {
			salarioLiquido = salarioLiquido.subtract(parametros.getValeTransporte());
		}
		return salarioLiquido;
	}
}
