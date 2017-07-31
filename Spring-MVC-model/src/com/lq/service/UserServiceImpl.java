package com.lq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lq.dao.CustomerDao;
import com.lq.dao.UserDao;
import com.lq.entity.Customer;
import com.lq.entity.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CustomerDao<Customer> customerDao;

	public User getUser(String id) {
		return userDao.getUser(id);
	}

	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	public void addUser(User user) {
		/* 用于测试hibernate的事务机制是否起作用
		 * 在数据库中把user表的age字段设置为Not NULL，即输入null值会报错
		 * 正确保存customer数据后，user数据会因为null值报错
		 * 此时，若事务机制启动，则customer和user的数据都不会保存*/
		Customer customer = new Customer();
		customer.setAge("15");
		customer.setName("43");
		customerDao.addCustomer(customer);
		//user.setAge(null);
		userDao.addUser(user);
	}

	public boolean delUser(String id) {

		return userDao.delUser(id);
	}

	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}
}
