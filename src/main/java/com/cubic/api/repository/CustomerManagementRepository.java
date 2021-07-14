package com.cubic.api.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.cubic.api.model.Customer;




@Repository
@RepositoryRestResource(exported = false)
public interface CustomerManagementRepository 
	extends JpaRepository<Customer, Long> {
	

}