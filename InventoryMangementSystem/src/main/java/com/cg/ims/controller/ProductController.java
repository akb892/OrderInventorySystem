package com.cg.ims.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.cg.ims.dto.ProductDto;
import com.cg.ims.entity.Products;
import com.cg.ims.exception.BadRequestException;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.service.ProductServiceImp;
 
/**
* Controller class for managing product-related operations.
* Provides RESTful APIs for CRUD operations and other product-specific functionalities.
*/
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
 
    @Autowired
    private ProductServiceImp productService;
 
    /**
     * Retrieves all products from the database.
     *
     * @return ResponseEntity containing a list of all products.
     * @throws InternalServerErrorException if an internal server error occurs.
     */
    @GetMapping("/getall")
    public ResponseEntity<?> getAllProducts() throws InternalServerErrorException {
        List<Products> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
 
    /**
     * Adds a new product to the database.
     *
     * @param productDto The product details to be added.
     * @return ResponseEntity with a success message and status code 201 (Created).
     * @throws BadRequestException if the input data is invalid.
     */
    @PostMapping("/create")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) throws BadRequestException {
        productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }
 
    /**
     * Updates an existing product in the database.
     *
     * @param productDto The product details to be updated.
     * @return ResponseEntity with a success message.
     * @throws BadRequestException if the input data is invalid or the product does not exist.
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto) throws BadRequestException {
        productService.updateProduct(productDto);
        return ResponseEntity.ok("Record Updated Successfully");
    }
 
    /**
     * Deletes a product from the database by its ID.
     *
     * @param id The ID of the product to be deleted.
     * @return ResponseEntity with a success message.
     * @throws ResourceNotFoundException if the product with the specified ID is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) throws ResourceNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Record deleted Successfully");
    }
 
    /**
     * Searches for products by their name.
     *
     * @param productname The name of the product(s) to search for.
     * @return ResponseEntity containing a list of matching products.
     * @throws ResourceNotFoundException if no products with the specified name are found.
     */
    @GetMapping("/{productname}")
    public ResponseEntity<?> searchProductsByName(@PathVariable String productname) throws ResourceNotFoundException {
        List<Products> products = productService.searchProductsByName(productname);
        return ResponseEntity.ok(products);
    }
 
    /**
     * Searches for a product by its ID.
     *
     * @param productId The ID of the product to search for.
     * @return ResponseEntity containing the product details.
     * @throws ResourceNotFoundException if no product with the specified ID is found.
     */
    @GetMapping("/id/{productId}")
    public ResponseEntity<?> searchProductById(@PathVariable int productId) throws ResourceNotFoundException {
        ProductDto product = productService.getProductByID(productId);
        return ResponseEntity.ok(product);
    }
 
    /**
     * Retrieves products by their brand.
     *
     * @param brand The brand of the products to search for.
     * @return ResponseEntity containing a list of products with the specified brand.
     * @throws ResourceNotFoundException if no products with the specified brand are found.
     */
    @GetMapping("/brand/{brand}")
    public ResponseEntity<?> getProductsByBrand(@PathVariable String brand) throws ResourceNotFoundException {
        List<Products> products = productService.getProductsByBrand(brand);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products with the specified brand not found.");
        }
        return ResponseEntity.ok(products);
    }
 
    /**
     * Retrieves products by their color.
     *
     * @param color The color of the products to search for.
     * @return ResponseEntity containing a list of products with the specified color.
     * @throws ResourceNotFoundException if no products with the specified color are found.
     */
    @GetMapping("/colour/{color}")
    public ResponseEntity<?> getProductsByColor(@PathVariable String color) throws ResourceNotFoundException {
        List<Products> products = productService.getProductsByColor(color);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Products with the specified color not found.");
        }
        return ResponseEntity.ok(products);
    }
 
    /**
     * Retrieves products within a specified price range.
     *
     * @param min The minimum price.
     * @param max The maximum price.
     * @return ResponseEntity containing a list of products within the specified range.
     * @throws BadRequestException if the input parameters are invalid.
     */
    @GetMapping("/unitprice")
    public ResponseEntity<?> getProductsByUnitPrice(@RequestParam double min, @RequestParam double max)
            throws BadRequestException {
        List<Products> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }
 
    /**
     * Retrieves products sorted by a specified field.
     *
     * @param field The field by which to sort the products.
     * @return ResponseEntity containing a list of sorted products.
     * @throws BadRequestException if the specified field is invalid.
     */
    @GetMapping("/sort")
    public ResponseEntity<?> getProductsSorted(@RequestParam String field) throws BadRequestException {
        List<Products> products = productService.getProductsSortedByField(field);
        return ResponseEntity.ok(products);
    }
}