package gui.customer;

import java.sql.Date;

import domain.Company;

public record CompanyDTO(String vatNumber, Date customerStart, String name, String sector, String country) {
    public CompanyDTO(Company c) {
    	this(c.getVatNumber(), (Date) c.getCustomerStart(), c.getName(), c.getSector(), c.getAddress().getCountry());
    }
}
