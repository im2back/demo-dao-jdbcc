package model.dao.impl;// pacote onde estar� a implementa��o do DAO

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Seller> findByDepartament(Departament department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");

			st.setInt(1, department.getId());

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Departament> map = new HashMap<>();// ferramenta map para verificar se ja existe um departamento criado e evitar repeti��o
            //#Integer# � o tipo de valor da chave para buscar(nesse caro � inteiro pos vamos buscar pelo ID DO departamento)
			//#Departament# cada objeto verificado ser� do tipo departamento
			//#map# nome do nosso objeto "apelido"
		    //#new HashMap<>() instanciamento vazio
			while (rs.next()) {
                 // verificando se o departamento ja existe (se ja foi instanciado)
				Departament dep = map.get(rs.getInt("DepartmentId"));
                // variavel dep recebe o valor da consulta do m�todo #map.get(rs.getInt("DepartmentId"));# 
				// se o valor da consulta for nulo, quer dizer que n�o h� departamento instanciado com o valor informado, ent�o ele ser� instanciado
				// se ja houver ele n�o ser� isntanciado e sim apontado para oq ja existe
				if (dep == null) {
					dep = instantiateDepartament(rs); // instanciando caso n�o existe ainda o departamento
					
					map.put(rs.getInt("DepartmentId"), dep);// salvando o departamento instanciado para futuras consultas no metodo map
				}

				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}


