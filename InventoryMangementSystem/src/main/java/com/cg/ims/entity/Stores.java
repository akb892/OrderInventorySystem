package com.cg.ims.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "stores")
public class Stores {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Stores(String storeName, String webAddress, String physicalAddress, Double latitude, Double longitude,
			byte[] logo, String logoMimeType, String logoFileName, String logoCharset, LocalDate logoLastUpdated) {
		super();
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
	}
	
	
	
}
