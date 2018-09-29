package com.elastic.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elastic.example.biz.BookBiz;
import com.elastic.example.model.Book;
import com.elastic.example.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController implements BookService {

	@Autowired
	BookBiz bookBiz;

	public Book insertBook(@RequestBody Book book) {
		System.out.println("books");
		return bookBiz.insertBook(book);
	}

	public Map<String, Object> getBookById(@RequestParam String id) {
		return bookBiz.getBookById(id);
	}

	public Map<String, Object> updateBookById(@RequestParam String id, @RequestBody Book book) {
		return bookBiz.updateBook(id, book);
	}

	public void deleteBookById(@RequestParam String id) {
		bookBiz.deleteBookById(id);
	}

}
