package com.cubic.api.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.exception.RecordNotFoundException;
import com.cubic.api.model.Customer;
import com.cubic.api.service.CustomerManagementService;

@RestController
public class CustomerManagementImpl implements CustomerManagementController {
	@Autowired
	CustomerManagementService custMgmtService;
	@Autowired
	HttpServletResponse httpServletResponse;
	
	@Autowired
	Environment env;
	private static final Logger LOG = LoggerFactory.getLogger(CustomerManagementImpl.class);

	@Override
	public ResponseEntity<Customer> retrieveCustomer(Long customerId) throws RecordNotFoundException {

		return new ResponseEntity<>(custMgmtService.getCustomerDetails(customerId), HttpStatus.OK);
	}

	@Scheduled(fixedRateString  = "${schedule.fixedDelay}")
	@Override
	public void isApiUp() {
		// TODO Auto-generated method stub
		LOG.info("Current Time--" + Calendar.getInstance().getTime() + httpServletResponse.getStatus());
	}

}
