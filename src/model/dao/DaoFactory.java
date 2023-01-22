package model.dao;

import model.dao.impl.SellerDaoJDBC;

// esta classe tem operações estaticas para isntanciar o DAO
public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
