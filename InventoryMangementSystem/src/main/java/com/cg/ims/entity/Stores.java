package com.cg.ims.entity;


import java.time.LocalDate;
import java.util.List;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stores")
public class Stores {
 
    @Id
    @Column(name = "store_id")
    private int storeId;
 
    @Column(name = "store_name")
    private String storeName;
 
    @Column(name = "web_address")
    private String webAddress;
 
    @Column(name = "physical_address") // Fixed the name for clarity
    private String physicalAddress;
 
    @Column(name = "latitude") // Fixed duplicate mapping
    private Double latitude;
 
    @Column(name = "longitude") // Fixed duplicate mapping
    private Double longitude;
 
    @Lob
    @Column(name = "logo")
    private byte[] logo;
 
    @Column(name = "logo_mime_type")
    private String logoMimeType;
 
    @Column(name = "logo_filename")
    private String logoFileName;
 
    @Column(name = "logo_charset")
    private String logoCharset;
 
    @Column(name = "logo_last_updated")
    private LocalDate logoLastUpdated;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> inventories;
 
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;
 
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shipments> shipments;

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getLogoMimeType() {
		return logoMimeType;
	}

	public void setLogoMimeType(String logoMimeType) {
		this.logoMimeType = logoMimeType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getLogoCharset() {
		return logoCharset;
	}

	public void setLogoCharset(String logoCharset) {
		this.logoCharset = logoCharset;
	}

	public LocalDate getLogoLastUpdated() {
		return logoLastUpdated;
	}

	public void setLogoLastUpdated(LocalDate logoLastUpdated) {
		this.logoLastUpdated = logoLastUpdated;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<Shipments> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipments> shipments) {
		this.shipments = shipments;
	}

	public Stores(int storeId, String storeName, String webAddress, String physicalAddress, Double latitude,
			Double longitude, byte[] logo, String logoMimeType, String logoFileName, String logoCharset,
			LocalDate logoLastUpdated, List<Inventory> inventories, List<Orders> orders, List<Shipments> shipments) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.webAddress = webAddress;
		this.physicalAddress = physicalAddress;
		this.latitude = latitude;
		this.longitude = longitude;
		this.logo = logo;
		this.logoMimeType = logoMimeType;
		this.logoFileName = logoFileName;
		this.logoCharset = logoCharset;
		this.logoLastUpdated = logoLastUpdated;
		this.inventories = inventories;
		this.orders = orders;
		this.shipments = shipments;
	}
    
    public Stores() {
		// TODO Auto-generated constructor stub
	}
}