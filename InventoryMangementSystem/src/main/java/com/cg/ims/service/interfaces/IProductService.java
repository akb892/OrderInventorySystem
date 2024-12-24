package com.cg.ims.service.interfaces;

import java.util.List;

import com.cg.ims.dto.ProductDto;
import com.cg.ims.entity.Products;
import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.ResourceNotFoundException;

public interface IProductService {

    /**
     * Retrieves all products from the system.
     * 
     * @return a list of all products.
     * @throws InternalServerErrorException if an error occurs while fetching the products.
     */
    List<Products> getAllProducts() throws InternalServerErrorException;

    /**
     * Adds a new product to the system.
     * 
     * @param productDto the product data transfer object containing product details.
     * @return the added product details.
     * @throws BadRequestException if the provided product data is invalid.
     */
    ProductDto addProduct(ProductDto productDto) throws BadRequestException;

    /**
     * Updates an existing product in the system.
     * 
     * @param productDto the product data transfer object containing updated product details.
     * @return the updated product details.
     * @throws BadRequestException if the product data is invalid.
     */
    ProductDto updateProduct(ProductDto productDto) throws BadRequestException;

    /**
     * Deletes a product from the system based on its ID.
     * 
     * @param productId the ID of the product to be deleted.
     * @return a confirmation message of deletion.
     * @throws ResourceNotFoundException if the product with the given ID is not found.
     */
    String deleteProduct(int productId) throws ResourceNotFoundException;

    /**
     * Searches for products by their name.
     * 
     * @param productName the name of the product to search for.
     * @return a list of products that match the specified name.
     * @throws ResourceNotFoundException if no products are found with the specified name.
     */
    List<Products> searchProductsByName(String productName) throws ResourceNotFoundException;

    /**
     * Retrieves products by their brand.
     * 
     * @param brand the brand of the products to search for.
     * @return a list of products that match the specified brand.
     * @throws ResourceNotFoundException if no products are found with the specified brand.
     */
    List<Products> getProductsByBrand(String brand) throws ResourceNotFoundException;

    /**
     * Retrieves product details by the product ID.
     * 
     * @param productId the ID of the product.
     * @return the product details.
     * @throws ResourceNotFoundException if the product with the given ID is not found.
     */
    ProductDto getProductByID(int productId) throws ResourceNotFoundException;

    /**
     * Retrieves products within a specific price range.
     * 
     * @param min the minimum price.
     * @param max the maximum price.
     * @return a list of products within the specified price range.
     * @throws BadRequestException if the price range is invalid.
     */
    public List<Products> getProductsByPriceRange(double min, double max) throws BadRequestException;

    /**
     * Retrieves products sorted by a specific field.
     * 
     * @param field the field to sort products by (e.g., "name", "price").
     * @return a list of products sorted by the specified field.
     * @throws BadRequestException if the specified field is invalid.
     */
    public List<Products> getProductsSortedByField(String field) throws BadRequestException;

    /**
     * Retrieves products by their color.
     * 
     * @param color the color of the products to search for.
     * @return a list of products that match the specified color.
     * @throws ResourceNotFoundException if no products are found with the specified color.
     */
    List<Products> getProductsByColor(String color) throws ResourceNotFoundException;
}
