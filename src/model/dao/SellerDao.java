package model.dao;

import java.util.List;

import model.entities.Seller;
//Para cada entidade, haverá um objeto responsável por fazer acesso a dados relacionado a esta
//entidade. Por exemplo:
//o Cliente: ClienteDao
//o Produto: ProdutoDao
//o Pedido: PedidoDao
// Cada DAO será definido por uma interface.
//A injeção de dependência pode ser feita por meio do padrão de projeto Factory
public interface SellerDao {

	
	void insert(Seller obj);
	void update(Seller obj);
	void deleteById (Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
	
	
	
}
