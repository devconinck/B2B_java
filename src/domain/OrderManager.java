package domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class OrderManager {

    public final String PERSISTENCE_UNIT_NAME = "project";
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public OrderManager() {
    	initializePersistence();
    }
    
    private void initializePersistence() {
    	openPersistence();
    }
    
    private void openPersistence() {
    	emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    	em = emf.createEntityManager();
    }
    
    public void closePersistence() {
    	em.close();
    	emf.close();
    }
}
