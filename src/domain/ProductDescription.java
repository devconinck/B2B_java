package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductDescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String productDescriptionId;
	private String languageId;
	private int syncDescriptionId;
	private String productName;
	@Column(length = 400)
	private String productListerDescription;
	@Column(length = 400)
	private String productShortDescription;
	@Column(length = 2000)
	private String productLongDescription;
	
	public ProductDescription(String productDescriptionId, String languageId, int syncDescriptionId, String productName, String productListerDescription, String productShortDescription, String productLongDescription) {
		setProductDescriptionId(productDescriptionId);
		setLanguageId(languageId);
		setSyncDescriptionId(syncDescriptionId);
		setProductName(productName);
		setProductListerDescription(productListerDescription);
		setProductShortDescription(productShortDescription);
		setProductLongDescription(productLongDescription);
	}
	
	protected ProductDescription() {
		
	}

	public String getProductDescriptionId() {
		return productDescriptionId;
	}

	public void setProductDescriptionId(String productDescriptionId) {
		this.productDescriptionId = productDescriptionId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public int getSyncDescriptionId() {
		return syncDescriptionId;
	}

	public void setSyncDescriptionId(int syncDescriptionId) {
		this.syncDescriptionId = syncDescriptionId;
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
