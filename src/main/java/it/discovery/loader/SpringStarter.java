package it.discovery.loader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.discovery.config.AppConfiguration;
import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import it.discovery.service.BookService;

public class SpringStarter {
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class)) {
			BookService service = context.getBean(BookService.class);

			Book book = new Book();
			book.setName("Introduction into Spring");
			book.setPages(100);
			book.setYear(2016);
			service.saveBook(book);

			List<Book> books = service.findBooks();
			System.out.println(books);

			System.out.println("Beans number: " + context.getBeanDefinitionCount());
			System.out.println("BookRepository beans: " +
					Arrays.stream(context.getBeanNamesForType(BookRepository.class))
							.collect(Collectors.joining(",")));
			System.out.println("Bean names: " + Arrays.stream(
					context.getBeanDefinitionNames()).collect(Collectors.joining(",")));
		}

	}

}