package com.lq.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.lq.entity.Reader;

@Repository
public class ReaderDao {
	

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	
	public void addReader(Reader customer) {
		sessionFactory.getCurrentSession().save(customer);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Reader> getAllUser() {
		String hql = "from Reader";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	
	
}
