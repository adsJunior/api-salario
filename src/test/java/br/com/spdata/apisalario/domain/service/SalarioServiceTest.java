package br.com.spdata.apisalario.domain.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.spdata.apisalario.api.model.ParametrosModel;
import br.com.spdata.apisalario.domain.exception.SalarioInvalidoException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarioServiceTest {
	
	@Autowired
	private SalarioService salarioService;

	@Test
	void calculaSalarioLiquidoSucessoTest() {
		
		//Arrange
		BigDecimal salarioBruto = new BigDecimal("3000.00");
		
		ParametrosModel parametros = new ParametrosModel();
		parametros.setAssistenciaMedica(new BigDecimal("20.00"));
		parametros.setValeTransporte(new BigDecimal("180.00"));
		
		//Act
		var salarioLiquido = salarioService.calculaSalarioLiquido(salarioBruto, parametros);
		
		//Assert
		Assertions.assertEquals(new BigDecimal("2461.20"), salarioLiquido);
	}
	
	@Test
	void calculaSalarioLiquidoErroTest() {
		
		//Arrange
		BigDecimal salarioBruto = new BigDecimal("-5000.00");
		ParametrosModel parametros = new ParametrosModel();
		
		//Act & Assert
		Assertions.assertThrows(SalarioInvalidoException.class, () -> 
			salarioService.calculaSalarioLiquido(salarioBruto, parametros));
	}
	
	@Test
	void obterDescontoInssDescontoMaximoTest() {
		
		//Arrange
		BigDecimal salario = new BigDecimal("7000.00");
		
		//Act
		BigDecimal valorDesconto = salarioService.obterDescontoInss(salario);
		
		//Assert
		Assertions.assertEquals(new BigDecimal("751.99"), valorDesconto);
	}
	
	@Test
	void obterDescontoImpostoDeRendaIsentoTest() {
		
		//Arrange
		BigDecimal salarioLiquido = new BigDecimal("1800.00");
		
		//Act
		BigDecimal valorDesconto = salarioService.obterDescontoImpostoDeRenda(salarioLiquido, 0);
		
		//Assert
		Assertions.assertEquals(new BigDecimal("0.00"), valorDesconto);
	}
	

	@Test
	void obterDescontoImpostoDeRendaAliquotaMaximaTest() {
		
		//Arrange
		BigDecimal salarioLiquido = new BigDecimal("6000.00");
		
		//Act
		BigDecimal valorDesconto = salarioService.obterDescontoImpostoDeRenda(salarioLiquido, 0);
		
		//Assert
		Assertions.assertEquals(new BigDecimal("780.64"), valorDesconto);
	}
	
	@Test
	void subtrairDescontosTest() {

		//Arrange
		BigDecimal salarioLiquido = new BigDecimal("5219.36");
		var parametros = new ParametrosModel();
		parametros.setPrevidenciaPrivada(new BigDecimal("420.00"));
		parametros.setValeAlimentacao(new BigDecimal("37.50"));

		// Act
		BigDecimal valorDesconto = salarioService.subtrairDescontos(parametros, salarioLiquido);

		// Assert
		Assertions.assertEquals(new BigDecimal("4761.86"), valorDesconto);
	}
}
