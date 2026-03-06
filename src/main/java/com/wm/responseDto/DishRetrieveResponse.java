package com.wm.responseDto;

import java.util.List;

import com.wm.entity.DishInfoEntity;

import lombok.Data;

@Data
public class DishRetrieveResponse {
	private List<DishInfoEntity> dishList;
}
