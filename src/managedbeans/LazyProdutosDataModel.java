package managedbeans;

import java.util.List;
import java.util.Map;

import model.Produto;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dao.ProdutoDAO;

public class LazyProdutosDataModel extends LazyDataModel<Produto>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoDAO produtoDAO;
	
	public static String FILTRO;
	
	public LazyProdutosDataModel(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
//		this.produtosTotal = produtoDAO.getTotal();
	}
	
	@Override
	public Produto getRowData(String rowKey) {
		return produtoDAO.findByPk(Integer.parseInt(rowKey));
	}
	
	@Override
	public Object getRowKey(Produto produto) {
		return produto.getId();
	}
	
	@Override
	public List<Produto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		List<Produto> produtos = (List<Produto>) produtoDAO.findByName(FILTRO, first, pageSize);
		this.setRowCount(produtoDAO.getTotalByName(FILTRO));

		return produtos;
	}
	
	@Override
	public void setRowIndex(int rowIndex) {
	    /*
	     * The following is in ancestor (LazyDataModel):
	     * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
	     */
	    if (rowIndex == -1 || getPageSize() == 0) {
	        super.setRowIndex(-1);
	    }
	    else
	        super.setRowIndex(rowIndex % getPageSize());
	}

	
}
