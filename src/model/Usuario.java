package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Usuario implements PersistentEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min=4)
	private String nome;
	
	@NotNull
	@Pattern(regexp="\\S+",message="Deve ter pelo menos um caracter e nao pode conter espacos")
	@Column(unique=true)
	private String login;
	
	@NotNull
	@Size(min=4)
	private String senha;
	
	@Pattern(regexp=".+@.+\\.[a-z]+",message="Email invalido")
	private String email;
	
	private boolean administrador;
	
	public Usuario() {
	}

	public Usuario(String nome, String login, String senha, String email, boolean administrador) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.administrador = administrador;
	}



	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public boolean isNew() {
		return (this.id==null);
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
