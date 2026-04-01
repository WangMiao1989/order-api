package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.DailyRevenueInfoEntity;
import com.wm.entity.PeriodRevenueInfoEntity;
import com.wm.requestDto.DailyRevenueRetrieveRequestForm;
import com.wm.requestDto.PeriodRevenueRetrieveRequestForm;
import com.wm.service.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;
	
	@RequestMapping("/period-revenue/retrieve")
	public List<PeriodRevenueInfoEntity> periodRevenueRetrieve(@RequestBody PeriodRevenueRetrieveRequestForm request) {
		return statisticsService.periodRevenueRetrieve(request);
	}
	
	@RequestMapping("/daily-revenue/retrieve")
	public List<DailyRevenueInfoEntity> dailyRevenueRetrieve(@RequestBody DailyRevenueRetrieveRequestForm request) {
		return statisticsService.dailyRevenueRetrieve(request);
	}
}
