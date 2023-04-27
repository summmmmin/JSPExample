package com.yedam.prod.domain;

import lombok.Data;

@Data
public class ProductVO {  
	
	private int productId;
	private String productName;
	private String productExplain;
	private int productPrice;
	private int productDiscount;
	private int productScore;
	private String productImg;
}
