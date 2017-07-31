package com.lq.service;

import com.lq.entity.Customer;

public interface CustomerService {
	public void addCustomer(Customer customer);
	public Customer getCustomer(String name,String pwd);
}
