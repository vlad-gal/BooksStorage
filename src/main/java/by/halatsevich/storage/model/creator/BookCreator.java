package by.halatsevich.storage.model.creator;

import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.parameter.BookParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BookCreator {
    private static final Logger logger = LogManager.getLogger();

    public Book createBook(Map<String, String> bookData) {
        String name = bookData.get(BookParameter.BOOK_NAME);
        String[] splitAuthors = bookData.get(BookParameter.BOOK_AUTHORS).split(BookParameter.REGEX_DELIMITER);
        List<String> authors = new ArrayList<>();
        Collections.addAll(authors, splitAuthors);
        int pages = Integer.parseInt(bookData.get(BookParameter.BOOK_PAGES));
        double price = Double.parseDouble(bookData.get(BookParameter.BOOK_PRICE));
        logger.log(Level.DEBUG, "Book was created with parameters {}", bookData);
        return new Book(name, authors, pages, price);
    }
}
