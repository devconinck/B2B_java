package domain.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import domain.AdminController;
import domain.Controller;
import domain.SupplierController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;


public class LoginController {

	public final String PERSISTENCE_UNIT_NAME = "delawaredb";
	private EntityManager em = Persistence.createEntityManagerFactory(null).createEntityManager();
	private static final byte[] salt = new String("J#7pQzL9").getBytes();

	public Controller login(String email, String password) {
		try {	
			Account accountBoundToEmail = getAccountByEmail(email);
			if (accountBoundToEmail.getPassword().equals(encryptPassword(password))) {
				if (accountBoundToEmail.getRole() == Role.Admin)
					return  new AdminController();
				if (accountBoundToEmail.getRole() == Role.Supplier)
					return new SupplierController();
			}
			System.err.println("Wrong password");
			return null;
		} catch (NoResultException e) {
			System.err.println("No matching email found");
			return null;
		}
	}

	public static String encryptPassword(String password) {
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
	
}
