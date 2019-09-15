package it.discovery.service;

import java.util.List;

import it.discovery.model.Book;

public interface BookService {
	public void saveBook(Book book);
	
	public Book findBookById(int id);

	public List<Book> findBooks();
}
