package com.lq.dao;

import java.util.List;

import javax.annotation.Resource;

import com.lq.entity.User;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//注入
@Repository
public class UserDaoImpl implements UserDao {
	
	

	//注入已在spring-common.xml中配制好的sessionFactory
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public User getUser(String id) {

		String hql = "from User u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);

		return (User) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {

		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public boolean delUser(String id) {

		String hql = "delete User u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);

		return (query.executeUpdate() > 0);
	}

	public boolean updateUser(User user) {

		String hql = "update User u set u.userName = ?,u.age=? where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getUserName());
		query.setLong(1, user.getAge());
		query.setString(2, user.getId());

		return (query.executeUpdate() > 0);
	}

}
