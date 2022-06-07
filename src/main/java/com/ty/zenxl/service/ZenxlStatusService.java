package com.ty.zenxl.service;

import static com.ty.zenxl.pojos.ZenxlConstantData.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ty.zenxl.entity.Status;
import com.ty.zenxl.exception.ChangeStatusException;
import com.ty.zenxl.exception.StatusNotFoundException;
import com.ty.zenxl.exception.StatusPersistenceException;
import com.ty.zenxl.exception.UpdateException;
import com.ty.zenxl.repository.StatusRepository;
import com.ty.zenxl.request.StatusRequest;
import com.ty.zenxl.request.UpdateStatusRequest;
import com.ty.zenxl.response.StatusCatagoryResponse;
import com.ty.zenxl.response.StatusResponse;
import com.ty.zenxl.response.ViewStatusResponse;

import lombok.RequiredArgsConstructor;

/**
 * Defines the mechanisms implemented for CRUD opeartions for a {@code Status}.
 *
 * @author Indrajit
 * @version 1.0
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ZenxlStatusService {

	private final StatusRepository statusRepository;

	public StatusResponse addStatus(StatusRequest request) {

		String statusCatagory = request.getStatusCatagory().toUpperCase();
		if (Boolean.TRUE.equals(statusRepository.existsByStatusNameAndStatusCatagory(request.getStatusName(),
				statusCatagory))) {
			throw new StatusPersistenceException(STATUS_ALEADY_EXISTS_WITH_THE_MENTIONED_STATUS_CATAGORY);
		}

		Status status = Status.builder().statusName(request.getStatusName()).statusCatagory(statusCatagory)
				.description(request.getDescription()).isActive(true).build();

		Status savedStatus = statusRepository.save(status);
		if (savedStatus.getStatusName() != null) {
			return StatusResponse.builder().statusName(savedStatus.getStatusName()).statusCatagory(savedStatus.getStatusCatagory())
					.description(savedStatus.getDescription()).isActive(savedStatus.isActive()).build();
		}
		throw new StatusPersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<StatusCatagoryResponse> findAllStatuses() {

		List<String> listOfCatagoryNames = statusRepository.findAll().stream().map(status -> status.getStatusCatagory()).distinct()
				.collect(Collectors.toList());

		List<StatusCatagoryResponse> listOfStatusCatagoryResponses = new ArrayList<StatusCatagoryResponse>();

		for (String statusCatagory : listOfCatagoryNames) {

			Optional<List<Status>> findStatusesByStatusCatagoryName = statusRepository
					.findAllByStatusCatagory(statusCatagory);

			if (findStatusesByStatusCatagoryName.isPresent()) {

				List<StatusResponse> statusResponses = findStatusesByStatusCatagoryName.get().stream()
						.map(status -> StatusResponse.builder().statusName(status.getStatusName())
								.description(status.getDescription()).isActive(status.isActive()).build())
						.collect(Collectors.toList());

				listOfStatusCatagoryResponses.add(StatusCatagoryResponse.builder().statusCatagory(statusCatagory)
						.statusResponses(statusResponses).build());
			} else {
				listOfStatusCatagoryResponses.add(StatusCatagoryResponse.builder().statusCatagory(statusCatagory)
						.statusResponses(new ArrayList<>()).build());
			}
		}
		return listOfStatusCatagoryResponses;
	}

	public List<String> findAllStatuseCatagories() {
		return statusRepository.findAll().stream().map(status -> status.getStatusCatagory()).distinct()
				.collect(Collectors.toList());
	}
	
	public String updateStatus(String statusName, String statusCatagory, UpdateStatusRequest request) {
		
		Status status = statusRepository.findByStatusNameAndStatusCatagory(statusName, statusCatagory.toUpperCase())
				.orElseThrow(() -> new StatusNotFoundException(STATUS_NOT_FOUND));
		
		if (statusRepository.existsByStatusNameAndStatusCatagory(request.getStatusName(), request.getStatusCatagory().toUpperCase())) {
			throw new UpdateException(STATUS_ALEADY_EXISTS_WITH_THE_MENTIONED_STATUS_CATAGORY);
		}

		status.setStatusName(request.getStatusName());
		status.setDescription(request.getDescription());
		status.setStatusCatagory(request.getStatusCatagory().toUpperCase());

		Status updatedStatus = statusRepository.save(status);
		if (updatedStatus != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteStatus(String statusName, String statusCatagory) {
		Status status = statusRepository.findByStatusNameAndStatusCatagory(statusName, statusCatagory.toUpperCase())
				.orElseThrow(() -> new StatusNotFoundException(
						"Status not found with status name " + statusName + "status catagory" + statusCatagory.toUpperCase()));

		statusRepository.delete(status);
		return DELETED_SUCCESSFULLY;
	}

	public String setStatus(String statusName, String statusCatagory, boolean isActive) {

		Status status = statusRepository.findByStatusNameAndStatusCatagory(statusName, statusCatagory.toUpperCase())
				.orElseThrow(() -> new StatusNotFoundException(
						"Status not found with status name " + statusName + "status catagory" + statusCatagory.toUpperCase()));

		status.setActive(isActive);
		Status updatedStatus = statusRepository.save(status);
		if (updatedStatus == null) {
			throw new ChangeStatusException(UNABLE_TO_CHANGE_USER_STATUS);
		}
		return STATUS_CHANGED_SUCCESSFULLY;
	}

	public ViewStatusResponse viewStatus(String statusName,String statusCatagory) {
		
		Status status = statusRepository.findByStatusNameAndStatusCatagory(statusName,statusCatagory.toUpperCase())
				.orElseThrow(() -> new StatusNotFoundException(STATUS_NOT_FOUND));
		ViewStatusResponse viewStatusResponse = ViewStatusResponse.builder()
				.statusCatagory(status.getStatusCatagory()).statusName(status.getStatusName())
				.description(status.getDescription()).build();

		return viewStatusResponse;
	}

}
