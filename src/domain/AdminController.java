package domain;

import repository.GenericDaoJpa;

public class AdminController extends Controller {
	
	private B2BPortaal portaal;
	
	public AdminController() {
		this.portaal = new B2BPortaal();
	}
	
	public void addCompany(Company company) {
		portaal.addCompany(company);
	}

	public void updateCompany(Company company) {
		portaal.updateCompany(company);
	}

}
