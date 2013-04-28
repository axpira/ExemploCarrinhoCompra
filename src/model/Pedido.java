package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements PersistentEntity<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="pedido")
	private List<PedidoItem> itens;

	@ManyToOne
	@JoinColumn(name="usuarioId")
	private Usuario usuario;
	
	public Pedido() {
		this.itens = new ArrayList<PedidoItem>();
	}
	
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public boolean isNew() {
		return (id==null);
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void adicionarItem(Produto p) {
		//if (this.itens.)
		for (PedidoItem item : this.itens) {
			if (item.getProduto().equals(p)) {
				item.adicionarQuantidade(1);
				return;
			}
		}
		this.itens.add(new PedidoItem(p, 1));
	}

	public double getTotal() {
		double total = 0;
		for (PedidoItem pi : itens) {
			total += pi.getTotal();
		}
		return total;
	}
}
