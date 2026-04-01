package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.DailyRevenueInfoEntity;
import com.wm.entity.PeriodRevenueInfoEntity;

@Mapper
public interface StatisticsRepository {
	public List<PeriodRevenueInfoEntity> selectRevenueByPeriod(String startDate, String endDate);
	
	public List<DailyRevenueInfoEntity> selectRevenueByDate(String searchDate);
}
