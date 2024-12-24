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
 
public interface ICustomerService {
	public CustomerDto createCustomer(CustomerDto customerDTO);
	public CustomerDto getCustomerById(Integer id);
    public List<CustomerDto> getAllCustomers();
    public void deleteCustomer(Integer id);
    public CustomerDto updateCustomer(CustomerDto customerDto);
    public List<CustomerDto> getCustomerByEmail(String email);
    public List<CustomerDto> getCustomerByName(String name);


 
   public List<ShipmentStatusCountCustomer> getCustomerCountByStatus();
   public CustomerOrders getCustomerOrders(int customerId);
   public CustomerShipment getCustomerShipments(int customerId); //throws ResourceNotFoundException;
   List<CustomerDto> getCustomersWithPendingShipments();
   List<CustomerDto> getCustomersWithCompletedOrders();
   List<CustomerDto> getCustomersWithOverdueShipments();
   List<CustomerDto> getCustomersByOrderQuantityRange(int min, int max);
}

