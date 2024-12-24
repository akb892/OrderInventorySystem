package com.cg.ims.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.cg.ims.dto.OrdersDto;
import com.cg.ims.entity.Orders;
import com.cg.ims.exception.list.BadRequestException;
import com.cg.ims.exception.list.InternalServerErrorException;
import com.cg.ims.exception.list.InvalidDataException;
import com.cg.ims.exception.list.ResourceNotFoundException;

public interface IOrdersService {

    /**
     * Fetches all orders from the system.
     * 
     * @return a list of all orders.
     * @throws InternalServerErrorException if an error occurs while fetching orders.
     */
    List<Orders> fetchAllOrders() throws InternalServerErrorException;

    /**
     * Creates a new order in the system.
     * 
     * @param od the order data transfer object containing order details.
     * @return the created order details.
     * @throws BadRequestException if the provided order data is invalid.
     */
    OrdersDto createNewOrders(OrdersDto od) throws BadRequestException;

    /**
     * Updates an existing order with the provided order details.
     * 
     * @param od the order data transfer object containing updated order details.
     * @throws BadRequestException if the order data is invalid.
     */
    void updateOrdersByObject(OrdersDto od) throws BadRequestException;

    /**
     * Deletes an order from the system based on its ID.
     * 
     * @param id the ID of the order to be deleted.
     * @return a message confirming the deletion.
     * @throws ResourceNotFoundException if the order with the given ID is not found.
     */
    String deleteOrder(int id) throws ResourceNotFoundException;

    /**
     * Fetches a count of all orders in the system.
     * 
     * @return a map with the count of orders.
     * @throws InternalServerErrorException if an error occurs while fetching the count.
     */
    Map<String, Integer> countOfOrders() throws InternalServerErrorException;

    /**
     * Retrieves orders for a specific store based on its name.
     * 
     * @param storeName the name of the store.
     * @return a list of orders for the specified store.
     * @throws ResourceNotFoundException if no orders are found for the store.
     */
    List<OrdersDto> getOrdersByStoreName(String storeName) throws ResourceNotFoundException;

    /**
     * Retrieves the details of a specific order by its ID.
     * 
     * @param id the ID of the order.
     * @return the order details.
     * @throws ResourceNotFoundException if the order is not found.
     */
    OrdersDto getOrdersDetailsById(int id) throws ResourceNotFoundException;

    /**
     * Retrieves a list of orders for a specific customer based on their ID.
     * 
     * @param customerId the ID of the customer.
     * @return a list of orders for the customer.
     * @throws ResourceNotFoundException if no orders are found for the customer.
     */
    List<OrdersDto> getOrdersBySpecificCustomer(int customerId) throws ResourceNotFoundException;

    /**
     * Marks an order as cancelled by its ID.
     * 
     * @param id the ID of the order to be cancelled.
     * @throws ResourceNotFoundException if the order is not found.
     */
    void markOrderAsCancelled(int id) throws ResourceNotFoundException;

    /**
     * Retrieves a specific order by its ID.
     * 
     * @param orderId the ID of the order.
     * @return the order details.
     * @throws ResourceNotFoundException if the order is not found.
     */
    OrdersDto getSingleOrderById(int orderId) throws ResourceNotFoundException;

    /**
     * Retrieves a list of orders with a specific status (e.g., "shipped", "pending").
     * 
     * @param status the status to filter orders by.
     * @return a list of orders with the specified status.
     * @throws ResourceNotFoundException if no orders are found with the specified status.
     */
    List<OrdersDto> getOrdersByStatus(String status) throws ResourceNotFoundException;

    /**
     * Retrieves orders placed within a specific date range.
     * 
     * @param startDate the start date of the range.
     * @param endDate the end date of the range.
     * @return a list of orders within the given date range.
     * @throws ResourceNotFoundException if no orders are found within the date range.
     */
    List<OrdersDto> getOrderWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) throws ResourceNotFoundException;

    /**
     * Retrieves orders placed by a customer identified by their email address.
     * 
     * @param email the email address of the customer.
     * @return a list of orders placed by the customer.
     * @throws ResourceNotFoundException if no orders are found for the specified email.
     * @throws InvalidDataException if the email format is invalid.
     */
    List<OrdersDto> getOrderByCustomerEmail(String email) throws ResourceNotFoundException, InvalidDataException;
}

