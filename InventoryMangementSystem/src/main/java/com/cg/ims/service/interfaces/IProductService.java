package com.cg.ims.service.interfaces;
 
import java.util.List;
 
import com.cg.ims.dto.ProductDto;
import com.cg.ims.entity.Products;
import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;
 
public interface IProductService {
 
	List<Products> getAllProducts() throws InternalServerErrorException;
 
	ProductDto addProduct(ProductDto productDto)throws BadRequestException;
 
	ProductDto updateProduct(ProductDto productDto)throws BadRequestException;
 
	String deleteProduct(int productId) throws ResourceNotFoundException;
 
	List<Products> searchProductsByName(String productName) throws ResourceNotFoundException;
 
	List<Products> getProductsByBrand(String brand) throws ResourceNotFoundException;
 
	ProductDto getProductByID(int productId) throws ResourceNotFoundException;
 
	public List<Products> getProductsByPriceRange(double min, double max) throws BadRequestException;
 
	public List<Products> getProductsSortedByField(String field) throws BadRequestException;
 
	List<Products> getProductsByColor(String color) throws ResourceNotFoundException;
 
}