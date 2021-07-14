package com.cubic.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.api.exception.RecordNotFoundException;
import com.cubic.api.model.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public interface CustomerManagementController {
	@Operation(summary = "Retrieves a Customer by ID", description = "This operation retrieves a Customer resource.")

	@RequestMapping(value = "/customer/{id}", produces = {
			"application/json;charset=utf-8" }, method = RequestMethod.GET)
	ResponseEntity<Customer> retrieveCustomer(
			@Parameter(in = ParameterIn.PATH, description = "Customer Id", required = true, schema = @Schema()) @PathVariable("id") Long customerId)
			throws RecordNotFoundException;

	
	@RequestMapping(value = "/isapiavailable", produces = {
			"application/json;charset=utf-8" }, method = RequestMethod.GET)
	void isApiUp();
}
