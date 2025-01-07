package com.cg.ims;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.ims.dao.ICustomerRepository;
import com.cg.ims.dao.IOrderRepo;
import com.cg.ims.dao.IStoreRepo;
import com.cg.ims.dto.OrdersDto;
import com.cg.ims.entity.Orders;
import com.cg.ims.exception.BadRequestException;
import com.cg.ims.exception.InternalServerErrorException;
import com.cg.ims.exception.InvalidDataException;
import com.cg.ims.exception.ResourceNotFoundException;
import com.cg.ims.service.OrderService;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private IOrderRepo orderRepo;

    @Mock
    private ICustomerRepository customerRepo;

    @Mock
    private IStoreRepo storeRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchAllOrders_whenOrdersExist_returnsOrderList() throws InternalServerErrorException {
        List<Orders> orders = Arrays.asList(new Orders(), new Orders());
        when(orderRepo.findAll()).thenReturn(orders);

        List<Orders> result = orderService.fetchAllOrders();

        assertEquals(2, result.size());
        verify(orderRepo, times(1)).findAll();
    }

    @Test
    void fetchAllOrders_whenNoOrdersExist_throwsException() {
        when(orderRepo.findAll()).thenReturn(Collections.emptyList());

        assertThrows(InternalServerErrorException.class, () -> orderService.fetchAllOrders());
        verify(orderRepo, times(1)).findAll();
    }

    @Test
    void createNewOrders_whenOrderDoesNotExist_createsOrder() throws BadRequestException {
        OrdersDto orderDto = new OrdersDto();
        orderDto.setOrderID(1);

        when(orderRepo.findById(1)).thenReturn(Optional.empty());
        doNothing().when(orderRepo).saveAndFlush(any(Orders.class));

        OrdersDto result = orderService.createNewOrders(orderDto);

        assertNotNull(result);
        verify(orderRepo, times(1)).findById(1);
        verify(orderRepo, times(1)).saveAndFlush(any(Orders.class));
    }

    @Test
    void createNewOrders_whenOrderExists_throwsException() {
        OrdersDto orderDto = new OrdersDto();
        orderDto.setOrderID(1);
        when(orderRepo.findById(1)).thenReturn(Optional.of(new Orders()));

        assertThrows(BadRequestException.class, () -> orderService.createNewOrders(orderDto));
        verify(orderRepo, times(1)).findById(1);
    }

    @Test
    void deleteOrder_whenOrderExists_deletesOrder() throws ResourceNotFoundException {
        Orders order = new Orders();
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));

        String result = orderService.deleteOrder(1);

        assertNull(result);
        verify(orderRepo, times(1)).findById(1);
        verify(orderRepo, times(1)).delete(order);
    }

    @Test
    void deleteOrder_whenOrderDoesNotExist_throwsException() {
        when(orderRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.deleteOrder(1));
        verify(orderRepo, times(1)).findById(1);
    }

    @Test
    void getOrdersByStatus_whenValidStatus_returnsOrders() throws InternalServerErrorException, InvalidDataException {
        String status = "Delivered";
        List<Orders> orders = Arrays.asList(new Orders(), new Orders());
        when(orderRepo.findByOrderStatus(status)).thenReturn(orders);

        List<OrdersDto> result = orderService.getOrdersByStatus(status);

        assertEquals(2, result.size());
        verify(orderRepo, times(1)).findByOrderStatus(status);
    }

    @Test
    void getOrdersByStatus_whenInvalidStatus_throwsException() {
        String status = "";

        assertThrows(InvalidDataException.class, () -> orderService.getOrdersByStatus(status));
    }

    @Test
    void markOrderAsCancelled_whenOrderExists_marksAsCancelled() throws ResourceNotFoundException {
        Orders order = new Orders();
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));

        orderService.markOrderAsCancelled(1);

        assertEquals("Cancelled", order.getOrderStatus());
        verify(orderRepo, times(1)).findById(1);
        verify(orderRepo, times(1)).saveAndFlush(order);
    }

    @Test
    void markOrderAsCancelled_whenOrderDoesNotExist_throwsException() {
        when(orderRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.markOrderAsCancelled(1));
        verify(orderRepo, times(1)).findById(1);
    }
}
