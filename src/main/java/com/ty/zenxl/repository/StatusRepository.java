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
 * @version 1.0
 */

public interface StatusRepository extends JpaRepository<Status, StatusPKID> {

	boolean existsByStatusNameAndStatusCategory(String statusName, String statusCategory);

	Optional<List<Status>> findAllByStatusName(String statusName);

	Optional<List<Status>> findAllByStatusCategory(String statusCategory);

	Optional<Status> findByStatusNameAndStatusCategory(String statusName, String statusCategory);

}
