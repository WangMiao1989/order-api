package com.wm.entity;

import lombok.Data;

@Data
public class TenantEntity {
	private String tenantId;
	private String tenantName;
	private String schemaName;
	private String contactTel;
}
