package crud;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class ProdutoDao {

	public void save(Produto produto) {
		EntityManager manager = ProdutoRepository.getConnection();

		try {
			manager.getTransaction().begin();
			if (produto.getId() == null) {
				manager.persist(produto);
			} else {
				manager.merge(produto);
			}
			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().getRollbackOnly();
			System.out.println(e.getMessage());

		} finally {
			manager.close();
		}
	}

	public Produto findById(Integer id) {
		EntityManager manager = ProdutoRepository.getConnection();
		Produto produto = null;
		
		try {
			produto = manager.find(Produto.class, id);
			
		}catch (Exception e){
			manager.getTransaction().getRollbackOnly();
			System.out.println(e.getMessage());
			
		}finally {
			manager.close();
		}
		
		return produto;
	}
	
	public List<Produto> findAll(){
		EntityManager manager = ProdutoRepository.getConnection();
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			produtos = manager.createQuery("from Produto p").getResultList();
		} catch (Exception e) {
			manager.getTransaction().getRollbackOnly();
			System.out.println(e.getMessage());
		} finally {
			manager.close();
		}
		
		return produtos;
	}
	
	public void removeById(Integer id) {
		EntityManager manager = ProdutoRepository.getConnection();
		
		try {
			Produto produto = this.findById(id);
			
			manager.getTransaction().begin();
			manager.remove( manager.contains(produto) ? produto : manager.merge(produto));
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			manager.getTransaction().getRollbackOnly();
			System.out.println(e.getMessage());
		} finally {
			manager.close();
		}
	}
}
