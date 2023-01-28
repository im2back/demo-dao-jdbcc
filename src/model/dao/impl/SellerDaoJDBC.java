package model.dao.impl;// pacote onde estar� a implementa��o do DAO

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

// classe que ir� implementar do DAO ou seja os metodos ter�o logicas
public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
// construtor para termos acesso a conex�o com o banco de dados e poder realizar os metodos que dependem do banco de dados
    public SellerDaoJDBC(Connection conn) {
	this.conn = conn;
}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
            
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
//criando metodo para reutilizar a instancia��o do Departament e evitar escrever muitas linhas... metodo que vai fazer isso � o #instantiateDepartament#
				Departament dep = instantiateDepartament(rs);
				//criando metodo para reutilizar a instancia��o do Seller e evitar escrever muitas linhas... metodo que vai fazer isso � o #instantiateSeller#
				Seller obj = instantiateSeller(rs, dep);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		}
	// a exce��o do metodo auxiliar nao � tratada porque quando o metodo for chamado epla classe que ir� utiliza-lo ele ja estara dentro de yum try catch, ent�o nos vamos somente propagar a exce��o	
private Seller instantiateSeller(ResultSet rs, Departament dep) throws SQLException {
	Seller obj = new Seller();
	obj.setId(rs.getInt("Id"));
	obj.setName(rs.getString("Name"));
	obj.setEmail(rs.getString("Email"));
	obj.setBaseSalary(rs.getDouble("BaseSalary"));
	obj.setBirthDate(rs.getDate("BirthDate"));
	obj.setDepartament(dep);
		return obj;
	}

// a exce��o do metodo auxiliar nao � tratada porque quando o metodo for chamado epla classe que ir� utiliza-lo ele ja estara dentro de yum try catch, ent�o nos vamos somente propagar a exce��o
	private Departament instantiateDepartament(ResultSet rs) throws SQLException {
		Departament dep = new Departament();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
