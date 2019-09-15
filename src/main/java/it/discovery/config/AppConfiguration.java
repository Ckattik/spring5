package it.discovery.config;

import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XmlBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.MainBookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("application.properties")
public class AppConfiguration {

    @Bean
    @Qualifier("xml")
    @ConditionalOnAppProfile(AppProfile.DEV)
    public BookRepository xmlRepository() {
        return new XmlBookRepository();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Qualifier("db")
    @ConditionalOnAppProfile(AppProfile.PROD)
    public BookRepository dbRepository(Environment env) {
        return new DBBookRepository(env.getProperty("library.server"),
                env.getProperty("library.db"));
    }

    @Bean
    public BookService mainService(BookRepository bookRepository) {
        return new MainBookService(bookRepository);
    }
}