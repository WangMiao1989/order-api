package com.wm.responseDto;

import java.util.List;

import com.wm.entity.DishEntity;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class DishRetrieveResponse {
	List<DishEntity> dishList;
}
