package com.elastic.example.biz;

import java.util.Map;

import com.elastic.example.model.Book;

public interface BookBiz {

	public Book insertBook(Book book);
	
	public Map<String, Object> getBookById(String id);

	public Map<String, Object> updateBook(String id, Book book);

	public void deleteBookById(String id);
}
