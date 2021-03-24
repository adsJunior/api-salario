package br.com.spdata.apisalario.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spdata.apisalario.api.model.DescontosModel;
import br.com.spdata.domain.dto.DescontosDto;
import br.com.spdata.domain.service.SalarioService;

@RestController
@RequestMapping("/salario")
public class SalarioController {

	@Autowired
	private SalarioService salarioService;
	
	@GetMapping("/{salarioBruto}")
	public ResponseEntity<BigDecimal> getSalarioLiquido(@PathVariable BigDecimal salarioBruto, 
			@RequestBody DescontosModel descontos) {
		BigDecimal salarioLiquido = salarioService.calculaSalarioLiquido(salarioBruto, 
				new DescontosDto(descontos));
		return ResponseEntity.ok(salarioLiquido);
	}
}
