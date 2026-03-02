package com.wm.responseDto;

import java.util.List;

import com.wm.entity.DishEntity;

import lombok.Data;

@Data
public class DishRetrieveResponse {
	private List<DishEntity> dishList;
}
