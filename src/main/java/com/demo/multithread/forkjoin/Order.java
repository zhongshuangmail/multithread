package com.demo.multithread.forkjoin;

import java.math.BigDecimal;
import java.util.List;

public class Order {
	
	
	private BigDecimal totalPrice;
	
	List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
	
}
