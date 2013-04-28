package managedbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Categoria;
import model.Produto;
import model.Usuario;
import dao.CategoriaDAO;
import dao.ProdutoDAO;
import dao.UsuarioDAO;

@ManagedBean
@SessionScoped
public class UsuarioMB {
	private Usuario usuario;
	private Usuario usuarioLogado;
	
	
	private String usuarioLogin;
	private String usuarioSenha;
	
	
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	@EJB
	private ProdutoDAO produtoDAO;
	
	@EJB
	private CategoriaDAO categoriaDAO;
	
	public UsuarioMB() {

	}

	@PostConstruct
	private void init(){
		usuarioDAO.save(new Usuario("Administrador", "admin", "admin", "a@a.com",true));
		usuarioDAO.save(new Usuario("Guest", "guest", "guest", "g@g.com",false));
		
		for (int i=0;i<10;i++) {
			categoriaDAO.save(new Categoria("Cat " + i));
		}
		
		for (int i=0;i<100;i++) {
			Categoria cat = categoriaDAO.findByPk((int) (1+Math.round(Math.random()*9)));
			produtoDAO.save(new Produto("Teste " + i, "linux.jpg" , 1+Math.random()*1000 , cat));
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String login(){
//		if (usuarioDAO.findAll().size()<1) {
//			usuarioDAO.save(new Usuario("Thiago Ferreira", "axpira", "axpira", true));
//		}
		
		this.usuarioLogado = usuarioDAO.login(new Usuario(null, usuarioLogin, usuarioSenha, null, false));
		FacesContext context = FacesContext.getCurrentInstance();
		if (this.usuarioLogado==null) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"Usuario ou senha invalido", 
							null
							)
					);
		} else {
			this.usuario = usuarioLogado;
		}
		return null;
	}
	public String usuarioCadastrar(){
		this.usuario = new Usuario();
		this.usuario.setLogin(usuarioLogin);
		this.usuario.setSenha(usuarioSenha);
		return "usuarioCadastrar";
	}
	
	public String cadastrar() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			usuarioDAO.save(usuario);
			this.usuario = new Usuario();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, 
							"Usuario cadastrado com sucesso", 
							null
							)
					);
			return "index";
		} catch (Exception e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							"Erro ao cadastrar o usuario", 
							null
							)
					);
		}
		return null;
	}
	
	public String logout(){
		this.usuarioLogado = null;
		this.usuario = new Usuario();
		return null;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getUsuarioSenha() {
		return usuarioSenha;
	}

	public void setUsuarioSenha(String usuarioSenha) {
		this.usuarioSenha = usuarioSenha;
	}

	public boolean isAdministrador(){
		return (this.usuarioLogado!=null && this.usuarioLogado.isAdministrador());
	}

}
