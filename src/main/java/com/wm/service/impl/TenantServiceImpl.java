package com.wm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.TenantEntity;
import com.wm.mapper.TenantRepository;
import com.wm.service.TenantService;

@Service
@Transactional
public class TenantServiceImpl implements TenantService{
	
	@Autowired
	private TenantRepository tenantRepository;
	
	public TenantEntity tenantRetrieve(String tenantId) {
		return tenantRepository.selectTenantInfo(tenantId);
	}
}
