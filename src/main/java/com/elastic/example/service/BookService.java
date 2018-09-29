package com.elastic.example.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elastic.example.model.Book;

public interface BookService {

	@RequestMapping(value = "/insertBook", method = {
			RequestMethod.POST }, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Book insertBook(@RequestBody Book book);

	@RequestMapping(value = "/getBook", method = {RequestMethod.GET}, produces = "application/json" )
	@ResponseBody
	public Map<String, Object> getBookById(@RequestParam String id);
	
	@RequestMapping(value = "/updateBook", method = {RequestMethod.PUT}, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Map<String, Object> updateBookById(@RequestParam String id,@RequestBody Book book);
	
	@RequestMapping(value = "/deleteBook", method = {RequestMethod.DELETE} , produces = "application/json", consumes = "application/json")
	@ResponseBody
	public void deleteBookById(@RequestParam String id);
}
