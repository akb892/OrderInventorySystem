package com.cg.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ims.service.interfaces.ICustomerService;

@RestController
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	private ICustomerService cs;

}
