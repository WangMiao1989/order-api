package com.wm.service;

import java.util.List;

import com.wm.requestDto.OrderListRetrieveRequestForm;
import com.wm.requestDto.TableDetailRetrieveRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;

public interface TableService {
	public TableDetailRetrieveResponse tableDetailRetrieve(TableDetailRetrieveRequestForm request);
	
	public List<Object> orderListRetrieve(OrderListRetrieveRequestForm request);
}
