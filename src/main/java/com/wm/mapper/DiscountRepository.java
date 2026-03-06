package com.wm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.DiscountEntity;

@Mapper
public interface DiscountRepository {
	
	public Long selectDiscountId(Long dishId);
	
	public int insertDiscount(DiscountEntity discount);
	
	public int updateDiscount(DiscountEntity discount);
	
	public void deleteDiscountByDishId(Long dishId);
}
