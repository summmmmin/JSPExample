package com.yedam.prod.mapper;

import java.util.List;

import com.yedam.prod.domain.ProductVO;

public interface ProductMapper {
	//상품목록
	public List<ProductVO> productList();
	//상품상세
	public ProductVO getProduct(int productId);
}
