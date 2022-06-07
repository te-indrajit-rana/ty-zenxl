package com.ty.zenxl.service;

import static com.ty.zenxl.pojos.ZenxlConstantData.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ty.zenxl.entity.Certificate;
import com.ty.zenxl.entity.Code;
import com.ty.zenxl.entity.HsCode;
import com.ty.zenxl.entity.Incoterm;
import com.ty.zenxl.entity.Inspection;
import com.ty.zenxl.exception.CertificateNotFoundException;
import com.ty.zenxl.exception.CertificatePersistenceException;
import com.ty.zenxl.exception.CodeNotFoundException;
import com.ty.zenxl.exception.CodePersistenceException;
import com.ty.zenxl.exception.HsCodeNotFoundException;
import com.ty.zenxl.exception.HsCodePersistenceException;
import com.ty.zenxl.exception.IncotermNotFoundException;
import com.ty.zenxl.exception.IncotermPersistenceException;
import com.ty.zenxl.exception.InspectionNotFoundException;
import com.ty.zenxl.exception.InspectionPersistenceException;
import com.ty.zenxl.exception.UpdateException;
import com.ty.zenxl.repository.CertificateRepository;
import com.ty.zenxl.repository.CodeRepository;
import com.ty.zenxl.repository.HsCodeRepository;
import com.ty.zenxl.repository.IncotermRepository;
import com.ty.zenxl.repository.InspectionRepository;
import com.ty.zenxl.request.CertificateRequest;
import com.ty.zenxl.request.CodeRequest;
import com.ty.zenxl.request.HsCodeRequest;
import com.ty.zenxl.request.IncotermRequest;
import com.ty.zenxl.request.InspectionRequest;
import com.ty.zenxl.request.UpdateCertificateRequest;
import com.ty.zenxl.request.UpdateCodeRequest;
import com.ty.zenxl.request.UpdateHsCodeRequest;
import com.ty.zenxl.request.UpdateIncotermRequest;
import com.ty.zenxl.request.UpdateInspectionRequest;
import com.ty.zenxl.response.CertificateResponse;
import com.ty.zenxl.response.CodeResponse;
import com.ty.zenxl.response.HsCodeResponse;
import com.ty.zenxl.response.IncotermResponse;
import com.ty.zenxl.response.InspectionResponse;

import lombok.RequiredArgsConstructor;

/**
 * Defines the mechanisms implemented for CRUD opeartions for all the utilities
 * such as {@code Certificate}, {@code Code}, {@code Incoterm},
 * {@code Inspection}.
 *
 * @author Indrajit
 * @version 1.0
 */

@Service
@Transactional
@RequiredArgsConstructor
public class ZenxlUtilityService {

	private final CertificateRepository certificateRepository;
	private final CodeRepository codeRepository;
	private final IncotermRepository incotermRepository;
	private final InspectionRepository inspectionRepository;
	private final HsCodeRepository hsCodeRepository;

	// certificate crud api logics

	public CertificateResponse addCertificate(CertificateRequest request) {
		if (Boolean.TRUE.equals(certificateRepository.existsByCertificateType(request.getCertificateType()))) {
			throw new CertificatePersistenceException(CERTIFICATE_ALREADY_EXISTS);
		}
		Certificate certificate = Certificate.builder().certificateType(request.getCertificateType()).build();
		Certificate savedCertificate = certificateRepository.save(certificate);
		if (savedCertificate != null) {
			return CertificateResponse.builder().certificateId(savedCertificate.getCertificateId())
					.certificateType(savedCertificate.getCertificateType()).build();
		}
		throw new CertificatePersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<CertificateResponse> findAllCertificates() {

		List<Certificate> findAllCertificates = certificateRepository.findAll();
		return findAllCertificates.stream()
				.map(certificate -> CertificateResponse.builder().certificateId(certificate.getCertificateId())
						.certificateType(certificate.getCertificateType()).build())
				.collect(Collectors.toList());
	}

	public String updateCertificate(int certificateId, UpdateCertificateRequest request) {

		Certificate certificate = certificateRepository.findByCertificateId(certificateId).orElseThrow(
				() -> new CertificateNotFoundException("Certificate not found with certificate id " + certificateId));

		if (!request.getCertificateType().equals(certificate.getCertificateType())) {
			if (Boolean.TRUE.equals(certificateRepository.existsByCertificateType(request.getCertificateType()))) {
				throw new CertificatePersistenceException(CERTIFICATE_ALREADY_EXISTS);
			}
		}

		certificate.setCertificateType(request.getCertificateType());
		Certificate updatedCertificate = certificateRepository.save(certificate);
		if (updatedCertificate != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteCertificate(int certificateId) {

		Certificate certificate = certificateRepository.findByCertificateId(certificateId).orElseThrow(
				() -> new CertificateNotFoundException("Certificate not found with certificate id " + certificateId));
		certificateRepository.deleteCertificate(certificate.getCertificateId());
		return DELETED_SUCCESSFULLY;
	}

	// code crud api logics

	public CodeResponse addCode(CodeRequest request) {
		if (Boolean.TRUE.equals(codeRepository.existsByCodeType(request.getCodeType()))) {
			throw new CodePersistenceException(CODE_ALREADY_EXISTS);
		}
		Code code = Code.builder().codeType(request.getCodeType()).build();
		Code savedCode = codeRepository.save(code);
		if (savedCode != null) {
			return CodeResponse.builder().codeId(savedCode.getCodeId()).codeType(savedCode.getCodeType()).build();
		}
		throw new CodePersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<CodeResponse> findAllCodes() {

		List<Code> findAllCodes = codeRepository.findAll();
		return findAllCodes.stream()
				.map(code -> CodeResponse.builder().codeId(code.getCodeId()).codeType(code.getCodeType()).build())
				.collect(Collectors.toList());
	}

	public String updateCode(int codeId, UpdateCodeRequest request) {
		Code code = codeRepository.findByCodeId(codeId)
				.orElseThrow(() -> new CodeNotFoundException("Code not found with code id " + codeId));

		if (!request.getCodeType().equals(code.getCodeType())) {
			if (Boolean.TRUE.equals(codeRepository.existsByCodeType(request.getCodeType()))) {
				throw new CodePersistenceException(CODE_ALREADY_EXISTS);
			}
		}

		code.setCodeType(request.getCodeType());
		Code updatedCode = codeRepository.save(code);
		if (updatedCode != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteCode(int codeId) {
		Code code = codeRepository.findByCodeId(codeId)
				.orElseThrow(() -> new CodeNotFoundException("Code not found with code id " + codeId));
		codeRepository.deleteCode(code.getCodeId());
		return DELETED_SUCCESSFULLY;
	}

	// incoterm crud api logics

	public IncotermResponse addIncoterm(IncotermRequest request) {
		if (Boolean.TRUE.equals(incotermRepository.existsByIncotermType(request.getIncotermType()))) {
			throw new IncotermPersistenceException(INCOTERM_ALREADY_EXISTS);
		}
		Incoterm incoterm = Incoterm.builder().incotermType(request.getIncotermType()).build();
		Incoterm savedIncoterm = incotermRepository.save(incoterm);
		if (savedIncoterm != null) {
			return IncotermResponse.builder().incotermId(savedIncoterm.getIncotermId())
					.incotermType(savedIncoterm.getIncotermType()).build();
		}
		throw new IncotermPersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<IncotermResponse> findAllIncoterms() {
		List<Incoterm> findAllIncoterms = incotermRepository.findAll();
		return findAllIncoterms.stream().map(incoterm -> IncotermResponse.builder().incotermId(incoterm.getIncotermId())
				.incotermType(incoterm.getIncotermType()).build()).collect(Collectors.toList());
	}

	public String updateIncoterm(int incotermId, @Valid UpdateIncotermRequest request) {
		Incoterm incoterm = incotermRepository.findByIncotermId(incotermId)
				.orElseThrow(() -> new IncotermNotFoundException("Incoterm not found with incoterm id " + incotermId));

		if (!request.getIncotermType().equals(incoterm.getIncotermType())) {
			if (Boolean.TRUE.equals(incotermRepository.existsByIncotermType(request.getIncotermType()))) {
				throw new IncotermPersistenceException(INCOTERM_ALREADY_EXISTS);
			}
		}

		incoterm.setIncotermType(request.getIncotermType());
		Incoterm updatedIncoterm = incotermRepository.save(incoterm);
		if (updatedIncoterm != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteIncoterm(int incotermId) {
		Incoterm incoterm = incotermRepository.findByIncotermId(incotermId)
				.orElseThrow(() -> new IncotermNotFoundException("Incoterm not found with incoterm id " + incotermId));
		incotermRepository.deleteIncoterm(incoterm.getIncotermId());
		return DELETED_SUCCESSFULLY;
	}

	// inspection crud api logics

	public InspectionResponse addInspection(InspectionRequest request) {
		if (Boolean.TRUE.equals(inspectionRepository.existsByInspectionType(request.getInspectionType()))) {
			throw new InspectionPersistenceException(INSPECTION_ALREADY_EXISTS);
		}
		Inspection inspection = Inspection.builder().inspectionType(request.getInspectionType()).build();
		Inspection savedInspection = inspectionRepository.save(inspection);
		if (savedInspection != null) {
			return InspectionResponse.builder().inspectionId(savedInspection.getInspectionId())
					.inspectionType(savedInspection.getInspectionType()).build();
		}
		throw new InspectionPersistenceException(SOMETHING_WENT_WRONG);
	}

	public List<InspectionResponse> findAllInspections() {
		List<Inspection> findAllInspections = inspectionRepository.findAll();
		return findAllInspections.stream().map(inspection -> InspectionResponse.builder()
				.inspectionId(inspection.getInspectionId()).inspectionType(inspection.getInspectionType()).build())
				.collect(Collectors.toList());
	}

	public String updateInspection(int inspectionId, UpdateInspectionRequest request) {
		Inspection inspection = inspectionRepository.findByInspectionId(inspectionId).orElseThrow(
				() -> new InspectionNotFoundException("Inspection not found with inspection id " + inspectionId));

		if (!request.getInspectionType().equals(inspection.getInspectionType())) {
			if (Boolean.TRUE.equals(inspectionRepository.existsByInspectionType(request.getInspectionType()))) {
				throw new InspectionPersistenceException(INSPECTION_ALREADY_EXISTS);
			}
		}

		inspection.setInspectionType(request.getInspectionType());
		Inspection updatedInspection = inspectionRepository.save(inspection);
		if (updatedInspection != null) {
			return UPDATED_SUCCESSFULLY;
		}
		throw new UpdateException(SOMETHING_WENT_WRONG);
	}

	public String deleteInspection(int inspectionId) {
		Inspection inspection = inspectionRepository.findByInspectionId(inspectionId).orElseThrow(
				() -> new InspectionNotFoundException("Inspection not found with inspection id " + inspectionId));
		inspectionRepository.deleteInspection(inspection.getInspectionId());
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
				.orElseThrow(() -> new HsCodeNotFoundException("HsCode not found with hsCode id " + hsCodeId));

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
				.orElseThrow(() -> new HsCodeNotFoundException("HsCode not found with hsCode id " + hsCodeId));
		hsCodeRepository.deleteHsCode(hsCode.getHsCodeId());
		return DELETED_SUCCESSFULLY;
	}

}
