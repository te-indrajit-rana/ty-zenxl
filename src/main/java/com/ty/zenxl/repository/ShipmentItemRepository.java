package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.ShipmentItem;

public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Integer> {

	Optional<ShipmentItem> findByInternalOrderNumber(Long internalOrderNumber);

	Boolean existsByInternalOrderNumber(Long internalOrderNumber);

}
