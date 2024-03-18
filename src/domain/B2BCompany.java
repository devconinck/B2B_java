package domain;

import java.util.Date;
import java.util.List;

import util.PaymentOption;

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
	public List<PaymentOption> getPaymentOptions();
	public Date getCustomerStart();
}
