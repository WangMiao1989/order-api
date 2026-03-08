package com.wm.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.DiscountEntity;
import com.wm.entity.DishEntity;
import com.wm.entity.DishInfoEntity;
import com.wm.mapper.DiscountRepository;
import com.wm.mapper.DishRepository;
import com.wm.requestDto.DishDeleteRequestForm;
import com.wm.requestDto.DishUpdateRequestForm;
import com.wm.service.DishService;

@Service
@Transactional
public class DishServiceImpl implements DishService{
	
	@Autowired
	private DishRepository dishRepository;
	
	@Autowired
	private DiscountRepository discountRepository;
	
	// 菜品取得
	public List<DishInfoEntity> dishRetrieve() {
		return dishRepository.selectAllDishes();
	}
	
	// 菜品更新
	public void dishUpdate(DishUpdateRequestForm request) {

		DishEntity dish = new DishEntity();
		BeanUtils.copyProperties(request, dish);
		
		// 图片处理
		byte[] image = Base64.getDecoder().decode(request.getImage());
		dish.setImage(image);
		
		// 菜品保存
		if(Objects.isNull(dish.getDishId())) {
			dishRepository.insertDish(dish);
		} else {
			dishRepository.updateDish(dish);
		}
		
		// 有效中的折扣取得
		Long discountId = discountRepository.selectDiscountId(dish.getDishId());

		// 折扣保存
		if(Boolean.TRUE.equals(request.getHasDiscount())) {
			DiscountEntity discount = new DiscountEntity();
			BeanUtils.copyProperties(request, discount);
			discount.setDishId(dish.getDishId());
			discount.setDiscountId(discountId);
			if(Objects.isNull(discountId)) {
				// 折扣登录
				discountRepository.insertDiscount(discount);
			} else {
				// 折扣更新
				discountRepository.updateDiscount(discount);
			}
			// 折扣存在&&这次没设定
		} else if(!Objects.isNull(discountId)){
			discountRepository.deleteDiscountByDishId(dish.getDishId());
		}
	}
	
	// 菜品删除
	public void dishDelete(DishDeleteRequestForm request) {
		dishRepository.deleteDish(request.getDishId());
		discountRepository.deleteDiscountByDishId(request.getDishId());
	}
}
