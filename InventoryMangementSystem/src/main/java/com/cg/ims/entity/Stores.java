package com.cg.ims.entity;


import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "stores")
public class Stores {
	
	
	@Id
	@Column(name = "store_id")
	private int storeId;
	
	@Column(name = "store_name")
	private String storeName;
	
	@Column(name = "web_address")
	private String webAddress;
	
	@Column(name = "latitude")
	private String physicalAddress;
	
	@Column(name = "longitude")
	private Double latitude;
	
	
	
	@Column(name = "logo")
	private Double longitude;
	

	@Column(name = "logo", insertable = false, updatable = false)
	private Blob logo;
	
	@Column(name = "logo_mime_type")
	private String logoMimeType;
	
	@Column(name = "logo_filename")
	private String logoFileName;
	
	@Column(name = "logo_charset")
	private String logoCharset;
	
	@Column(name = "logo_last_updated")
	private LocalDate logoLastUpdated;
	
	
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
	private List<Orders> oi;

	public Stores() {
		
	}

	public Stores(int storeId, String storeName, String webAddress, String physicalAddress, Double latitude,
			Double longitude, Blob logo, String logoMimeType, String logoFileName, String logoCharset,
			LocalDate logoLastUpdated, List<Orders> oi) {
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
		this.oi = oi;
	}


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


	public Blob getLogo() {
		return logo;
	}


	public void setLogo(Blob logo) {
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


	public List<Orders> getOi() {
		return oi;
	}


	public void setOi(List<Orders> oi) {
		this.oi = oi;
	}

	
	
}
