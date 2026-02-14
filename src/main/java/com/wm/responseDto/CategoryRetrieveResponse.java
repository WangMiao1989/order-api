package com.wm.responseDto;

import java.util.List;

import com.wm.entity.CategoryEntity;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class CategoryRetrieveResponse {
	List<CategoryEntity> categoryList;
}
