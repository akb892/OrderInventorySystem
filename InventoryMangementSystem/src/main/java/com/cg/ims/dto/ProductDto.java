package com.cg.ims.dto;
 
import java.util.List;
 
import com.cg.ims.entity.Inventory;
import com.cg.ims.entity.OrderItems;
 
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
 
/**
* Data Transfer Object (DTO) for representing product details.
* This class is used to transfer product data between different layers
* of the application while maintaining separation of concerns.
*/
public class ProductDto {
 
    // Unique identifier for the product
    private Integer productId;
 
    // Name of the product (mandatory field)
    @NotBlank(message = "Product name is mandatory")
    private String productName;
 
    // Unit price of the product (minimum value 0)
    @DecimalMin(value = "0.0", message = "Unit price must be greater than or equal to 0")
    private Double unitPrice;
 
    // Color of the product
    private String colour;
 
    // Brand of the product
    private String brand;
 
    // Size of the product
    private String size;
 
    // Product rating (between 0 and 5)
    @Min(value = 0, message = "Rating must be greater than or equal to 0")
    @Max(value = 5, message = "Rating must be less than or equal to 5")
    private Integer rating;
 
    // List of order items associated with the product
    private List<OrderItems> orderItems;
 
    // List of inventory details associated with the product
    private List<Inventory> inventory;
 
    /**
     * Gets the list of order items associated with the product.
     *
     * @return List of order items.
     */
    public List<OrderItems> getOrderItems() {
        return orderItems;
    }
 
    /**
     * Sets the list of order items associated with the product.
     *
     * @param orderItems List of order items.
     */
    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }
 
    /**
     * Gets the inventory details associated with the product.
     *
     * @return List of inventory details.
     */
    public List<Inventory> getInventory() {
        return inventory;
    }
 
    /**
     * Sets the inventory details associated with the product.
     *
     * @param inventory List of inventory details.
     */
    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }
 
    /**
     * Gets the product ID.
     *
     * @return Product ID.
     */
    public Integer getProductId() {
        return productId;
    }
 
    /**
     * Sets the product ID.
     *
     * @param productId Product ID.
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
 
    /**
     * Gets the product name.
     *
     * @return Product name.
     */
    public String getProductName() {
        return productName;
    }
 
    /**
     * Sets the product name.
     *
     * @param productName Product name.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
 
    /**
     * Gets the unit price of the product.
     *
     * @return Unit price.
     */
    public Double getUnitPrice() {
        return unitPrice;
    }
 
    /**
     * Sets the unit price of the product.
     *
     * @param unitPrice Unit price.
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
 
    /**
     * Gets the color of the product.
     *
     * @return Product color.
     */
    public String getColour() {
        return colour;
    }
 
    /**
     * Sets the color of the product.
     *
     * @param colour Product color.
     */
    public void setColour(String colour) {
        this.colour = colour;
    }
 
    /**
     * Gets the brand of the product.
     *
     * @return Product brand.
     */
    public String getBrand() {
        return brand;
    }
 
    /**
     * Sets the brand of the product.
     *
     * @param brand Product brand.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
 
    /**
     * Gets the size of the product.
     *
     * @return Product size.
     */
    public String getSize() {
        return size;
    }
 
    /**
     * Sets the size of the product.
     *
     * @param size Product size.
     */
    public void setSize(String size) {
        this.size = size;
    }
 
    /**
     * Gets the rating of the product.
     *
     * @return Product rating.
     */
    public Integer getRating() {
        return rating;
    }
 
    /**
     * Sets the rating of the product.
     *
     * @param rating Product rating.
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }
 
    /**
     * Parameterized constructor to initialize product details.
     *
     * @param productId   Product ID.
     * @param productName Product name.
     * @param unitPrice   Unit price.
     * @param colour      Product color.
     * @param brand       Product brand.
     * @param size        Product size.
     * @param rating      Product rating.
     * @param orderItems  List of order items.
     * @param inventory   List of inventory details.
     */
    public ProductDto(Integer productId, @NotBlank(message = "Product name is mandatory") String productName,
                      @DecimalMin(value = "0.0", message = "Unit price must be greater than or equal to 0") Double unitPrice,
                      String colour, String brand, String size,
                      @Min(value = 0, message = "Rating must be greater than or equal to 0") 
                      @Max(value = 5, message = "Rating must be less than or equal to 5") Integer rating,
                      List<OrderItems> orderItems, List<Inventory> inventory) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.colour = colour;
        this.brand = brand;
        this.size = size;
        this.rating = rating;
        this.orderItems = orderItems;
        this.inventory = inventory;
    }
}
