package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

// esta classe tem operações estaticas para isntanciar o DAO
public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
}
