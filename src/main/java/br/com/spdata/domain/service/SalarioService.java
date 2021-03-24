package br.com.spdata.domain.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.spdata.domain.dto.DescontosDto;


@Service
public class SalarioService {

	public BigDecimal calculaSalarioLiquido(BigDecimal salarioBruto, DescontosDto descontos) {
		
		return new BigDecimal("0.00");
	}
}
