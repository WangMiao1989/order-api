package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.requestDto.OrderListRetrieveRequestForm;
import com.wm.requestDto.TableDetailRetrieveRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;
import com.wm.service.TableService;

@RestController
@RequestMapping("/table")
public class TableController {
	
	@Autowired
	TableService tableService;
	
	@RequestMapping("/table-detail-retrieve")
	public TableDetailRetrieveResponse tableDetailRetrieve(@RequestBody TableDetailRetrieveRequestForm request) {
		return tableService.tableDetailRetrieve(request);
	}
	
	@RequestMapping("/order-list-retrieve")
	public List<Object> orderListRetrieve(@RequestBody OrderListRetrieveRequestForm request) {
		return tableService.orderListRetrieve(request);
	}
}
