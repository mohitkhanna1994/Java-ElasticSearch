/**
 * 
 */
package com.elastic.example.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import com.elastic.example.dao.BookDao;
import com.elastic.example.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BookDaoImpl implements BookDao {

	private final String INDEX = "bookdata";
	private final String TYPE = "books";

	private RestHighLevelClient restHighLevelClient;

	private ObjectMapper objectMapper;

	public BookDaoImpl(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
		this.objectMapper = objectMapper;
		this.restHighLevelClient = restHighLevelClient;
	}

	public Book insertBook(Book book) {
		System.out.println("BOokssss:::");
		book.setId(UUID.randomUUID().toString());
		Map<String, Object> dataMap = objectMapper.convertValue(book, Map.class);
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, book.getId()).source(dataMap);
		try {
			IndexResponse response = restHighLevelClient.index(indexRequest);
		} catch (ElasticsearchException e) {
			e.getDetailedMessage();
		} catch (java.io.IOException ex) {
			ex.getLocalizedMessage();
		}
		return book;
	}

	public Map<String, Object> getBookById(String id) {
		GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
		GetResponse getResponse = null;
		try {
			getResponse = restHighLevelClient.get(getRequest);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        return sourceAsMap;
	}

	public Map<String, Object> updateBook(String id, Book book) {
		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id).fetchSource(true);
		Map<String,Object> error = new HashMap();
		error.put("Error", "Unable to update book");
		try {
			String bookJson = objectMapper.writeValueAsString(book);
			updateRequest.doc(book,XContentType.JSON);
			UpdateResponse updateResponse = restHighLevelClient.update(updateRequest);
			Map<String,Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();
			return sourceAsMap;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}

	public void deleteBook(String id) {
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
		try {
			DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
			System.out.println("Delete Successfull");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
