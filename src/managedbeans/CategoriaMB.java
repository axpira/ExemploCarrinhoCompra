package managedbeans;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.Categoria;
import dao.CategoriaDAO;

@ManagedBean
public class CategoriaMB {
	private Categoria categoria;
	private Collection<Categoria> categorias;
	
	
	@EJB
	private CategoriaDAO categoriaDAO;

	public CategoriaMB() {
		categoria = new Categoria();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String gravar() {

		FacesContext context = FacesContext.getCurrentInstance();
		try {
			categoriaDAO.save(categoria);
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, 
							"Cadastrado com sucesso", 
							"A categoria " + categoria.getNome() + " foi cadastrada com sucesso, codigo " + categoria.getId()
							)
			);
			categorias = null;
			categoria = new Categoria();
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"Erro ao criar a categoria", 
							"A categoria " + categoria.getNome() + " nao foi cadastrada com sucesso " + e.getCause().getLocalizedMessage()
							)
			);
		}
		return null;

	}

	public Collection<Categoria> getCategorias() {
		if (categorias==null) {
			categorias = categoriaDAO.findAll();
		}
		return categorias;
	}

	public void setCategorias(Collection<Categoria> categorias) {
		this.categorias = categorias;
	}

}
