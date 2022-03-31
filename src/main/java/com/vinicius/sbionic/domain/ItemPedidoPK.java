package com.vinicius.sbionic.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// anotação para indicar que essa classe será um subtipo 
// (geralmente usado em classes para chaves compostas)
@Embeddable
// classes PK precisam ter serializable
public class ItemPedidoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Essa classe recebe duas chaves estrangeiras para fazer o intermeio entre as duas classes
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(pedido, produto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(produto, other.produto);
	}
	
	
	
}
