package crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProdutoRepository {
	
	private static EntityManagerFactory createFactory = Persistence.createEntityManagerFactory("treinamento");
	
	public static EntityManager getConnection() {
		return createFactory.createEntityManager();
	}
}
