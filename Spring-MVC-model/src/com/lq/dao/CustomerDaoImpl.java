package com.lq.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lq.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao<Customer> {
	
	

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void addCustomer(Customer customer) {
		sessionFactory.getCurrentSession().save(customer);
	}

	public Customer getCustomer(String name,String password){
		String hql = "from Customer c where c.name=? and c.passWord=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, name);
		query.setString(1, password);
		return (Customer)query.uniqueResult();
	}
}
