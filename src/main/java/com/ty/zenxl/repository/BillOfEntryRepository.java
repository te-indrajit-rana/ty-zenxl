package com.ty.zenxl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.BillOfEntry;

public interface BillOfEntryRepository extends JpaRepository<BillOfEntry, Integer> {

	Optional<BillOfEntry> findByBillOfEntryId(Integer billOfEntryId);

	Optional<BillOfEntry> findByBoeNumber(Long boeNumber);

	Optional<BillOfEntry> findByInvoiceNumber(Long invoiceNumber);

	Boolean existsByBoeNumber(Long boeNumber);
}
