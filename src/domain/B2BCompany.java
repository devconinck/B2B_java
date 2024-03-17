package domain;

import java.util.Date;
import java.util.List;

public interface B2BCompany {
	public String getVatNumber();
	public String getLogo();
	public Address getAddress();
	public String getAddressString();
	public Contact getContact();
	public String getName();
	public String getSector();
	public Long getBankAccountNr();
	public boolean getIsActive();
	public List<String> getPaymentOptions();
	public Date getCustomerStart();
}
