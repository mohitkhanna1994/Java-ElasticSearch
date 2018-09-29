package com.elastic.example.dao;

import java.util.Map;

import com.elastic.example.model.Book;

public interface BookDao {

	public Map<String, Object> getBookById(String id);
	
	public Book insertBook(Book book);

	public Map<String, Object> updateBook(String id, Book book);

	public void deleteBook(String id);
}
