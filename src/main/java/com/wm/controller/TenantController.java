package com.wm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.TenantEntity;
import com.wm.requestDto.TenantRetrieveRequestForm;
import com.wm.service.TenantService;

@RestController
@RequestMapping("/tenant")
public class TenantController {

	@Autowired
	private TenantService tenantService;
	
	@RequestMapping("/retrieve")
	public TenantEntity tenantRetrieve(@RequestBody TenantRetrieveRequestForm request) {
		return tenantService.tenantRetrieve(request.getTenantId());
	}
}
