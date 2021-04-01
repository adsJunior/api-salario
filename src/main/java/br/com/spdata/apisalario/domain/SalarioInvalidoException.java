package br.com.spdata.apisalario.domain;

import java.io.Serializable;

public class SalarioInvalidoException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -8236805079147043933L;

	public SalarioInvalidoException() {
		super("O Salário informado não pode ser utilizado.");
	}
	
	public SalarioInvalidoException(String message) {
		super(message);
	}
}
