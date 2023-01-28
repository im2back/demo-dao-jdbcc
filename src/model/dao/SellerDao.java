package model.dao;

import java.util.List;

import model.entities.Seller;
//Para cada entidade, haver� um objeto respons�vel por fazer acesso a dados relacionado a esta
//entidade. Por exemplo:
//o Cliente: ClienteDao
//o Produto: ProdutoDao
//o Pedido: PedidoDao
// Cada DAO ser� definido por uma interface.
//A inje��o de depend�ncia pode ser feita por meio do padr�o de projeto Factory
public interface SellerDao {

	
	void insert(Seller obj);
	void update(Seller obj);
	void deleteById (Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
	
	
	
}
