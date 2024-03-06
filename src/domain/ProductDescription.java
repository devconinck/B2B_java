package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductDescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productDescriptionId;

	private String languageId;
	private int syncId;
	private String productName;
	private String productListerDescription;
	private String productShortDescription;
	private String productLongDescription;
	
	public ProductDescription(String languageId, int syncId, String productName, String productListerDescription, String productShortDescription, String productLongDescription) {
		setLanguageId(languageId);
		setSyncId(syncId);
		setProductName(productName);
		setProductListerDescription(productListerDescription);
		setProductShortDescription(productShortDescription);
		setProductLongDescription(productLongDescription);
	}
	
	protected ProductDescription() {
		
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public int getSyncId() {
		return syncId;
	}

	public void setSyncId(int syncId) {
		this.syncId = syncId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductListerDescription() {
		return productListerDescription;
	}

	public void setProductListerDescription(String productListerDescription) {
		this.productListerDescription = productListerDescription;
	}

	public String getProductShortDescription() {
		return productShortDescription;
	}

	public void setProductShortDescription(String productShortDescription) {
		this.productShortDescription = productShortDescription;
	}

	public String getProductLongDescription() {
		return productLongDescription;
	}

	public void setProductLongDescription(String productLongDescription) {
		this.productLongDescription = productLongDescription;
	}
	
	
}
