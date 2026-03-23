package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.RevenueInfoEntity;

@Mapper
public interface StatisticsRepository {
	public List<RevenueInfoEntity> selectRevenueByMonth(String startMonth, String endMonth);
}
