package com.wm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.TenantEntity;

@Mapper
public interface TenantRepository {
	public TenantEntity selectTenantInfo(String tenantId);
}
