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
	
	
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
	private List<Orders> oi;

}
