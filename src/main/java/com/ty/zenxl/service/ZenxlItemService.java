package com.ty.zenxl.service;

import static com.ty.zenxl.pojos.ZenxlConstantData.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.zenxl.entity.Certificate;
import com.ty.zenxl.entity.CertificateType;
import com.ty.zenxl.entity.Code;
import com.ty.zenxl.entity.CodeType;
import com.ty.zenxl.entity.ItemDetails;
import com.ty.zenxl.exception.CertificateException;
import com.ty.zenxl.exception.DataException;
import com.ty.zenxl.exception.ItemException;
import com.ty.zenxl.repository.CertificateRepository;
import com.ty.zenxl.repository.CertificateTypeRepository;
import com.ty.zenxl.repository.CodeTypeRepository;
import com.ty.zenxl.repository.ItemRepository;
import com.ty.zenxl.request.CertificateUpdateRequest;
import com.ty.zenxl.request.CertificateUploadRequest;
import com.ty.zenxl.request.CodeRequest;
import com.ty.zenxl.request.ItemRequest;
import com.ty.zenxl.request.ItemUpdateRequest;
import com.ty.zenxl.response.CodeResponse;
import com.ty.zenxl.response.FileResponse;
import com.ty.zenxl.response.Item;
import com.ty.zenxl.response.ItemDashboardResponse;
import com.ty.zenxl.response.ItemDetailsResponse;
import com.ty.zenxl.response.ItemResponse;
import com.ty.zenxl.response.ViewCertificate;
import com.ty.zenxl.response.ViewCertificateList;

import lombok.RequiredArgsConstructor;

/**
 * service class for part/item details
 * 
 * @author Abhishek
 * @version 1.0
 *
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ZenxlItemService {

	private final Validator validator;

	private final ItemRepository itemRepository;

	private final CertificateRepository certificateRepository;

	private final CertificateTypeRepository certificateTypeRepository;

	private final CodeTypeRepository codeTypeRepository;

	private final ObjectMapper mapper = new ObjectMapper();

	private Path directory;

	@Value("${file.item.upload}")
	private String pathInfo;

	/**
	 * get file path
	 * 
	 */
	private Path getPath(String certificateName) {
		String dir = pathInfo + "\\" + certificateName;
		directory = Paths.get(dir).toAbsolutePath().normalize();
		return directory;
	}

	/**
	 * upload certificate details
	 * 
	 * @throws JsonProcessingException
	 */
	private CertificateUploadRequest uploadCertificateDetails(String data) throws JsonProcessingException {
		return mapper.readValue(data, CertificateUploadRequest.class);
	}

	/**
	 * update certificate details
	 * 
	 * @throws JsonProcessingException
	 */
	private CertificateUpdateRequest updateCertificateDetails(String data) throws JsonProcessingException {
		return mapper.readValue(data, CertificateUpdateRequest.class);
	}

	/**
	 * upload certificate
	 * 
	 * @throws IOException
	 */
	public FileResponse uploadCertificate(MultipartFile file, String data) throws IOException {
		if (file.isEmpty()) {
			throw new CertificateException(PLEASE_SELECT_DOCUMENT);
		} else if (data.isEmpty()) {
			throw new DataException(DATA_NOT_EMPTY);
		} else {
			CertificateUploadRequest certificateUpload = null;
			try {
				certificateUpload = uploadCertificateDetails(data);

				Set<ConstraintViolation<CertificateUploadRequest>> violations = validator.validate(certificateUpload);

				if (!violations.isEmpty()) {
					throw new ConstraintViolationException(violations);
				}
				if (Boolean.TRUE.equals(
						certificateRepository.existsByCertificateNumber(certificateUpload.getCertificateNumber()))) {
					throw new CertificateException(CERTIFICATE_ALREADY_EXISTS);
				}
				directory = getPath(file.getOriginalFilename());
				Path path = directory.resolve(file.getOriginalFilename());
				Files.createDirectories(directory);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				ItemDetails itemDetails = itemRepository.findBySerialNumber(certificateUpload.getSerialNumber())
						.orElseThrow(() -> new ItemException(NO_ITEM_FOUND));
				Certificate certificate = Certificate.builder().certificateName(file.getOriginalFilename())
						.certificateFormat(file.getContentType()).certificateUrl(path.toString())
						.certificateNumber(certificateUpload.getCertificateNumber())
						.expiryDate(certificateUpload.getExpiryDate()).build();
				CertificateType type = certificateTypeRepository
						.findByCertificateType(certificateUpload.getCertificateType()).orElse(CertificateType.builder()
								.certificateType(certificateUpload.getCertificateType()).build());
				certificate.setCertificateType(type);
				certificate.setItemDetails(itemDetails);
				certificateRepository.save(certificate);
			} catch (IOException exception) {
				throw new IOException(exception.getMessage());
			}
		}
		return FileResponse.builder().isError(false).message(CERTIFICATE_UPLOADED_SUCCESSFULLY).build();
	}

	/**
	 * view certificate
	 * 
	 * @throws MalformedURLException
	 */
	public ViewCertificate viewCertificate(String certificateNumber) throws MalformedURLException {
		if (!certificateNumber.isEmpty()) {
			Certificate certificateInfo = certificateRepository.findByCertificateNumber(certificateNumber)
					.orElseThrow(() -> new CertificateException(CERTIFICATE_NOT_FOUND));
			directory = Paths.get(certificateInfo.getCertificateUrl()).toAbsolutePath().normalize();
			if (Files.notExists(directory)) {
				throw new CertificateException(FILE_NOT_PRESENT);
			}
			Resource resource = null;
			try {
				resource = new UrlResource(directory.toUri());
			} catch (MalformedURLException exception) {
				throw new MalformedURLException(exception.getMessage());
			}
			return ViewCertificate.builder().certificateFormat(certificateInfo.getCertificateFormat())
					.resource(resource).build();
		}
		throw new CertificateException(PLEASE_ENTER_CERTIFICATE_NUMBER);
	}

	/**
	 * update certificate
	 * 
	 * @throws IOException
	 */
	public FileResponse updateCertificate(MultipartFile file, String data) throws IOException {
		if (file.isEmpty()) {
			throw new CertificateException(PLEASE_SELECT_DOCUMENT);
		} else if (data.isEmpty()) {
			throw new DataException(DATA_NOT_EMPTY);
		} else {
			CertificateUpdateRequest certificateUpdate = null;
			try {
				certificateUpdate = updateCertificateDetails(data);

				Set<ConstraintViolation<CertificateUpdateRequest>> violations = validator.validate(certificateUpdate);

				if (!violations.isEmpty()) {
					throw new ConstraintViolationException(violations);
				}

				Certificate certificate = certificateRepository
						.findByCertificateNumber(certificateUpdate.getCertificateNumber())
						.orElseThrow(() -> new CertificateException(CERTIFICATE_NOT_FOUND));
				directory = getPath(file.getOriginalFilename());
				Path path = directory.resolve(file.getOriginalFilename());
				Files.createDirectories(directory);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				certificate.setCertificateName(file.getOriginalFilename());
				certificate.setCertificateFormat(file.getContentType());
				certificate.setCertificateUrl(path.toString());
				certificate.setExpiryDate(certificateUpdate.getExpiryDate());
				certificateRepository.save(certificate);
			} catch (IOException exception) {
				throw new IOException(exception.getMessage());
			}
		}
		return FileResponse.builder().isError(false).message(CERTIFICATE_UPDATED_SUCCESSFULLY).build();
	}

	/**
	 * add item
	 */
	public ItemResponse addItem(ItemRequest request) {
		if (Boolean.TRUE.equals(itemRepository.existsBySerialNumber(request.getSerialNumber())))
			throw new ItemException(ITEM_ALREADY_EXISTS);

		ItemDetails itemDetails = ItemDetails.builder().serialNumber(request.getSerialNumber()).build();

		Set<Code> listOfCodes = request.getCodeRequestList().stream().map(codeRequest -> {
			CodeType codeType = codeTypeRepository.findByCodeType(codeRequest.getCodeType())
					.orElse(CodeType.builder().codeType(codeRequest.getCodeType()).build());
			return Code.builder().codeType(codeType).codeValue(codeRequest.getCodeValue()).item(itemDetails).build();
		}).collect(Collectors.toSet());

		itemDetails.setManufacturerName(request.getManufacturerName());
		itemDetails.setPartNumber(request.getPartNumber());
		itemDetails.setQuantity(request.getQuantity());
		itemDetails.setDescription(request.getDescription());
		itemDetails.setUnitOfMeasure(request.getUnitOfMeasure());
		itemDetails.setUnitPrice(request.getUnitPrice());
		itemDetails.setCountryOfOrigin(request.getCountryOfOrigin());
		itemDetails.setCodeList(listOfCodes);
		itemRepository.save(itemDetails);
		return ItemResponse.builder().isError(false).message(ITEM_ADDED_SUCCESSFULLY).build();
	}

	/**
	 * view item
	 */
	public ItemDetailsResponse viewItem(Long serialNumber) {
		ItemDetails itemDetails = itemRepository.findBySerialNumber(serialNumber)
				.orElseThrow(() -> new ItemException(ITEM_NOT_FOUND_WITH_SERIAL + serialNumber));

		Set<CodeResponse> codeList = itemDetails.getCodeList().stream().map(code -> CodeResponse.builder()
				.codeType(code.getCodeType().getCodeType()).codeValue(code.getCodeValue()).build())
				.collect(Collectors.toSet());

		Set<ViewCertificateList> certificateList = itemDetails.getCertificateList().stream()
				.map(certificate -> ViewCertificateList.builder().certificateNumber(certificate.getCertificateNumber())
						.expiryDate(certificate.getExpiryDate())
						.certificateType(certificate.getCertificateType().getCertificateType()).build())
				.collect(Collectors.toSet());

		Item item = Item.builder().manufacturerName(itemDetails.getManufacturerName())
				.serialNumber(itemDetails.getSerialNumber()).partNumber(itemDetails.getPartNumber())
				.quantity(itemDetails.getQuantity()).description(itemDetails.getDescription())
				.unitOfMeasure(itemDetails.getUnitOfMeasure()).unitPrice(itemDetails.getUnitPrice())
				.countryOfOrigin(itemDetails.getCountryOfOrigin()).codeList(codeList).certificateList(certificateList)
				.build();
		return ItemDetailsResponse.builder().isError(false).message(ITEM_FETCHED_SUCCESSFULLY).item(item).build();
	}

	/**
	 * view all items
	 */
	public ItemDashboardResponse viewAllItems() {
		List<ItemDetails> allItems = itemRepository.findAll();
		if (allItems.isEmpty()) {
			throw new ItemException(NO_ITEM_FOUND);
		}
		List<Item> dashboardItems = allItems.stream().map(itemDetails -> {
			Set<CodeResponse> listOfCodes = itemDetails
					.getCodeList().stream().map(code -> CodeResponse.builder()
							.codeType(code.getCodeType().getCodeType()).codeValue(code.getCodeValue()).build())
					.collect(Collectors.toSet());

			Set<ViewCertificateList> listOfCertificate = itemDetails.getCertificateList().stream()
					.map(certificate -> ViewCertificateList.builder()
							.certificateNumber(certificate.getCertificateNumber())
							.expiryDate(certificate.getExpiryDate())
							.certificateType(certificate.getCertificateType().getCertificateType()).build())
					.collect(Collectors.toSet());

			return Item.builder().manufacturerName(itemDetails.getManufacturerName())
					.serialNumber(itemDetails.getSerialNumber()).partNumber(itemDetails.getPartNumber())
					.quantity(itemDetails.getQuantity()).description(itemDetails.getDescription())
					.unitOfMeasure(itemDetails.getUnitOfMeasure()).unitPrice(itemDetails.getUnitPrice())
					.countryOfOrigin(itemDetails.getCountryOfOrigin()).codeList(listOfCodes)
					.certificateList(listOfCertificate).build();
		}).collect(Collectors.toList());
		return ItemDashboardResponse.builder().isError(false).message(ALL_ITEMS_FETCHED).itemsList(dashboardItems)
				.build();
	}

	/**
	 * delete item
	 */
	public ItemResponse deleteItem(Long serialNumber) {
		ItemDetails item = itemRepository.findBySerialNumber(serialNumber)
				.orElseThrow(() -> new ItemException(ITEM_NOT_FOUND_WITH_SERIAL + serialNumber));
		Set<Certificate> certificateList = item.getCertificateList();

		if (certificateList.isEmpty()) {
			itemRepository.delete(item);
		} else {
			certificateList.stream().forEach(certificate -> {
				directory = getPath(certificate.getCertificateName());
				certificateRepository.deleteByCertificateNumber(certificate.getCertificateNumber());
				try {
					if (Files.exists(directory)) {
						FileUtils.forceDelete(new File(directory.toString()));
					}
				} catch (IOException exception) {
					throw new ItemException(exception.getMessage());
				}
			});
			itemRepository.delete(item);
		}
		return ItemResponse.builder().isError(false).message(ITEM_DELETED_SUCCESSFULLY).build();
	}

	/**
	 * update item
	 */
	public ItemResponse updateItem(ItemUpdateRequest request, Long serialNumber) {

		ItemDetails itemDetails = itemRepository.findBySerialNumber(serialNumber)
				.orElseThrow(() -> new ItemException(ITEM_NOT_FOUND_WITH_SERIAL + serialNumber));

		itemDetails.setManufacturerName(request.getCustomerName());
		itemDetails.setPartNumber(request.getPartNumber());
		itemDetails.setQuantity(request.getQuantity());
		itemDetails.setDescription(request.getDescription());
		itemDetails.setUnitOfMeasure(request.getUnitOfMeasure());
		itemDetails.setUnitPrice(request.getUnitPrice());
		itemDetails.setCountryOfOrigin(request.getCountryOfOrigin());

		for (CodeRequest requestCodeType : request.getCodeRequestList()) {
			for (Code code : itemDetails.getCodeList()) {
				if (code.getCodeType().getCodeType().equals(requestCodeType.getCodeType())) {
					code.setCodeValue(requestCodeType.getCodeValue());
				}
			}
		}
		itemRepository.save(itemDetails);
		return ItemResponse.builder().isError(false).message(ITEM_UPDATED_SUCCESSFULLY).build();
	}
}
