package com.ty.zenxl.repository;

/**
 * Interface to interact with db for {@code Address} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findByAddressId(int addressId);
	boolean existsByAddressId(int addressId);
}
