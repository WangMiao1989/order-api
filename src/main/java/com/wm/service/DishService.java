package com.wm.service;

import java.util.List;

import com.wm.entity.DishInfoEntity;
import com.wm.requestDto.DishDeleteRequestForm;
import com.wm.requestDto.DishUpdateRequestForm;

public interface DishService {
	public List<DishInfoEntity> dishRetrieve();
	
	public void dishUpdate(DishUpdateRequestForm request);
	
	public void dishDelete(DishDeleteRequestForm request);
}
