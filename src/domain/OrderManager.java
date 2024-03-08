package domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class OrderManager {

    public final String PERSISTENCE_UNIT_NAME = "delaware";
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public OrderManager() {
    	initializePersistence();
    }
    
    private void initializePersistence() {
    	openPersistence();
    	OrderData od = new OrderData(this);
    	od.addData();
    	System.out.println("Add data done!");
    }
    
    private void openPersistence() {
    	emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    	em = emf.createEntityManager();
    }
    
    public void closePersistence() {
    	em.close();
    	emf.close();
    }
    
    public void addOrder(Order o) {
    	em.getTransaction().begin();
    	em.persist(o);
    	em.getTransaction().commit();
    }
    
    public void addOrderItem(OrderItem oi) {
    	em.getTransaction().begin();
    	em.persist(oi);
    	em.getTransaction().commit();
    }
    
    public void addProduct(Product p) {
    	em.getTransaction().begin();
    	em.persist(p);
    	em.getTransaction().commit();
    }
    
    public void addProductPrice(ProductPrice pp) {
    	em.getTransaction().begin();
    	em.persist(pp);
    	em.getTransaction().commit();
    }
    
    public void addProductDescription(ProductDescription pd) {
    	em.getTransaction().begin();
    	em.persist(pd);
    	em.getTransaction().commit();
    }
    
    public void addProductUnitOfMeasureConversion(ProductUnitOfMeasureConversion pu) {
    	em.getTransaction().begin();
    	em.persist(pu);
    	em.getTransaction().commit();
    }
}
