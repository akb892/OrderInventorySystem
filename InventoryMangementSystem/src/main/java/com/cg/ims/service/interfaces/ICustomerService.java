package com.cg.ims.service.interfaces;

import java.util.List;
import java.util.Map;

import org.hibernate.ResourceClosedException;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.cg.ims.dto.CustomerDto;
import com.cg.ims.dto.CustomerOrders;
import com.cg.ims.dto.CustomerShipment;
import com.cg.ims.dto.OrdersDto;
import com.cg.ims.dto.ShipmentStatusCountCustomer;
import com.cg.ims.dto.ShipmentsDto;
import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Orders;
import com.cg.ims.entity.Shipments;
import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.InvalidDataException;
import com.cg.ims.exception.list.ResourceNotFoundException;

public interface ICustomerService {
    
    /**
     * Creates a new customer.
     * @param customerDTO the customer data to be created.
     * @return the created customer data transfer object (DTO).
     * @throws BadRequestException if the provided data is invalid.
     * @throws InternalServerErrorException if there is an internal server error during the creation process.
     */
    public CustomerDto createCustomer(CustomerDto customerDTO) throws BadRequestException, InternalServerErrorException;

    /**
     * Fetches a customer by their unique ID.
     * @param id the unique ID of the customer.
     * @return the customer data transfer object (DTO).
     * @throws ResourceNotFoundException if no customer is found for the provided ID.
     */
    public CustomerDto getCustomerById(Integer id) throws ResourceNotFoundException;

    /**
     * Fetches a list of all customers.
     * @return a list of customer data transfer objects (DTOs).
     * @throws InternalServerErrorException if there is an internal server error while fetching the customers.
     */
    public List<CustomerDto> getAllCustomers() throws InternalServerErrorException;

    /**
     * Deletes a customer by their unique ID.
     * @param id the unique ID of the customer to be deleted.
     * @throws ResourceNotFoundException if no customer is found for the provided ID.
     * @throws InternalServerErrorException if there is an internal server error during the deletion process.
     */
    public void deleteCustomer(Integer id) throws ResourceNotFoundException, InternalServerErrorException;

    /**
     * Updates an existing customer.
     * @param customerDto the customer data to be updated.
     * @return the updated customer data transfer object (DTO).
     * @throws ResourceNotFoundException if no customer is found for the provided ID.
     * @throws InvalidDataException if the provided data is invalid.
     * @throws InternalServerErrorException if there is an internal server error during the update process.
     */
    public CustomerDto updateCustomer(CustomerDto customerDto) throws ResourceNotFoundException, InvalidDataException, InternalServerErrorException;

    /**
     * Fetches a list of customers based on their email.
     * @param email the email address of the customers.
     * @return a list of customer data transfer objects (DTOs).
     * @throws ResourceNotFoundException if no customers are found for the provided email.
     */
    public List<CustomerDto> getCustomerByEmail(String email) throws ResourceNotFoundException;

    /**
     * Fetches a list of customers based on their name.
     * @param name the name of the customers.
     * @return a list of customer data transfer objects (DTOs).
     * @throws ResourceNotFoundException if no customers are found for the provided name.
     */
    public List<CustomerDto> getCustomerByName(String name) throws ResourceNotFoundException;

    /**
     * Fetches a list of customers and their shipment status count.
     * @return a list of shipment status count for each customer.
     * @throws InternalServerErrorException if there is an internal server error during the process.
     */
    public List<ShipmentStatusCountCustomer> getCustomerCountByStatus() throws InternalServerErrorException;

    /**
     * Fetches the orders for a specific customer by their ID.
     * @param customerId the unique ID of the customer.
     * @return the customer orders data transfer object (DTO).
     * @throws ResourceNotFoundException if no orders are found for the provided customer ID.
     */
    public CustomerOrders getCustomerOrders(int customerId) throws ResourceNotFoundException;

    /**
     * Fetches the shipments for a specific customer by their ID.
     * @param customerId the unique ID of the customer.
     * @return the customer shipments data transfer object (DTO).
     * @throws ResourceNotFoundException if no shipments are found for the provided customer ID.
     */
    public CustomerShipment getCustomerShipments(int customerId) throws ResourceNotFoundException;

    /**
     * Fetches a list of customers who have pending shipments.
     * @return a list of customers with pending shipments.
     * @throws InternalServerErrorException if there is an internal server error during the process.
     */
    List<CustomerDto> getCustomersWithPendingShipments() throws InternalServerErrorException;

    /**
     * Fetches a list of customers who have completed orders.
     * @return a list of customers with completed orders.
     * @throws InternalServerErrorException if there is an internal server error during the process.
     */
    List<CustomerDto> getCustomersWithCompletedOrders() throws InternalServerErrorException;

    /**
     * Fetches a list of customers with overdue shipments.
     * @return a list of customers with overdue shipments.
     * @throws InternalServerErrorException if there is an internal server error during the process.
     */
    List<CustomerDto> getCustomersWithOverdueShipments() throws InternalServerErrorException;

    /**
     * Fetches a list of customers whose order quantity is within a specified range.
     * @param min the minimum order quantity.
     * @param max the maximum order quantity.
     * @return a list of customers who meet the specified order quantity range.
     * @throws BadRequestException if the provided order quantity range is invalid.
     * @throws InternalServerErrorException if there is an internal server error during the process.
     */
    List<CustomerDto> getCustomersByOrderQuantityRange(int min, int max) throws BadRequestException, InternalServerErrorException;
}
