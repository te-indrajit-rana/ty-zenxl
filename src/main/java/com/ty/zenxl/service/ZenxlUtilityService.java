package com.ty.zenxl.service;

import static com.ty.zenxl.pojos.ZenxlConstantData.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ty.zenxl.entity.CertificateType;
import com.ty.zenxl.entity.CodeType;
import com.ty.zenxl.entity.HsCode;
import com.ty.zenxl.entity.IncotermType;
import com.ty.zenxl.entity.InspectionType;
import com.ty.zenxl.exception.CertificateTypeNotFoundException;
import com.ty.zenxl.exception.CertificateTypePersistenceException;
import com.ty.zenxl.exception.CodeTypeNotFoundException;
import com.ty.zenxl.exception.CodeTypePersistenceException;
import com.ty.zenxl.exception.HsCodeNotFoundException;
import com.ty.zenxl.exception.HsCodePersistenceException;
import com.ty.zenxl.exception.IncotermTypeNotFoundException;
import com.ty.zenxl.exception.IncotermTypePersistenceException;
import com.ty.zenxl.exception.InspectionTypeNotFoundException;
import com.ty.zenxl.exception.InspectionTypePersistenceException;
import com.ty.zenxl.exception.UpdateException;
import com.ty.zenxl.repository.CertificateTypeRepository;
import com.ty.zenxl.repository.CodeTypeRepository;
import com.ty.zenxl.repository.HsCodeRepository;
import com.ty.zenxl.repository.IncotermTypeRepository;
import com.ty.zenxl.repository.InspectionTypeRepository;
import com.ty.zenxl.request.CertificateTypeRequest;
import com.ty.zenxl.request.CodeTypeRequest;
import com.ty.zenxl.request.HsCodeRequest;
import com.ty.zenxl.request.IncotermTypeRequest;
import com.ty.zenxl.request.InspectionTypeRequest;
import com.ty.zenxl.request.UpdateCertificateTypeRequest;
import com.ty.zenxl.request.UpdateCodeTypeRequest;
import com.ty.zenxl.request.UpdateHsCodeRequest;
import com.ty.zenxl.request.UpdateIncotermTypeRequest;
import com.ty.zenxl.request.UpdateInspectionTypeRequest;
import com.ty.zenxl.response.CertificateTypeResponse;
import com.ty.zenxl.response.CodeTypeResponse;
import com.ty.zenxl.response.HsCodeResponse;
import com.ty.zenxl.response.IncotermTypeResponse;
import com.ty.zenxl.response.InspectionTypeResponse;

import lombok.RequiredArgsConstructor;

/**
 * Defines the mechanisms implemented for CRUD opeartions for all the utilities
 * such as {@code CertificateType}, {@code CodeType}, {@code IncotermType},
 * {@code InspectionType} and {@code HsCode}.
 *
 * @author Indrajit
 * @version 1.0
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ZenxlUtilityService {

	private final CertificateTypeRepository certificateTypeRepository;
	private final CodeTypeRepository codeTypeRepository;
	private final IncotermTypeRepository incotermTypeRepository;
	private final InspectionTypeRepository inspectionTypeRepository;
	private final HsCodeRepository hsCodeRepository;

	// certificate-type crud api logics

	public CertificateTypeResponse addCertificateType(CertificateTypeRequest request) {
		if (Boolean.TRUE.equals(certificateTypeRepository.existsByCertificateType(request.getCertificateType()))) {
			throw new CertificateTypePersistenceException(CERTIFICATE_TYPE_ALREADY_EXISTS);
		}
		CertificateType certificateType = CertificateType.builder().certificateType(request.getCertificateType())
				.build();
		CertificateType savedCertificateType = certificateTypeRepository.save(certificateType);
		if (savedCertificateType != null) {
			return CertificateTypeResponse.builder().certificateTypeId(savedCertificateType.getCertificateTypeId())
					.certificateType(savedCertificateType.getCertificateType()).build();
		}
		throw new CertificateTypePersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<CertificateTypeResponse> findAllCertificateTypes() {

		List<CertificateType> findAllCertificateTypes = certificateTypeRepository.findAll();
		return findAllCertificateTypes.stream()
				.map(certificateType -> CertificateTypeResponse.builder()
						.certificateTypeId(certificateType.getCertificateTypeId())
						.certificateType(certificateType.getCertificateType()).build())
				.collect(Collectors.toList());
	}

	public String updateCertificateType(int certificateTypeId, UpdateCertificateTypeRequest request) {

		CertificateType certificateType = certificateTypeRepository.findByCertificateTypeId(certificateTypeId)
				.orElseThrow(() -> new CertificateTypeNotFoundException(
						CERTIFICATE_TYPE_NOT_FOUND_WITH_CERTIFICATE_TYPE_ID + certificateTypeId));

		if (!request.getCertificateType().equals(certificateType.getCertificateType())) {
			if (Boolean.TRUE.equals(certificateTypeRepository.existsByCertificateType(request.getCertificateType()))) {
				throw new CertificateTypePersistenceException(CERTIFICATE_TYPE_ALREADY_EXISTS);
			}
		}

		certificateType.setCertificateType(request.getCertificateType());
		CertificateType updatedCertificateType = certificateTypeRepository.save(certificateType);
		if (updatedCertificateType != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteCertificateType(int certificateTypeId) {

		CertificateType certificateType = certificateTypeRepository.findByCertificateTypeId(certificateTypeId)
				.orElseThrow(() -> new CertificateTypeNotFoundException(
						CERTIFICATE_TYPE_NOT_FOUND_WITH_CERTIFICATE_TYPE_ID + certificateTypeId));
		certificateTypeRepository.deleteCertificateType(certificateType.getCertificateTypeId());
		return DELETED_SUCCESSFULLY;
	}

	// code-type crud api logics

	public CodeTypeResponse addCodeType(CodeTypeRequest request) {
		if (Boolean.TRUE.equals(codeTypeRepository.existsByCodeType(request.getCodeType()))) {
			throw new CodeTypePersistenceException(CODE_TYPE_ALREADY_EXISTS);
		}
		CodeType codeType = CodeType.builder().codeType(request.getCodeType()).build();
		CodeType savedCodeType = codeTypeRepository.save(codeType);
		if (savedCodeType != null) {
			return CodeTypeResponse.builder().codeTypeId(savedCodeType.getCodeTypeId())
					.codeType(savedCodeType.getCodeType()).build();
		}
		throw new CodeTypePersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<CodeTypeResponse> findAllCodeTypes() {

		List<CodeType> findAllCodeTypes = codeTypeRepository.findAll();
		return findAllCodeTypes.stream().map(codeType -> CodeTypeResponse.builder().codeTypeId(codeType.getCodeTypeId())
				.codeType(codeType.getCodeType()).build()).collect(Collectors.toList());
	}

	public String updateCodeType(int codeTypeId, UpdateCodeTypeRequest request) {
		CodeType codeType = codeTypeRepository.findByCodeTypeId(codeTypeId)
				.orElseThrow(() -> new CodeTypeNotFoundException(CODE_TYPE_NOT_FOUND_WITH_CODE_TYPE_ID + codeTypeId));

		if (!request.getCodeType().equals(codeType.getCodeType())) {
			if (Boolean.TRUE.equals(codeTypeRepository.existsByCodeType(request.getCodeType()))) {
				throw new CodeTypePersistenceException(CODE_TYPE_ALREADY_EXISTS);
			}
		}

		codeType.setCodeType(request.getCodeType());
		CodeType updatedCodeType = codeTypeRepository.save(codeType);
		if (updatedCodeType != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteCodeType(int codeTypeId) {
		CodeType codeType = codeTypeRepository.findByCodeTypeId(codeTypeId)
				.orElseThrow(() -> new CodeTypeNotFoundException(CODE_TYPE_NOT_FOUND_WITH_CODE_TYPE_ID + codeTypeId));
		codeTypeRepository.deleteCodeType(codeType.getCodeTypeId());
		return DELETED_SUCCESSFULLY;
	}

	// incoterm-type crud api logics

	public IncotermTypeResponse addIncotermType(IncotermTypeRequest request) {
		if (Boolean.TRUE.equals(incotermTypeRepository.existsByIncotermType(request.getIncotermType()))) {
			throw new IncotermTypePersistenceException(INCOTERM_TYPE_ALREADY_EXISTS);
		}
		IncotermType incotermType = IncotermType.builder().incotermType(request.getIncotermType()).build();
		IncotermType savedIncotermType = incotermTypeRepository.save(incotermType);
		if (savedIncotermType != null) {
			return IncotermTypeResponse.builder().incotermTypeId(savedIncotermType.getIncotermTypeId())
					.incotermType(savedIncotermType.getIncotermType()).build();
		}
		throw new IncotermTypePersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<IncotermTypeResponse> findAllIncotermTypes() {
		List<IncotermType> findAllIncotermTypes = incotermTypeRepository.findAll();
		return findAllIncotermTypes.stream().map(incotermType -> IncotermTypeResponse.builder()
				.incotermTypeId(incotermType.getIncotermTypeId()).incotermType(incotermType.getIncotermType()).build())
				.collect(Collectors.toList());
	}

	public String updateIncotermType(int incotermTypeId, @Valid UpdateIncotermTypeRequest request) {
		IncotermType incotermType = incotermTypeRepository.findByIncotermTypeId(incotermTypeId)
				.orElseThrow(() -> new IncotermTypeNotFoundException(
						INCOTERM_TYPE_NOT_FOUND_WITH_INCOTERM_TYPE_ID + incotermTypeId));

		if (!request.getIncotermType().equals(incotermType.getIncotermType())) {
			if (Boolean.TRUE.equals(incotermTypeRepository.existsByIncotermType(request.getIncotermType()))) {
				throw new IncotermTypePersistenceException(INCOTERM_TYPE_ALREADY_EXISTS);
			}
		}

		incotermType.setIncotermType(request.getIncotermType());
		IncotermType updatedIncotermType = incotermTypeRepository.save(incotermType);
		if (updatedIncotermType != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteIncotermType(int incotermTypeId) {
		IncotermType incotermType = incotermTypeRepository.findByIncotermTypeId(incotermTypeId)
				.orElseThrow(() -> new IncotermTypeNotFoundException(
						INCOTERM_TYPE_NOT_FOUND_WITH_INCOTERM_TYPE_ID + incotermTypeId));
		incotermTypeRepository.deleteIncotermType(incotermType.getIncotermTypeId());
		return DELETED_SUCCESSFULLY;
	}

	// inspection-type crud api logics

	public InspectionTypeResponse addInspectionType(InspectionTypeRequest request) {
		if (Boolean.TRUE.equals(inspectionTypeRepository.existsByInspectionType(request.getInspectionType()))) {
			throw new InspectionTypePersistenceException(INSPECTION_TYPE_ALREADY_EXISTS);
		}
		InspectionType inspectionType = InspectionType.builder().inspectionType(request.getInspectionType()).build();
		InspectionType savedInspectionType = inspectionTypeRepository.save(inspectionType);
		if (savedInspectionType != null) {
			return InspectionTypeResponse.builder().inspectionTypeId(savedInspectionType.getInspectionTypeId())
					.inspectionType(savedInspectionType.getInspectionType()).build();
		}
		throw new InspectionTypePersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<InspectionTypeResponse> findAllInspectionTypes() {
		List<InspectionType> findAllInspectionTypes = inspectionTypeRepository.findAll();
		return findAllInspectionTypes.stream()
				.map(inspectionType -> InspectionTypeResponse.builder()
						.inspectionTypeId(inspectionType.getInspectionTypeId())
						.inspectionType(inspectionType.getInspectionType()).build())
				.collect(Collectors.toList());
	}

	public String updateInspectionType(int inspectionTypeId, UpdateInspectionTypeRequest request) {
		InspectionType inspectionType = inspectionTypeRepository.findByInspectionTypeId(inspectionTypeId)
				.orElseThrow(() -> new InspectionTypeNotFoundException(
						INSPECTION_TYPE_NOT_FOUND_WITH_INSPECTION_TYPE_ID + inspectionTypeId));

		if (!request.getInspectionType().equals(inspectionType.getInspectionType())) {
			if (Boolean.TRUE.equals(inspectionTypeRepository.existsByInspectionType(request.getInspectionType()))) {
				throw new InspectionTypePersistenceException(INSPECTION_TYPE_ALREADY_EXISTS);
			}
		}

		inspectionType.setInspectionType(request.getInspectionType());
		InspectionType updatedInspectionType = inspectionTypeRepository.save(inspectionType);
		if (updatedInspectionType != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteInspectionType(int inspectionTypeId) {
		InspectionType inspectionType = inspectionTypeRepository.findByInspectionTypeId(inspectionTypeId)
				.orElseThrow(() -> new InspectionTypeNotFoundException(
						INSPECTION_TYPE_NOT_FOUND_WITH_INSPECTION_TYPE_ID + inspectionTypeId));
		inspectionTypeRepository.deleteInspectionType(inspectionType.getInspectionTypeId());
		return DELETED_SUCCESSFULLY;
	}

	// hscode crud api logics

	public HsCodeResponse addHsCode(HsCodeRequest request) {
		if (Boolean.TRUE.equals(hsCodeRepository.existsByHsCodeType(request.getHsCodeType()))) {
			throw new HsCodePersistenceException(HS_CODE_ALREADY_EXISTS);
		}
		HsCode hsCode = HsCode.builder().hsCodeType(request.getHsCodeType()).build();
		HsCode savedHsCode = hsCodeRepository.save(hsCode);
		if (savedHsCode != null) {
			return HsCodeResponse.builder().hsCodeId(savedHsCode.getHsCodeId()).hsCodeType(savedHsCode.getHsCodeType())
					.build();
		}
		throw new HsCodePersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<HsCodeResponse> findAllHsCodes() {
		List<HsCode> findAllHsCodes = hsCodeRepository.findAll();
		return findAllHsCodes.stream().map(hsCode -> HsCodeResponse.builder().hsCodeId(hsCode.getHsCodeId())
				.hsCodeType(hsCode.getHsCodeType()).build()).collect(Collectors.toList());
	}

	public String updateHsCode(int hsCodeId, @Valid UpdateHsCodeRequest request) {
		HsCode hsCode = hsCodeRepository.findByHsCodeId(hsCodeId)
				.orElseThrow(() -> new HsCodeNotFoundException(HS_CODE_NOT_FOUND_WITH_HS_CODE_ID + hsCodeId));

		if (!request.getHsCodeType().equals(hsCode.getHsCodeType())) {
			if (Boolean.TRUE.equals(hsCodeRepository.existsByHsCodeType(request.getHsCodeType()))) {
				throw new HsCodePersistenceException(HS_CODE_ALREADY_EXISTS);
			}
		}

		hsCode.setHsCodeType(request.getHsCodeType());
		HsCode updatedHsCode = hsCodeRepository.save(hsCode);
		if (updatedHsCode != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteHsCode(int hsCodeId) {
		HsCode hsCode = hsCodeRepository.findByHsCodeId(hsCodeId)
				.orElseThrow(() -> new HsCodeNotFoundException(HS_CODE_NOT_FOUND_WITH_HS_CODE_ID + hsCodeId));
		hsCodeRepository.deleteHsCode(hsCode.getHsCodeId());
		return DELETED_SUCCESSFULLY;
	}

}
