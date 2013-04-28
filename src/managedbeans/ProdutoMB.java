package managedbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Produto;

import org.primefaces.model.LazyDataModel;

import dao.ProdutoDAO;

@ManagedBean
@ViewScoped
public class ProdutoMB {
	private LazyDataModel<Produto> lazyProdutosDataModel;

	private Produto produtoSelecionado;
	
	@EJB
	private ProdutoDAO produtoDAO;

	@ManagedProperty(value="#{carrinhoMB}")
	private CarrinhoMB carrinhoMB;

	@PostConstruct
	private void init(){
		lazyProdutosDataModel = new LazyProdutosDataModel(produtoDAO);
	}
	
	
	public LazyDataModel<Produto> getLazyProdutosDataModel() {
		return lazyProdutosDataModel;
	}

	public void setLazyProdutosDataModel(LazyDataModel<Produto> lazyProdutosDataModel) {
		this.lazyProdutosDataModel = lazyProdutosDataModel;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
	public String adicionarNoCarrinho() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
			new FacesMessage(FacesMessage.SEVERITY_INFO, 
					"ProdutoSelecionado: " + produtoSelecionado.getNome() + " " + carrinhoMB, 
					null
					)
		);
		carrinhoMB.adicionarProduto(produtoSelecionado);
		return null;
	}

	public CarrinhoMB getCarrinhoMB() {
		return carrinhoMB;
	}


	public void setCarrinhoMB(CarrinhoMB carrinhoMB) {
		this.carrinhoMB = carrinhoMB;
	}

	public String getProdutoPesquisar() {
		return LazyProdutosDataModel.FILTRO;
	}


	public void setProdutoPesquisar(String produtoPesquisar) {
		LazyProdutosDataModel.FILTRO = produtoPesquisar;
	}

	public String listarProdutosSemFiltro() {
		LazyProdutosDataModel.FILTRO = "";
		return "produtosListar";
	}


}
