package com.wm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.TableInfoEntity;
import com.wm.requestDto.TableIdRetrieveRequestForm;
import com.wm.responseDto.TableDetailRetrieveResponse;
import com.wm.service.TableService;

@RestController
@RequestMapping("/table")
public class TableController {
	
	@Autowired
	TableService tableService;
	
	@RequestMapping("/table-detail-retrieve")
	public TableDetailRetrieveResponse tableDetailRetrieve(@RequestBody TableIdRetrieveRequestForm request) {
		return tableService.tableDetailRetrieve(request);
	}
}
