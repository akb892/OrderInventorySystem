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
	public CustomerDto createCustomer(CustomerDto customerDTO) throws BadRequestException, InternalServerErrorException;
	public CustomerDto getCustomerById(Integer id) throws ResourceNotFoundException;
    public List<CustomerDto> getAllCustomers() throws InternalServerErrorException;
    public void deleteCustomer(Integer id) throws ResourceNotFoundException, InternalServerErrorException;
    public CustomerDto updateCustomer(CustomerDto customerDto) throws ResourceNotFoundException, InvalidDataException, InternalServerErrorException;
    public List<CustomerDto> getCustomerByEmail(String email) throws ResourceNotFoundException;
    public List<CustomerDto> getCustomerByName(String name) throws ResourceNotFoundException;
 
 
 
   public List<ShipmentStatusCountCustomer> getCustomerCountByStatus() throws InternalServerErrorException;
   public CustomerOrders getCustomerOrders(int customerId) throws ResourceNotFoundException;
   public CustomerShipment getCustomerShipments(int customerId) throws ResourceNotFoundException; //throws ResourceNotFoundException;
   List<CustomerDto> getCustomersWithPendingShipments() throws InternalServerErrorException;
   List<CustomerDto> getCustomersWithCompletedOrders() throws InternalServerErrorException;
   List<CustomerDto> getCustomersWithOverdueShipments() throws InternalServerErrorException;
   List<CustomerDto> getCustomersByOrderQuantityRange(int min, int max) throws BadRequestException, InternalServerErrorException;
}