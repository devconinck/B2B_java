package util;

import java.util.stream.Stream;

import domain.login.Account;
import domain.login.LoginController;
import domain.login.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Seed {
	
	public final String PERSISTENCE_UNIT_NAME = "delawaredb";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public Seed() {
		run();
	}
	
	private void run() {
		openPersistence();
		addUsers();
		closePersistence();
	}
	
	private void openPersistence() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }
	
	public static void closePersistence() {
        em.close();
        emf.close();
    }
	
	private void addUsers() {
		Account acc1 = new Account("Charles.leclerc@gmail.com", LoginController.encryptPassword("test123"), 123456, Role.Supplier);
		Account acc2 = new Account("Danny.ricciardo@gmail.com", LoginController.encryptPassword("root123"), 123456, Role.Admin);
		em.getTransaction().begin();
		Stream.of(acc1, acc2).forEach(em::persist);
		em.getTransaction().commit();
	}
}
