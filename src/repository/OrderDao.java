package repository;

import java.util.List;

import domain.Company;
import domain.Order;
import jakarta.persistence.EntityNotFoundException;

public interface OrderDao extends GenericDao<Order> {

	public List<Order> getOrdersMadeToCompany(Company company) throws EntityNotFoundException;
}
