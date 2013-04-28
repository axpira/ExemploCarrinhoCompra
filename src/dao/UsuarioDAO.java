package dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;

import model.Usuario;

@Stateful
public class UsuarioDAO extends GenericDAO<Usuario, Integer> {

	public Usuario login(Usuario usuario) {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("login", usuario.getLogin());
			parameters.put("senha", usuario.getSenha());

			return (Usuario) findSingleByQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", parameters);
		} catch (Exception e) {
			return null;
		}
	}

}
