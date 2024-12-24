package com.cg.ims.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.cg.ims.dao.IProductRepo;
import com.cg.ims.dto.ProductDto;
import com.cg.ims.entity.Products;
import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;
import com.cg.ims.service.interfaces.IProductService;
 
@Service
public class ProductServiceImp implements IProductService {
	@Autowired
	private IProductRepo repo;
 
	@Override
	public List<Products> getAllProducts() throws InternalServerErrorException {
		List<Products> li = repo.findAll();
		if(li.isEmpty()) {
			throw new InternalServerErrorException("An internal server error occurred while fetching all products.");
		}
		else {
			return li;
		}
	}
 
	@Override
	public ProductDto addProduct(ProductDto productDto) throws BadRequestException {
		// TODO Auto-generated method stubF
		if (repo.findById(productDto.getProductId()).isPresent()) {
			throw new BadRequestException("Invalid request. Please provide valid product data for creation.");
		}
		Products product = new Products();
 
		product.setProductId(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setUnitPrice(productDto.getUnitPrice());
		product.setColor(productDto.getColour());
		product.setBrand(productDto.getBrand());
		product.setBrand(productDto.getBrand());
		product.setSize(productDto.getSize());
		return productDto;
 
	}
 
	@Override
	public ProductDto updateProduct(ProductDto productDto) throws BadRequestException {
 
		Products product = null;
		Optional<Products> op = repo.findById(productDto.getProductId());
		if (op.isPresent()) {
			repo.saveAndFlush(product);
			return productDto;
		}
		else {
			throw new BadRequestException("Invalid request. Please provide valid product data for updating.");
		}
	}
 
	@Override
	public String deleteProduct(int productId) throws ResourceNotFoundException {
		Optional<Products> op = repo.findById(productId);
		if (op.isPresent()) {
			repo.delete(op.get());
			return "Product Deleted";
		} else {
			throw new ResourceNotFoundException("Product with the specified ID not found for deletion.");
		}
	}
 
	@Override
	public ProductDto getProductByID(int productId) throws ResourceNotFoundException {
		return repo.findById(productId)
				.map(e -> new ProductDto(e.getProductId(), e.getProductName(), e.getUnitPrice(), e.getColor(),
						e.getBrand(), e.getSize(), e.getRating(),e.getOrderItems(),e.getInventories()))
				.orElseThrow(() -> new ResourceNotFoundException("Product with " + productId + " not Found"));
 
	}
 
	@Override
	public List<Products> searchProductsByName(String productName) throws ResourceNotFoundException{
		List<Products> li =  repo.searchProductByName(productName);
		if(li.isEmpty()) {
			throw new ResourceNotFoundException("Product(s) with the specified name not found.");
		}
		else {
			return li;
		}
	}
 
	@Override
	public List<Products> getProductsByBrand(String brand) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		List<Products> li =  repo.findByBrand(brand);
		if(li.isEmpty()) {
			throw new ResourceNotFoundException("Products with the specified brand not found.");
		}
		else {
			return li;
		}
	}
 
	@Override
	public List<Products> getProductsByPriceRange(double min, double max) throws BadRequestException {
		// TODO Auto-generated method stub
		List<Products> li =  repo.findByUnitPriceBetween(min, max);
		if(li.isEmpty()) {
			throw new BadRequestException("Invalid request. Please provide valid minimum and maximum unit prices.");
		}
		else {
			return li;
		}
	}
 
	@Override
	public List<Products> getProductsSortedByField(String field) throws BadRequestException{
		// TODO Auto-generated method stub
		List<Products> li =  repo.findAllByOrderByField(field);
		if(li.isEmpty()) {
			throw new BadRequestException("Invalid request. Please provide a valid field for sorting.");
		}
		else {
			return li;
		}
	}
 
	@Override
	public List<Products> getProductsByColor(String color) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		List<Products> li =  repo.findByColor(color);
		if(li.isEmpty()) {
			throw new ResourceNotFoundException("Products with the specified color not found.");
		}
		else {
			return li;
		}
	}
 
}

