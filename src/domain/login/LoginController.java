package domain.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import domain.AdminController;
import domain.Controller;
import domain.SupplierController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class LoginController {

	public final String PERSISTENCE_UNIT_NAME = "delawaredb";
    private EntityManager em;
    private EntityManagerFactory emf;
	private static final byte[] salt = new String("J#7pQzL9").getBytes();
	
	public LoginController() {
		openPersistentie();
		addUsers();
	}

	public Controller login(String email, String password) {
		Account accountBoundToEmail = getAccountByEmail(email);
		if (accountBoundToEmail == null)
			System.err.println("null"); // TODO must throw error
		if (!accountBoundToEmail.getPassword().equals(encryptPassword(password)))
			System.err.println("password does not match"); // TODO must throw error
		if (accountBoundToEmail.getRole() == Role.Admin)
			return  new AdminController();
		return new SupplierController();
	}

	public String encryptPassword(String password) {
		try {
			// combine both on byte level
			byte[] fullPassword = new byte[password.length() + salt.length];
			System.arraycopy(password.getBytes(), 0, fullPassword, 0, password.length());
            System.arraycopy(salt, 0, fullPassword, password.length(), salt.length);
			
            // hashing
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = messageDigest.digest(fullPassword);
			
			// convert back to string
			StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
			
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	private Account getAccountByEmail(String email) {
		Account acc = em.createNamedQuery("Account.getByEmail", Account.class)
				.setParameter("email", email)
				.getSingleResult();
		return acc;
	}
	
	private void openPersistentie() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }
	
	private void closePersistentie() {
        em.close();
        emf.close();
    }
	
	private void addUsers() {
		Account acc1 = new Account("Charles.leclerc@gmail.com", encryptPassword("test123"), 123456, Role.Supplier);
		Account acc2 = new Account("Danny.ricciardo@gmail.com", encryptPassword("root123"), 123456, Role.Admin);
		em.getTransaction().begin();
		Stream.of(acc1, acc2).forEach(em::persist);
		em.getTransaction().commit();
	}
	
}
