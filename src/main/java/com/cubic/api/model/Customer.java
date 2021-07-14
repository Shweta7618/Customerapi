
package com.cubic.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Cloneable{
	@Id
	private Long id = null;

	private String title = null;

	private String firstName = null;

	private String lastName = null;

	

	public Customer id(Long id) {
		this.id = id;
		return this;
	}


	@Schema(required = true, description = "Unique identifier for Customers.")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Customer title. 
	 * 
	 * @return title
	 **/
	@Schema(description = "Customer title.")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Customer firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * Customer first name.
	 * 
	 * @return firstName
	 **/
	@Schema(required = true, description = "Customer first name.")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Customer lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * Customer last name.
	 * 
	 * @return lastName
	 **/
	@Schema(required = true, description = "Customer last name.")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
