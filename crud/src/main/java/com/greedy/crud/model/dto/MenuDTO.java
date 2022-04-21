package com.greedy.crud.model.dto;

import lombok.Data;

@Data
public class MenuDTO {

	private int code;
	private String name;
	private int price;
	private int categoryCode;
	private String orderableStatus;
}
