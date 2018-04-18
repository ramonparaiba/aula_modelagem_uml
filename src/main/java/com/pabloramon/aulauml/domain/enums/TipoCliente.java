package com.pabloramon.aulauml.domain.enums;

public enum TipoCliente {
	
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
//	Todo o código abaixo é para ter um controle refinado dos tipos enumerados.

	private int cod;
	private String descricao;
//	Construtor de tipo enumerado é Private
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
