package com.lq.service;

import org.aspectj.weaver.tools.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lq.dao.CustomerDao;
import com.lq.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired()
	private CustomerDao<Customer> customerDao;

	public void addCustomer(Customer customer) {
		customerDao.addCustomer(customer);
	}
	
	public Customer getCustomer(String name,String pwd){
		return customerDao.getCustomer(name,pwd);		
	}
}
