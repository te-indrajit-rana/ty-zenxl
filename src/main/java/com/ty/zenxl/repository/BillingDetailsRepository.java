package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.BillingDetails;

/**
 * Interface to interact with db for {@code BillingDetails} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Integer> {

	Optional<BillingDetails> findByBillingName(String billingName);
	boolean existsByBillingDetailsId(int billingDetailsId);
}
