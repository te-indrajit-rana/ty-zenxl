package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.ItemDetails;

public interface ItemRepository extends JpaRepository<ItemDetails, Integer> {

	Optional<ItemDetails> findBySerialNumber(long serialNumber);

	Boolean existsBySerialNumber(long serialNumber);

}
