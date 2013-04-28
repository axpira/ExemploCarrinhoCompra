package managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Pedido;
import model.Produto;

@ManagedBean
@SessionScoped
public class CarrinhoMB {
	
	private Pedido carrinho;
	
	public CarrinhoMB() {
		this.carrinho = new Pedido();
	}
	
	public void adicionarProduto(Produto produto) {
		this.carrinho.adicionarItem(produto);
	}	

	public Pedido getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Pedido carrinho) {
		this.carrinho = carrinho;
	}

	public String getQuantidadeItens() {
		return String.valueOf(carrinho.getItens().size());

	}
	
}
