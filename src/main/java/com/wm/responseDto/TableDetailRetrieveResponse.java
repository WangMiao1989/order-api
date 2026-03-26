package com.wm.responseDto;

import lombok.Data;

@Data
public class TableDetailRetrieveResponse {
	private Long tableId;
	private Integer customerCnt;
	private String tenantName;
}
