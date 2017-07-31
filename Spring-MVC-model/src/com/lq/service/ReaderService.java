package com.lq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.lq.dao.ReaderDao;
import com.lq.entity.Book;
import com.lq.entity.Reader;
import com.lq.entity.User;

@Service
public class ReaderService {
	@Autowired
	private ReaderDao bookDaoImpl;

	public void addReader(Reader reader){
		bookDaoImpl.addReader(reader);
	}
	
	
	public List<Reader> getAllReader() {
		return bookDaoImpl.getAllUser();
	}

}
