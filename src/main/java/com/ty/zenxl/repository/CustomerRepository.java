package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ty.zenxl.entity.Customer;

/**
 * Interface to interact with db for {@code Customer} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByCustomerName(String customerName);

	Optional<Customer> findByCustomerId(int customerId);
	 
	Boolean existsByCustomerName(String customerName);

	@Modifying
	@Query("delete from Customer c where c.customerId=:customerId")
	void deleteCustomer(int customerId);


}
