package com.cg.ims.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
 
public class StoresDto {
    private int storeId;
 
    @NotBlank(message = "Store name is required")
    @Size(max = 255, message = "Store name must not exceed 255 characters")
    private String storeName;
 
    @Size(max = 100, message = "Web address must not exceed 100 characters")
    private String webAddress;
 
    @Size(max = 512, message = "Physical address must not exceed 512 characters")
    private String physicalAddress;
 
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
 
   
}
