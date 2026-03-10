package com.wm.requestDto;

import java.util.List;

import lombok.Data;

@Data
public class OrderCheckoutRequestForm {
	private List<Long> orderIdList;
}