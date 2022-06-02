package com.ty.zenxl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.zenxl.entity.Status;
import com.ty.zenxl.entity.StatusPKID;

/**
 * Interface to interact with db for {@code Status} entity class.
 * Extends the {@code JpaRepository} interface.
 * 
 * @author Indrajit
 * @verion 1.0
 */

public interface StatusRepository extends JpaRepository<Status, StatusPKID> {

	boolean existsByStatusNameAndStatusCatagory(String statusName, String statusCatagory);

	Optional<List<Status>> findAllByStatusName(String statusName);

	Optional<List<Status>> findAllByStatusCatagory(String statusCatagory);

	Optional<Status> findByStatusNameAndStatusCatagory(String statusName, String statusCatagory);

}
