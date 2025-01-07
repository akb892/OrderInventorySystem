package com.cg.ims.dao;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.cg.ims.entity.Products;
 
/**
* Repository interface for managing product-related database operations.
* Extends JpaRepository to provide CRUD operations and includes custom query methods.
*/
public interface IProductRepo extends JpaRepository<Products, Integer> {
 
    /**
     * Finds all products by the specified brand.
     *
     * @param brand The brand name to search for.
     * @return List of products matching the given brand.
     */
    List<Products> findByBrand(String brand);
 
    /**
     * Searches for products by a partial match of their name.
     *
     * @param productName The partial or full product name to search for.
     * @return List of products with names containing the given string.
     */
    @Query("SELECT p FROM Products p WHERE p.productName LIKE %:productName%")
    List<Products> searchProductByName(@Param("productName") String productName);
 
    /**
     * Finds products within the specified unit price range.
     *
     * @param min The minimum unit price.
     * @param max The maximum unit price.
     * @return List of products whose unit price falls within the specified range.
     */
    @Query("SELECT p FROM Products p WHERE p.unitPrice BETWEEN :min AND :max")
    List<Products> findByUnitPriceBetween(@Param("min") double min, @Param("max") double max);
 
    /**
     * Retrieves all products sorted dynamically by the specified field.
     * The supported fields are 'productName' and 'unitPrice'.
     *
     * @param field The field by which to sort the results.
     * @return List of products sorted by the specified field.
     */
    @Query("SELECT p FROM Products p ORDER BY CASE WHEN :field = 'productName' THEN p.productName WHEN :field = 'unitPrice' THEN p.unitPrice END ASC")
    List<Products> findAllByOrderByField(@Param("field") String field);
 
    /**
     * Finds all products by the specified color.
     *
     * @param color The color of the products to search for.
     * @return List of products matching the given color.
     */
    @Query("SELECT p FROM Products p WHERE p.color = :color")
    List<Products> findByColor(@Param("color") String color);
}
