package crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/* Objetivo: Criar um crud utilizando jpa-hibernate
 * Autor: Matheus Jordan Paz de Oliveira
 */
public class App {
	
	public static void main(String[] args) {
		
		Produto p0 = new Produto("café",2.50);
		Produto p1 = new Produto("leite",2.0);
		Produto p2 = new Produto("suco",1.50);
		Produto p3 = new Produto("refrigerante",3.0);
		
		List<Produto> ps = new ArrayList<Produto>(Arrays.asList(p0,p1,p2,p3));
		
		//Meu EntityManagerFactory -> Criador do gerenciador de entidades
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("treinamento"); treinamento = persistence unit from persistence.xml
		
		//Exemplo de gerenciamento de minhas entidades
//		EntityManager em = emf.createEntityManager(); <- Instanciação do meu gerenciador de entidades
//		em.getTransaction().begin(); 				  <- Inicio da conexão com o banco de dados
//		em.persist(Object); 						  <- Persistencia do meu objeto no banco de dados
//		em.getTransaction().commit(); 				  <- Salvamento das persistencias feitas no banco de dados
//		em.close(); 								  <- Fechamento da conexão feita com o banco de dados
//		emf.close();								  <- Fechamaneto do meu criador de gerenciador de entiades 

		ProdutoDao dao = new ProdutoDao();
		
		//CREATE
		for(Produto p : ps) {
			dao.save(p);
		}
		
		//READ
		System.out.println("FIND BY ID ---");
		System.out.println(dao.findById(1).getNome());
		System.out.println("FIN ALL ---");
		for(Produto p : dao.findAll()) {
			System.out.println(p.toString());
		}
		
		//UPDATE
		p2.setNome("cachaça");
		dao.save(p2);
		
		//READ
		System.out.println("FIND BY ID ---");
		System.out.println(dao.findById(3).getNome());
		
		//DELETE
		dao.removeById(2);
	}
}
