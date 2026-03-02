package com.wm.responseDto;

import java.util.List;

import com.wm.entity.TableInfoEntity;

import lombok.Data;

@Data
public class TableDetailRetrieveResponse {
	private TableInfoEntity tableInfo;
	private List<Object> orderList;
}
