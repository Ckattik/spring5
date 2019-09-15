package it.discovery.repository;

import it.discovery.model.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles XML-related book operations
 *
 * @author morenets
 */

public class XmlBookRepository implements BookRepository {
    private final Map<Integer, Book> books = new HashMap<>();

    private int counter = 0;

    private String file = "book.xml";

    public void init() {
        System.out.println("Using XML file:" + file);
    }

    public void destroy() {
        System.out.println("Shutting down repository ... ");
    }

    @Override
    public void saveBook(Book book) {
        if (book.getId() == 0) {
            counter++;
            book.setId(counter);
        }

        books.put(book.getId(), book);

        System.out.println("Saved book " + book);
    }

    @Override
    public Book findBookById(int id) {
        return books.get(id);
    }

    @Override
    public List<Book> findBooks() {
        return new ArrayList<>(books.values());
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}