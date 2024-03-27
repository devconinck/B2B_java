package util;

public class Validation {
	// Source: https://emailregex.com
	public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
	// Source: https://stackoverflow.com/questions/42104546/java-regular-expressions-to-validate-phone-numbers#42105140
	public static final String PHONE_NUMBER_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
	
	// Source: https://stackoverflow.com/questions/62659073/regex-for-house-number
	public static final String HOUSE_NUMBER_REGEX = "^[1-9]\\d*(?: ?(?:[a-z]|[/-] ?\\d+[a-z]?))?$";
	
	public static final String IBAN_REGEX = "^([A-Z]{2}[ \\-]?[0-9]{2})(?=(?:[ \\-]?[A-Z0-9]){9,30}$)((?:[ \\-]?[A-Z0-9]{3,5}){2,7})([ \\-]?[A-Z0-9]{1,3})?$";
	
	// Source: https://stackoverflow.com/questions/46404695/simple-regex-for-eu-vat-numbers
	public static final String VAT_REGEX = "^[A-Za-z]{2,4}(?=.{2,12}$)[-_\\s0-9]*(?:[a-zA-Z][-_\\s0-9]*){0,2}$";
	
	public static final String PASSWORD_REGEX = "^.{4,}$";
	
	public static final String COMPANY_VAT_REGEX = "[A-Z]{2}[0-9A-Za-z]{1,30}";
	
	public static final String COMPANY_BANK_ACCOUNT = "[A-Z]{2}[0-9]{1,30}";
	
	public static final String DATE_REGEX = "\\b(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])\\b";
	
	public static final int PAYMENT_PERIOD = 14;
	
	public static final String ACCOUNT_EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
}
