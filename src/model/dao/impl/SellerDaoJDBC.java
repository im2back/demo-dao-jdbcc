package model.dao.impl;// pacote onde estará a implementação do DAO

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

// classe que irá implementar do DAO ou seja os metodos terão logicas
public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
// construtor para termos acesso a conexão com o banco de dados e poder realizar os metodos que dependem do banco de dados
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
//criando metodo para reutilizar a instanciação do Departament e evitar escrever muitas linhas... metodo que vai fazer isso é o #instantiateDepartament#
				Departament dep = instantiateDepartament(rs);
				//criando metodo para reutilizar a instanciação do Seller e evitar escrever muitas linhas... metodo que vai fazer isso é o #instantiateSeller#
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
	// a exceção do metodo auxiliar nao é tratada porque quando o metodo for chamado epla classe que irá utiliza-lo ele ja estara dentro de yum try catch, então nos vamos somente propagar a exceção	
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

// a exceção do metodo auxiliar nao é tratada porque quando o metodo for chamado epla classe que irá utiliza-lo ele ja estara dentro de yum try catch, então nos vamos somente propagar a exceção
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
			Map<Integer, Departament> map = new HashMap<>();// ferramenta map para verificar se ja existe um departamento criado e evitar repetição
            //#Integer# é o tipo de valor da chave para buscar(nesse caro é inteiro pos vamos buscar pelo ID DO departamento)
			//#Departament# cada objeto verificado será do tipo departamento
			//#map# nome do nosso objeto "apelido"
		    //#new HashMap<>() instanciamento vazio
			while (rs.next()) {
                 // verificando se o departamento ja existe (se ja foi instanciado)
				Departament dep = map.get(rs.getInt("DepartmentId"));
                // variavel dep recebe o valor da consulta do método #map.get(rs.getInt("DepartmentId"));# 
				// se o valor da consulta for nulo, quer dizer que não há departamento instanciado com o valor informado, então ele será instanciado
				// se ja houver ele não será isntanciado e sim apontado para oq ja existe
				if (dep == null) {
					dep = instantiateDepartament(rs); // instanciando caso não existe ainda o departamento
					
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


