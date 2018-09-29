package com.elastic.example.biz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elastic.example.biz.BookBiz;
import com.elastic.example.dao.BookDao;
import com.elastic.example.model.Book;

@Service
public class BookBizImpl implements BookBiz {

	@Autowired
	BookDao bookDao;
	
	public Book insertBook(Book book) {		
		return bookDao.insertBook(book);
	}

	public Map<String, Object> getBookById(String id) {
		return bookDao.getBookById(id);
	}

	public Map<String, Object> updateBook(String id, Book book) {
		return bookDao.updateBook(id,book);
	}

	public void deleteBookById(String id) {
		bookDao.deleteBook(id);
	}

}
