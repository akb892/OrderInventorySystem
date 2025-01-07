package com.cg.ims;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.cg.ims.dao.ICustomerRepository;
import com.cg.ims.dao.IOrderRepo;
import com.cg.ims.dao.IShipmentsRepo;
import com.cg.ims.dto.CustomerDto;
import com.cg.ims.dto.CustomerOrders;
import com.cg.ims.dto.CustomerShipment;
import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Orders;
import com.cg.ims.entity.Shipments;
import com.cg.ims.exception.BadRequestException;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.InvalidDataException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.service.CustomerService;
 
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
 
    @Mock
    private ICustomerRepository customerRepository;
 
    @Mock
    private IOrderRepo orderRepository;
 
    @Mock
    private IShipmentsRepo shipmentRepository;
 
    @InjectMocks
    private CustomerService customerService;
    private CustomerDto customerDto;
    private Customers customer;
 
    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto();
        customerDto.setCustomerId(1);
        customerDto.setFullName("John Doe");
        customerDto.setEmailAddress("john@example.com");
 
        customer = new Customers();
        customer.setCustomerId(1);
        customer.setFullName("John Doe");
        customer.setEmailAddress("john@example.com");
    }
 
    @Test
    void createCustomer_Success() throws BadRequestException, InternalServerErrorException {
        when(customerRepository.save(any(Customers.class))).thenReturn(customer);
        CustomerDto result = customerService.createCustomer(customerDto);
        assertNotNull(result);
        assertEquals(customerDto.getFullName(), result.getFullName());
        assertEquals(customerDto.getEmailAddress(), result.getEmailAddress());
    }
 
    @Test
    void createCustomer_ThrowsBadRequestException() {
        CustomerDto invalidDto = new CustomerDto();
        assertThrows(BadRequestException.class, () -> 
            customerService.createCustomer(invalidDto));
    }
 
    @Test
    void getCustomerById_Success() throws ResourceNotFoundException {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        CustomerDto result = customerService.getCustomerById(1);
        assertNotNull(result);
        assertEquals(customerDto.getCustomerId(), result.getCustomerId());
    }
 
    @Test
    void getCustomerById_ThrowsResourceNotFoundException() {
        when(customerRepository.findById(999)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> 
            customerService.getCustomerById(999));
    }
 
    @Test
    void getAllCustomers_Success() throws InternalServerErrorException {
        List<Customers> customersList = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customersList);
        List<CustomerDto> result = customerService.getAllCustomers();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
 
    @Test
    void getAllCustomers_ThrowsInternalServerErrorException() {
        when(customerRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(InternalServerErrorException.class, () -> 
            customerService.getAllCustomers());
    }
 
    @Test
    void deleteCustomer_Success() throws ResourceNotFoundException, InternalServerErrorException {
        when(customerRepository.existsById(1)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1);
        assertDoesNotThrow(() -> customerService.deleteCustomer(1));
    }
 
    @Test
    void deleteCustomer_ThrowsResourceNotFoundException() {
        when(customerRepository.existsById(999)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> 
            customerService.deleteCustomer(999));
    }
 
    @Test
    void updateCustomer_Success() throws ResourceNotFoundException, InvalidDataException, InternalServerErrorException {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customers.class))).thenReturn(customer);
        CustomerDto result = customerService.updateCustomer(customerDto);
        assertNotNull(result);
        assertEquals(customerDto.getFullName(), result.getFullName());
    }
 
    @Test
    void updateCustomer_ThrowsResourceNotFoundException() {
        when(customerRepository.findById(999)).thenReturn(Optional.empty());
        customerDto.setCustomerId(999);
        assertThrows(ResourceNotFoundException.class, () -> 
            customerService.updateCustomer(customerDto));
    }
 
    @Test
    void getCustomerByEmail_Success() throws ResourceNotFoundException {
        when(customerRepository.findByEmailAddress("john@example.com"))
            .thenReturn(Arrays.asList(customer));
        List<CustomerDto> result = customerService.getCustomerByEmail("john@example.com");
        assertFalse(result.isEmpty());
        assertEquals(customerDto.getEmailAddress(), result.get(0).getEmailAddress());
    }
 
    @Test
    void getCustomerByEmail_ThrowsResourceNotFoundException() {
        when(customerRepository.findByEmailAddress("nonexistent@example.com"))
            .thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> 
            customerService.getCustomerByEmail("nonexistent@example.com"));
    }
 
    @Test
    void getCustomerOrders_Success() throws ResourceNotFoundException {
        List<Orders> orders = Arrays.asList(new Orders());
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepository.findOrdersByCustomerId(1)).thenReturn(orders);
        CustomerOrders result = customerService.getCustomerOrders(1);
        assertNotNull(result);
        assertFalse(result.getOrder().isEmpty());
    }
 
    @Test
    void getCustomerOrders_ThrowsResourceNotFoundException() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepository.findOrdersByCustomerId(1)).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> 
            customerService.getCustomerOrders(1));
    }
 
    @Test
    void getCustomerShipments_Success() throws ResourceNotFoundException {
        List<Shipments> shipments = Arrays.asList(new Shipments());
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(shipmentRepository.findShipmentsByCustomerId(1)).thenReturn(shipments);
        CustomerShipment result = customerService.getCustomerShipments(1);
        assertNotNull(result);
        assertFalse(result.getShipment().isEmpty());
    }
 
    @Test
    void getCustomerShipments_ThrowsResourceNotFoundException() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(shipmentRepository.findShipmentsByCustomerId(1)).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> 
            customerService.getCustomerShipments(1));
    }
 
    @Test
    void getCustomersWithPendingShipments_Success() throws InternalServerErrorException {
        when(customerRepository.findCustomersWithPendingShipments())
            .thenReturn(Arrays.asList(customer));
        List<CustomerDto> result = customerService.getCustomersWithPendingShipments();
        assertFalse(result.isEmpty());
    }
 
    @Test
    void getCustomersWithPendingShipments_ThrowsInternalServerErrorException() {
        when(customerRepository.findCustomersWithPendingShipments())
            .thenReturn(Collections.emptyList());
        assertThrows(InternalServerErrorException.class, () -> 
            customerService.getCustomersWithPendingShipments());
    }
 
    @Test
    void getCustomersByOrderQuantityRange_Success() throws BadRequestException, InternalServerErrorException {
        when(customerRepository.findCustomersByOrderQuantityRange(1, 10))
            .thenReturn(Arrays.asList(customer));
        List<CustomerDto> result = customerService.getCustomersByOrderQuantityRange(1, 10);
        assertFalse(result.isEmpty());
    }
 
    @Test
    void getCustomersByOrderQuantityRange_ThrowsBadRequestException() {
        assertThrows(BadRequestException.class, () -> 
            customerService.getCustomersByOrderQuantityRange(10, 1));
    }
}
