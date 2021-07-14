package com.cubic.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubic.api.exception.RecordNotFoundException;
import com.cubic.api.model.Customer;
import com.cubic.api.repository.CustomerManagementRepository;

@Component
public class CustomerManagementService {
	@Autowired
	CustomerManagementRepository custMgmtRepo;
	private static final Logger LOG = LoggerFactory.getLogger(CustomerManagementService.class);

	
	public Customer getCustomerDetails(Long CustomerId) throws RecordNotFoundException {

		Customer custDet = custMgmtRepo.getById(CustomerId);
		if (custDet != null) {
			//logResponse();
			return custDet;
		} else {
			throw new RecordNotFoundException("Not Found!!");
		}

	}
/**	@Scheduled(fixedDelay = 2000)
	public void logResponse() {
		 LOG.info("Current Time--"+Calendar.getInstance().getTime());
	}**/
}
