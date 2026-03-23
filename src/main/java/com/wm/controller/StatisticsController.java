package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.RevenueInfoEntity;
import com.wm.requestDto.RevenueRetrieveRequestForm;
import com.wm.service.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;
	
	@RequestMapping("/revenue/retrieve")
	public List<RevenueInfoEntity> revenueRetrieve(@RequestBody RevenueRetrieveRequestForm request) {
		return statisticsService.revenueRetrieve(request);
	}
}
