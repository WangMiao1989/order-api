package com.wm.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.TableEntity;
import com.wm.controller.SseController;
import com.wm.entity.AllTableEntity;
import com.wm.mapper.TableRepository;
import com.wm.requestDto.TableNoRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;
import com.wm.service.TableService;

@Service
@Transactional
public class TableServiceImpl implements TableService{
	
	@Autowired
	TableRepository tableRepository;
	
	@Autowired
	SseController sseController;
	
	public TableDetailRetrieveResponse tableDetailRetrieve(TableNoRequestForm request) {
		TableDetailRetrieveResponse response = new TableDetailRetrieveResponse();
		
		// table信息取得
		TableEntity tableInfo =  tableRepository.selectTableInfo(request.getTableNo());
		if(!Objects.isNull(tableInfo)) {
			BeanUtils.copyProperties(tableInfo, response);
		}

		return response;
	}
	
	public List<AllTableEntity> allTableRetrieve(){
		return tableRepository.selectAllTable();
	}
	
	public void tableFinish(TableNoRequestForm request) {
		tableRepository.updateTableEndtime(request.getTableNo());
		
		// sse触发table更新
		sseController.notifyTableUpdated();
		// sse触发order更新
		sseController.notifyOrderUpdated("[]");
	}
}
