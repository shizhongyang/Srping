package com.lq.dao;

import com.lq.entity.Customer;

public interface CustomerDao<T> {
	public void addCustomer(T customer);
	public T getCustomer(String name,String password);
}
