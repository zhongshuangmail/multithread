package com.demo.multithread.forkjoin;

import java.util.List;

public class Consumer {
	
	private String name;
	
	
	private List<Order> orders;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
}

