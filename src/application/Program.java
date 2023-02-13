package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
	
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("===TESTE 1 : seller findById ===");
		Seller seller =  sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("===TESTE 2 : seller findByDepartament ===");
		Departament departament = new Departament(2,null);
		List<Seller> list =  sellerDao.findByDepartament(departament);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("===TESTE 3 : seller findByDepartament ===");
		
		list =  sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		
	}
		System.out.println("\n=== TEST 4: seller insert =====");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, departament);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());

}
}
