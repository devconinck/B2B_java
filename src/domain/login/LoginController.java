package domain.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import domain.AdminController;
import domain.Controller;
import domain.SupplierController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class LoginController {

	public final String PERSISTENCE_UNIT_NAME = "b2bportal";
    private EntityManager em;
    private EntityManagerFactory emf;
	private static final byte[] salt = new String("J#7pQzL9").getBytes();
	
	public LoginController() {
		addUsers();
	}

	public Controller login(String email, String password) {
		Account accountBoundToEmail = getAccountByEmail(email);
		if (accountBoundToEmail.getPassword() != encryptPassword(password))
			throw new IllegalArgumentException("Email and password combination does not match");
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
		openPersistentie();
		Account acc = em.createNamedQuery("Account.getByEmail", Account.class)
				.setParameter("email", email)
				.getSingleResult();
		closePersistentie();
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
		List<Account> accountList = new ArrayList<>();
		Account acc1 = new Account("Charles.leclerc@gmail.com", encryptPassword("test123"), 123456, Role.Supplier);
		Account acc2 = new Account("Danny.ricciardo@gmail.com", encryptPassword("root123"), 123456, Role.Admin);
		accountList.add(acc1);
		accountList.add(acc2);
		
		openPersistentie();
		em.getTransaction().begin();
		em.persist(acc1);
		em.getTransaction().commit();
		closePersistentie();
	}
	
}
