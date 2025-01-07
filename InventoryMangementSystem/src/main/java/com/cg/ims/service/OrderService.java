package com.cg.ims.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import com.cg.ims.dao.ICustomerRepository;
import com.cg.ims.dao.IOrderRepo;
import com.cg.ims.dao.IStoreRepo;
import com.cg.ims.dto.CustomerDto;
import com.cg.ims.dto.OrdersDto;
import com.cg.ims.entity.Customers;
import com.cg.ims.entity.Orders;
import com.cg.ims.entity.Stores;
import com.cg.ims.exception.BadRequestException;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.InvalidDataException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.service.interfaces.IOrdersService;

/**
 * Service class for handling order-related operations.
 */
@Service
public class OrderService implements IOrdersService {

    // Repositories for accessing the database
    @Autowired
    private IOrderRepo repo;
    @Autowired
    private ICustomerRepository repo1;
    @Autowired
    private IStoreRepo repo2;

    /**
     * Fetches all orders from the database.
     * 
     * @return List of all orders.
     * @throws InternalServerErrorException if there is an error fetching orders.
     */
    @Override
    public List<Orders> fetchAllOrders() throws InternalServerErrorException {
        List<Orders> li = repo.findAll();
        if (li.isEmpty()) {
            throw new InternalServerErrorException("An internal server error occurred while fetching all Orders");
        } else {
            return li;
        }
        
    }

    /**
     * Creates a new order.
     * 
     * @param od the order data transfer object (DTO) containing order details.
     * @return the created order DTO.
     * @throws BadRequestException if the order already exists.
     */
    @Override
    public OrdersDto createNewOrders(OrdersDto od) throws BadRequestException {
        Orders o = new Orders();
        Optional<Customers> op = repo1.findById(od.getCustomer().getCustomerId());
        if (op.isPresent()) {
            throw new BadRequestException("Invalid Request. Please provide Valid order Data for creation");
        } else {
            o.setCustomer(od.getCustomer());
            o.setOi(od.getOi());
            o.setOrderID(od.getOrderID());
            o.setOrderStatus(od.getOrderStatus());
            o.setOrderTms(od.getOrderTms());
            o.setStore(od.getStore());
            repo.saveAndFlush(o);
            return od;
        }
    }

    /**
     * Updates an existing order by its object.
     * 
     * @param od the order data transfer object (DTO) containing updated order details.
     * @throws BadRequestException if the order does not exist.
     */
    @Override
    public void updateOrdersByObject(OrdersDto od) throws BadRequestException {
        Orders o = new Orders();
        Optional<Orders> op = repo.findById(od.getOrderID());
        if (op.isPresent()) {
            o.setCustomer(od.getCustomer());
            o.setOi(od.getOi());
            o.setOrderID(od.getOrderID());
            o.setOrderStatus(od.getOrderStatus());
            o.setOrderTms(od.getOrderTms());
            o.setStore(od.getStore());
            repo.saveAndFlush(o);
        } else {
            throw new BadRequestException("Invalid request. Please provide valid order data for updating");
        }
    }

    /**
     * Deletes an order by its ID.
     * 
     * @param id the ID of the order to be deleted.
     * @return a success message.
     * @throws ResourceNotFoundException if the order is not found.
     */
    @Override
    public String deleteOrder(int id) throws ResourceNotFoundException {
        Optional<Orders> op = repo.findById(id);
        if (op.isPresent()) {
            repo.delete(op.get());
        } else {
            throw new ResourceNotFoundException("Order with the specified id not found for deletion");
        }
        return null;
    }

    /**
     * Retrieves the count of orders grouped by their status.
     * 
     * @return a map of order statuses and their corresponding counts.
     * @throws InternalServerErrorException if there is an error fetching the order count.
     */
    @Override
    public Map<String, Integer> countOfOrders() throws InternalServerErrorException {
    	try {
            // Fetch data from the database
            List<Object[]> result = repo.countOrdersGroupedByStatus();

            // Convert the result into a map
            return result.stream()
                         .collect(Collectors.toMap(
                             row -> (String) row[0], // Order status
                             row -> ((Number) row[1]).intValue() // Count
                         ));
        } catch (Exception e) {
            // Handle unexpected errors
            throw new InternalServerErrorException("An error occurred while fetching order counts.");
        }
    }

    /**
     * Retrieves orders for a specific store by its name.
     * 
     * @param storeName the name of the store.
     * @return a list of orders for that store.
     * @throws ResourceNotFoundException if no orders are found for the store.
     */
    @Override
    public List<OrdersDto> getOrdersByStoreName(String storeName) throws ResourceNotFoundException {
        List<OrdersDto> od = new ArrayList<>();
        List<Stores> s = repo2.findByStoreName(storeName);
        for (Stores s1 : s) {
            List<Orders> li = new ArrayList<>(s1.getOrders());
            if (li.isEmpty()) {
                throw new ResourceNotFoundException("Orders with the specified store name not found.");
            }
            OrdersDto od1 = new OrdersDto();
            for (Orders o : li) {
                od1.setCustomer(o.getCustomer());
                od1.setOi(o.getOi());
                od1.setOrderID(o.getOrderID());
                od1.setOrderStatus(o.getOrderStatus());
                od1.setOrderTms(o.getOrderTms());
                od1.setStore(o.getStore());
                od.add(od1);
            }
        }
        return od;
    }

    /**
     * Retrieves order details by order ID.
     * 
     * @param id the ID of the order.
     * @return the order details DTO.
     * @throws ResourceNotFoundException if the order is not found.
     */
    @Override
    public OrdersDto getOrdersDetailsById(int id) throws ResourceNotFoundException {
        List<Orders> o = new ArrayList<>(repo.findByOrderID(id));
        if (o.isEmpty()) {
            throw new ResourceNotFoundException("Order with the specified ID not found.");
        }
        OrdersDto od = new OrdersDto();
        for (Orders o1 : o) {
            od.setCustomer(o1.getCustomer());
            od.setOrderID(o1.getOrderID());
            od.setOrderStatus(o1.getOrderStatus());
            od.setOrderTms(o1.getOrderTms());
            od.setStore(o1.getStore());
        }
        return od;
    }

    /**
     * Retrieves orders for a specific customer by their customer ID.
     * 
     * @param customerId the ID of the customer.
     * @return a list of orders for that customer.
     * @throws ResourceNotFoundException if no orders are found for the customer.
     */
    @Override
    public List<OrdersDto> getOrdersBySpecificCustomer(int customerId) throws ResourceNotFoundException {
        CustomerDto co = new CustomerDto();
        co.setCustomerId(customerId);
        if (co.getCustomerId() > 0) {
            Optional<Customers> op = repo1.findById(customerId);
            if (op.isPresent()) {
                Customers c = op.get();
                List<Orders> o = new ArrayList<>(c.getOrder());
                List<OrdersDto> li = new ArrayList<>();
                for (Orders o1 : o) {
                    OrdersDto od = new OrdersDto();
                    od.setCustomer(o1.getCustomer());
                    od.setOi(o1.getOi());
                    od.setOrderID(o1.getOrderID());
                    od.setOrderStatus(o1.getOrderStatus());
                    od.setOrderTms(o1.getOrderTms());
                    od.setStore(o1.getStore());
                    li.add(od);
                }
                return li;
            } else {
                throw new ResourceNotFoundException("Orders for the specified customer ID not found.");
            }
        } else {
            return null;
        }
    }

    /**
     * Marks an order as cancelled by its ID.
     * 
     * @param id the ID of the order to be cancelled.
     * @throws ResourceNotFoundException if the order is not found.
     */
    @Override
    public void markOrderAsCancelled(int id) throws ResourceNotFoundException {
        OrdersDto od = new OrdersDto();
        od.setOrderID(id);
        if (od.getOrderID() > 0) {
            Optional<Orders> op = repo.findById(id);
            if (op.isPresent()) {
                Orders o = op.get();
                o.setOrderStatus("Cancelled");
                repo.saveAndFlush(o);
            } else {
                throw new ResourceNotFoundException("Order with the specified ID not found for cancellation.");
            }
        }
    }

    /**
     * Retrieves a single order by its order ID.
     * 
     * @param orderId the ID of the order.
     * @return the order details DTO.
     * @throws ResourceNotFoundException if the order is not found.
     */
    @Override
    public OrdersDto getSingleOrderById(int orderId) throws ResourceNotFoundException {
//        Optional<Orders> op = repo.findById(orderId);
//        if (op.isPresent()) {
//            Orders o = op.get();
//            OrdersDto od = new OrdersDto();
//            od.setCustomer(o.getCustomer());
//            od.setOrderID(o.getOrderID());
//            od.setOrderStatus(o.getOrderStatus());
//            od.setOrderTms(o.getOrderTms());
//            od.setStore(o.getStore());
//            return od;
//        } else {
//            throw new ResourceNotFoundException("Order with the specified order ID not found.");
//        }
    	
          Orders o = repo.findSingleOrderById(orderId);
          if(o != null) {
          OrdersDto od = new OrdersDto();
          od.setCustomer(o.getCustomer());
          od.setOrderID(o.getOrderID());
          od.setOrderStatus(o.getOrderStatus());
          od.setOrderTms(o.getOrderTms());
          od.setStore(o.getStore());
          return od;
      } else {
          throw new ResourceNotFoundException("Order with the specified order ID not found.");
      }
    }

    /**
     * Retrieves orders by their status.
     * 
     * @param status the status of the orders to be retrieved.
     * @return a list of orders matching the specified status.
     * @throws ResourceNotFoundException if no orders are found for the specified status.
     * @throws InvalidDataException 
     */
    @Override
    public List<OrdersDto> getOrdersByStatus(String status) throws InternalServerErrorException, InvalidDataException {
    	if (status == null || status.trim().isEmpty()) {
            throw new InvalidDataException("Status cannot be null or empty.");
        }

        // Fetch orders from the database
        List<Orders> orders = repo.findByOrderStatus(status);

        // Handle no orders found
        if (orders.isEmpty()) {
            throw new InternalServerErrorException("An internal server error occurred while fetching the count of orders by status.");
        }

        // Convert entities to DTOs
        return orders.stream()
                     .map(order -> new OrdersDto(
                         order.getOrderID(),
                         order.getOrderTms(),
                         order.getCustomer(),
                         order.getOrderStatus(),
                         order.getStore(),
                         order.getOi()))
                     .collect(Collectors.toList());
    }
    

    /**
     * Retrieves orders within a specific date range.
     * 
     * @param startDate the start date of the range.
     * @param endDate the end date of the range.
     * @return a list of orders within the date range.
     * @throws ResourceNotFoundException if no orders are found within the date range.
     */
    @Override
    public List<OrdersDto> getOrderWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) throws ResourceNotFoundException {
        Assert.notNull(startDate, "Start Date cannot be null");
        Assert.notNull(endDate, "End Date cannot be null");
        OrdersDto od = new OrdersDto();
        List<OrdersDto> li = new ArrayList<>();
        if (startDate.isBefore(endDate)) {
            List<Orders> o = repo.findOrderWithinDateRange(startDate, endDate);
            if (o.isEmpty()) {
                throw new ResourceNotFoundException("Orders within the specified date range not found.");
            }
            for (Orders or : o) {
                od.setCustomer(or.getCustomer());
                od.setOi(or.getOi());
                od.setOrderID(or.getOrderID());
                od.setOrderStatus(or.getOrderStatus());
                od.setOrderTms(or.getOrderTms());
                od.setStore(or.getStore());
                li.add(od);
            }
            return li;
        } else {
            return null;
        }
    }

    /**
     * Retrieves orders by the customer's email.
     * 
     * @param email the email of the customer.
     * @return a list of orders for the customer.
     * @throws ResourceNotFoundException if no orders are found for the email.
     * @throws InvalidDataException if the email is invalid.
     */
    @Override
    public List<OrdersDto> getOrderByCustomerEmail(String email) throws ResourceNotFoundException, InvalidDataException {
        String regex = "^[^@].*[@]+.*[^@]$";
        if (!email.matches(regex)) {
            throw new InvalidDataException("Invalid Email!");
        }
        List<OrdersDto> od = new ArrayList<>();
        List<Customers> c = repo1.findByEmailAddress(email);
        for (Customers c1 : c) {
            List<Orders> o = new ArrayList<>(c1.getOrder());
            if (o.isEmpty()) {
                throw new ResourceNotFoundException("Orders for the specified customer email not found.");
            }
            OrdersDto od1 = new OrdersDto();
            for (Orders o1 : o) {
                od1.setCustomer(o1.getCustomer());
                od1.setOi(o1.getOi());
                od1.setOrderID(o1.getOrderID());
                od1.setOrderStatus(o1.getOrderStatus());
                od1.setOrderTms(o1.getOrderTms());
                od1.setStore(o1.getStore());
                od.add(od1);
            }
        }
        return od;
    }
    
    
}
