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

    /**
     * Fetches all products from the database.
     * 
     * @return List of all products.
     * @throws InternalServerErrorException if an error occurs while fetching products.
     */
    @Override
    public List<Products> getAllProducts() throws InternalServerErrorException {
        List<Products> li = repo.findAll();
        if (li.isEmpty()) {
            throw new InternalServerErrorException("An internal server error occurred while fetching all products.");
        }
        return li;
    }

    /**
     * Adds a new product to the system.
     * 
     * @param productDto the data transfer object containing the product details.
     * @return ProductDto containing the details of the created product.
     * @throws BadRequestException if the product already exists.
     */
    @Override
    public ProductDto addProduct(ProductDto productDto) throws BadRequestException {
        if (repo.findById(productDto.getProductId()).isPresent()) {
            throw new BadRequestException("Invalid request. Please provide valid product data for creation.");
        }
        
        // Create new Product entity
        Products product = new Products();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setUnitPrice(productDto.getUnitPrice());
        product.setColor(productDto.getColour());
        product.setBrand(productDto.getBrand());
        product.setSize(productDto.getSize());

        // Return the ProductDto of the added product
        return productDto;
    }

    /**
     * Updates an existing product.
     * 
     * @param productDto the data transfer object containing the product details.
     * @return ProductDto containing the updated product details.
     * @throws BadRequestException if the product doesn't exist.
     */
    @Override
    public ProductDto updateProduct(ProductDto productDto) throws BadRequestException {
        Optional<Products> op = repo.findById(productDto.getProductId());
        if (op.isPresent()) {
            // Save and flush the updated product to the repository
            repo.saveAndFlush(op.get());
            return productDto;
        } else {
            throw new BadRequestException("Invalid request. Please provide valid product data for updating.");
        }
    }

    /**
     * Deletes a product by its ID.
     * 
     * @param productId the ID of the product to delete.
     * @return a confirmation message.
     * @throws ResourceNotFoundException if the product doesn't exist.
     */
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

    /**
     * Retrieves a product by its ID.
     * 
     * @param productId the ID of the product to retrieve.
     * @return ProductDto containing the product details.
     * @throws ResourceNotFoundException if the product doesn't exist.
     */
    @Override
    public ProductDto getProductByID(int productId) throws ResourceNotFoundException {
        return repo.findById(productId)
                .map(e -> new ProductDto(e.getProductId(), e.getProductName(), e.getUnitPrice(), e.getColor(),
                        e.getBrand(), e.getSize(), e.getRating(), e.getOrderItems(), e.getInventories()))
                .orElseThrow(() -> new ResourceNotFoundException("Product with " + productId + " not Found"));
    }

    /**
     * Searches for products by name.
     * 
     * @param productName the name of the product to search for.
     * @return List of products that match the name.
     * @throws ResourceNotFoundException if no products are found with the given name.
     */
    @Override
    public List<Products> searchProductsByName(String productName) throws ResourceNotFoundException {
        List<Products> li = repo.searchProductByName(productName);
        if (li.isEmpty()) {
            throw new ResourceNotFoundException("Product(s) with the specified name not found.");
        }
        return li;
    }

    /**
     * Retrieves products by their brand.
     * 
     * @param brand the brand to filter products by.
     * @return List of products that match the brand.
     * @throws ResourceNotFoundException if no products are found for the given brand.
     */
    @Override
    public List<Products> getProductsByBrand(String brand) throws ResourceNotFoundException {
        List<Products> li = repo.findByBrand(brand);
        if (li.isEmpty()) {
            throw new ResourceNotFoundException("Products with the specified brand not found.");
        }
        return li;
    }

    /**
     * Retrieves products within a specific price range.
     * 
     * @param min the minimum price.
     * @param max the maximum price.
     * @return List of products within the given price range.
     * @throws BadRequestException if the price range is invalid.
     */
    @Override
    public List<Products> getProductsByPriceRange(double min, double max) throws BadRequestException {
        List<Products> li = repo.findByUnitPriceBetween(min, max);
        if (li.isEmpty()) {
            throw new BadRequestException("Invalid request. Please provide valid minimum and maximum unit prices.");
        }
        return li;
    }

    /**
     * Retrieves products sorted by a specific field.
     * 
     * @param field the field to sort by.
     * @return List of products sorted by the specified field.
     * @throws BadRequestException if the field is invalid.
     */
    @Override
    public List<Products> getProductsSortedByField(String field) throws BadRequestException {
        List<Products> li = repo.findAllByOrderByField(field);
        if (li.isEmpty()) {
            throw new BadRequestException("Invalid request. Please provide a valid field for sorting.");
        }
        return li;
    }

    /**
     * Retrieves products by color.
     * 
     * @param color the color of the products to filter by.
     * @return List of products that match the color.
     * @throws ResourceNotFoundException if no products are found for the given color.
     */
    @Override
    public List<Products> getProductsByColor(String color) throws ResourceNotFoundException {
        List<Products> li = repo.findByColor(color);
        if (li.isEmpty()) {
            throw new ResourceNotFoundException("Products with the specified color not found.");
        }
        return li;
    }
}

