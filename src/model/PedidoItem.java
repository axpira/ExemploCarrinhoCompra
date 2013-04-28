package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PedidoItem implements PersistentEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private double valor;
	private int quantidade;
	
	@ManyToOne
	@JoinColumn(name="produtoId")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name="pedidoId")
	private Pedido pedido;
	
	public PedidoItem() {
	}

	public PedidoItem(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = produto.getValor();
	}

	public PedidoItem(Produto produto, int quantidade, double valor) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public boolean isNew() {
		return (id==null);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public double getTotal() {
		return this.quantidade * this.valor;
	}

	public void adicionarQuantidade(int qtd) {
		this.quantidade += qtd;
	}
}
