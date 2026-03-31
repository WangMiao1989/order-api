package com.wm.responseDto;

import lombok.Data;

@Data
public class TableDetailRetrieveResponse {
	private String tableSessionId;
	private Integer customerCnt;
	private String tenantName;
}
