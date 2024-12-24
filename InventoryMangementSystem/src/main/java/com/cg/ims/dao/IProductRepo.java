package com.cg.ims.dao;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.cg.ims.entity.Products;
 
public interface IProductRepo extends JpaRepository<Products, Integer> {
 
	List<Products> findByBrand(String brand);
 
	@Query("SELECT p FROM Products p WHERE p.productName LIKE %:productName%")
	List<Products> searchProductByName(@Param("productName") String productName);
 
	@Query("SELECT p FROM Products p WHERE p.unitPrice BETWEEN :min AND :max")
	List<Products> findByUnitPriceBetween(@Param("min") double min, @Param("max") double max);
 
	@Query("SELECT p FROM Products p ORDER BY CASE WHEN :field = 'productName' THEN p.productName WHEN :field = 'unitPrice' THEN p.unitPrice END ASC")
	List<Products> findAllByOrderByField(@Param("field") String field);
 
	@Query("SELECT p FROM Products p WHERE p.color = :color")
	List<Products> findByColor(@Param("color") String color);
 
}
