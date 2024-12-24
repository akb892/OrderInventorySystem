package com.cg.ims.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.cg.ims.dto.ProductDto;
import com.cg.ims.entity.Products;
import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;
import com.cg.ims.service.ProductServiceImp;
 

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
 
	@Autowired
	private ProductServiceImp productService;
 
	@GetMapping
	public ResponseEntity<?> getAllProducts()  throws InternalServerErrorException{
			List<Products> products = productService.getAllProducts();
			return ResponseEntity.ok(products);
		
	}
 
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) throws BadRequestException {
			productService.addProduct(productDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
	}
 
	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto) throws BadRequestException {
			productService.updateProduct(productDto);
			return ResponseEntity.ok("Record Updated Successfully");
		
	}
 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) throws ResourceNotFoundException {
		productService.deleteProduct(id);
		return ResponseEntity.ok("Record deleted Successfully");
	
	}
 
	@GetMapping("/{productname}")
	public ResponseEntity<?> searchProductsByName(@PathVariable String productname) throws ResourceNotFoundException {
		List<Products> products = productService.searchProductsByName(productname);
		return ResponseEntity.ok(products);
	
	}
	@GetMapping("/id/{productId}")
	public ResponseEntity<?> searchProductById(@PathVariable int productId) throws ResourceNotFoundException {
	        ProductDto product = productService.getProductByID(productId);
	        return ResponseEntity.ok(product);
	    
	}
	
	
 
 
	@GetMapping("/brand/{brand}")
	public ResponseEntity<?> getProductsByBrand(@PathVariable String brand) throws ResourceNotFoundException {
		List<Products> products = productService.getProductsByBrand(brand);
		if (products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products with the specified brand not found.");
		} else {
			return ResponseEntity.ok(products);
		}
	}
 
	@GetMapping("/colour/{color}")
	public ResponseEntity<?> getProductsByColor(@PathVariable String color) throws ResourceNotFoundException {
		List<Products> products = productService.getProductsByColor(color);
		if (products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products with the specified color not found.");
		} else {
			return ResponseEntity.ok(products);
		}
	}
 
	@GetMapping("/unitprice")
	public ResponseEntity<?> getProductsByUnitPrice(@RequestParam double min, @RequestParam double max) throws BadRequestException {
			List<Products> products = productService.getProductsByPriceRange(min, max);
			return ResponseEntity.ok(products);
		
	}
 
	@GetMapping("/sort")
	public ResponseEntity<?> getProductsSorted(@RequestParam String field) throws BadRequestException {
			List<Products> products = productService.getProductsSortedByField(field);
			return ResponseEntity.ok(products);
	}
}