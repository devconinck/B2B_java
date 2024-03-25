package repository;

import java.util.ArrayList;
import java.util.List;

import domain.Company;
import domain.Order;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

public class OrderDaoJpa extends GenericDaoJpa<Order> implements OrderDao {

	public OrderDaoJpa() {
		super(Order.class);
	}

	@Override
	public List<Order> getOrdersMadeToCompany(Company company) throws EntityNotFoundException {
		try {
			return em.createNamedQuery("Order.orderMadeToCompany", Order.class).setParameter("company", company).getResultList();
		} catch (NoResultException ex) {
			return new ArrayList<>();
		}
	}

}
