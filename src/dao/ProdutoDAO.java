package dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.Query;

import model.Produto;

@Stateful
public class ProdutoDAO extends GenericDAO<Produto, Integer>{
	
	public Collection<Produto> findByName(String nome, int firstPage, int maxResults){
		String query = "SELECT p FROM Produto p WHERE p.nome like :produtoNome";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("produtoNome", "%" + nome + "%");
		
		
		return findByQuery(query, firstPage, maxResults, map);
	}

	public int getTotalByName(String nome) {
		
		Query q = entityManager.createQuery("SELECT COUNT(p) FROM Produto p WHERE p.nome like :produtoNome");
		q.setParameter("produtoNome", "%" + nome + "%");
		return ((Long)q.getSingleResult()).intValue();
		
	}

}
