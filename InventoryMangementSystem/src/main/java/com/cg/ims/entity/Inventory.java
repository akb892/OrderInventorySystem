package com.cg.ims.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory")
public class Inventory {
	
	
	@Id
	private int inventoryId;
	
	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName = "store_id")
	private Stores store;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Products product;
	
	@Column(name = "product_inventory")
	private int productInventory;

	
	
	
}
