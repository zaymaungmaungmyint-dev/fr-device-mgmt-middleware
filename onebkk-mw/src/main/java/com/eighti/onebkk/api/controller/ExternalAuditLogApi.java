package com.eighti.onebkk.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eighti.onebkk.dto.IdentifyLogDto;
import com.eighti.onebkk.dto.PaginatedResponse;
import com.eighti.onebkk.dto.api.request.IdentifyAuditLogRequest;
import com.eighti.onebkk.response.Response;
import com.eighti.onebkk.service.AuditLogService;
import com.eighti.onebkk.utils.FieldErrorCode;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@Hidden
@RequestMapping("/v1/log")
@Tag(name = "Audit Log External APIs")
public class ExternalAuditLogApi {

	private static final Logger LOG = LoggerFactory.getLogger(ExternalAuditLogApi.class);

	private final AuditLogService auditLogService;

	public ExternalAuditLogApi(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	// Get the identify audit log
	@PostMapping("/identify")
	public Response<PaginatedResponse<IdentifyLogDto>> searchIdentifyAuditLog(
			@RequestBody IdentifyAuditLogRequest requestData, HttpServletRequest httpRequest) {

		Response<PaginatedResponse<IdentifyLogDto>> response = new Response<PaginatedResponse<IdentifyLogDto>>();

		try {

			// TODO Get the identify audit logs based on search criteria
			PaginatedResponse<IdentifyLogDto> resultList = auditLogService.searchIdentifyLogList(requestData);

			response.setData(resultList);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("searchIdentifyAuditLog() >>> ERROR: " + e.getMessage(), e);
			response = new Response<>(FieldErrorCode.GENERAL);

		}

		return response;
	}

}
