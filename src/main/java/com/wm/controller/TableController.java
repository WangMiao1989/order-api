package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.AllTableEntity;
import com.wm.requestDto.TableNoRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;
import com.wm.service.TableService;
import com.wm.utils.ContextHolder;

@RestController
@RequestMapping("/table")
public class TableController {
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private SseController sseController;
	
	@RequestMapping("/retrieve")
	public TableDetailRetrieveResponse tableDetailRetrieve(@RequestBody TableNoRequestForm request) {
		return tableService.tableDetailRetrieve(request);
	}
	
	@RequestMapping("/all-table/retrieve")
	public List<AllTableEntity> allTableRetrieve() {
		return tableService.allTableRetrieve();
	}
	
	@RequestMapping("/finish")
	public void tableFinish(@RequestBody TableNoRequestForm request) {
		tableService.tableFinish(request);
	}
	
	@RequestMapping("customer/checkout")
	public void customerCheckout(@RequestBody TableNoRequestForm request) {
		sseController.sendEmitter(ContextHolder.getTenantId(), "checkout", request.getTableNo() + "桌结账");
	}
}
