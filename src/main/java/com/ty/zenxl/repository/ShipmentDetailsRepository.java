package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.ShipmentDetails;

public interface ShipmentDetailsRepository extends JpaRepository<ShipmentDetails, Integer> {

	Optional<ShipmentDetails> findByWayBillNumber(String wayBillNumber);

	Boolean existsByWayBillNumber(String wayBillNumber);

}
