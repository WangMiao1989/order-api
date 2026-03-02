package com.wm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.TableInfoEntity;
import com.wm.mapper.OrderRepository;
import com.wm.mapper.TableRepository;
import com.wm.requestDto.TableIdRetrieveRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;
import com.wm.service.TableService;

@Service
@Transactional
public class TableServiceImpl implements TableService{
	
	@Autowired
	TableRepository tableRepository;
	
	public TableDetailRetrieveResponse tableDetailRetrieve(TableIdRetrieveRequestForm request) {
		TableDetailRetrieveResponse response = new TableDetailRetrieveResponse();
		
		// table信息取得
		TableInfoEntity tableInfo = new TableInfoEntity();
		tableInfo =  tableRepository.selectTableInfo(request.getTableNo());
		
		// 订单明细取得
		List<Object> orderList = new ArrayList<>();
		// 用餐中的情况
		if (!Objects.isNull(tableInfo.getTableId())) {
			orderList = tableRepository.selectOrderList(tableInfo.getTableId());
		}
		
		response.setTableInfo(tableInfo);
		response.setOrderList(orderList);
		
		return response;
	}
}
