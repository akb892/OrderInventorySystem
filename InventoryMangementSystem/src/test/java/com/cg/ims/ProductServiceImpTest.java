package com.cg.ims;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.ims.dao.IProductRepo;
import com.cg.ims.dto.ProductDto;
import com.cg.ims.entity.Products;
import com.cg.ims.exception.BadRequestException;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.service.ProductServiceImp;

@ExtendWith(MockitoExtension.class)
class ProductServiceImpTest {

    @Mock
    private IProductRepo productRepo;

    @InjectMocks
    private ProductServiceImp productService;

    private Products product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        product = new Products();
        product.setProductId(1);
        product.setProductName("Test Product");
        product.setUnitPrice(100.0);
        product.setColor("Red");
        product.setBrand("Test Brand");
        product.setSize("M");

        productDto = new ProductDto();
        productDto.setProductId(1);
        productDto.setProductName("Test Product");
        productDto.setUnitPrice(100.0);
        productDto.setColour("Red");
        productDto.setBrand("Test Brand");
        productDto.setSize("M");
    }

    @Test
    void getAllProducts_ShouldReturnProducts() throws InternalServerErrorException {
        when(productRepo.findAll()).thenReturn(Arrays.asList(product));

        List<Products> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getProductName());
    }

    @Test
    void getAllProducts_ShouldThrowException_WhenNoProductsFound() {
        when(productRepo.findAll()).thenReturn(Arrays.asList());

        assertThrows(InternalServerErrorException.class, () -> productService.getAllProducts());
    }

    @Test
    void addProduct_ShouldAddProduct() throws BadRequestException {
        when(productRepo.findById(productDto.getProductId())).thenReturn(Optional.empty());

        ProductDto result = productService.addProduct(productDto);

        assertEquals("Test Product", result.getProductName());
    }

    @Test
    void addProduct_ShouldThrowException_WhenProductAlreadyExists() {
        when(productRepo.findById(productDto.getProductId())).thenReturn(Optional.of(product));

        assertThrows(BadRequestException.class, () -> productService.addProduct(productDto));
    }

    @Test
    void updateProduct_ShouldUpdateProduct() throws BadRequestException {
        when(productRepo.findById(productDto.getProductId())).thenReturn(Optional.of(product));

        ProductDto result = productService.updateProduct(productDto);

        assertEquals("Test Product", result.getProductName());
    }

    @Test
    void updateProduct_ShouldThrowException_WhenProductNotFound() {
        when(productRepo.findById(productDto.getProductId())).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> productService.updateProduct(productDto));
    }

    @Test
    void deleteProduct_ShouldDeleteProduct() throws ResourceNotFoundException {
        when(productRepo.findById(product.getProductId())).thenReturn(Optional.of(product));

        String result = productService.deleteProduct(product.getProductId());

        assertEquals("Product Deleted", result);
    }

    @Test
    void deleteProduct_ShouldThrowException_WhenProductNotFound() {
        when(productRepo.findById(product.getProductId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(product.getProductId()));
    }

    @Test
    void getProductByID_ShouldReturnProduct() throws ResourceNotFoundException {
        when(productRepo.findById(product.getProductId())).thenReturn(Optional.of(product));

        ProductDto result = productService.getProductByID(product.getProductId());

        assertEquals("Test Product", result.getProductName());
    }

    @Test
    void getProductByID_ShouldThrowException_WhenProductNotFound() {
        when(productRepo.findById(product.getProductId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductByID(product.getProductId()));
    }

    @Test
    void searchProductsByName_ShouldReturnProducts() throws ResourceNotFoundException {
        when(productRepo.searchProductByName("Test Product")).thenReturn(Arrays.asList(product));

        List<Products> result = productService.searchProductsByName("Test Product");

        assertEquals(1, result.size());
    }

    @Test
    void searchProductsByName_ShouldThrowException_WhenNoProductsFound() {
        when(productRepo.searchProductByName("Invalid Product")).thenReturn(Arrays.asList());

        assertThrows(ResourceNotFoundException.class, () -> productService.searchProductsByName("Invalid Product"));
    }
}
