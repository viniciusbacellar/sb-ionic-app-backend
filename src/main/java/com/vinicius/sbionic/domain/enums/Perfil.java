package com.vinicius.sbionic.domain.enums;

public enum Perfil {

	// Definir cada enum com um numero inteiro para manter a segurança no momento de inserir
	// um novo enum no banco de dados
	// Prefixo ROLE é exigência do spring security
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	// Metodo que mantém a segurança no momento de recuperar um dado enum específico
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido " + cod);
	}
	
	
	
}
