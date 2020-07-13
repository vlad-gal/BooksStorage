package by.halatsevich.storage.model.creator;

import by.halatsevich.storage.model.entity.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BookCreator {
    private static final String BOOK_NAME = "name";
    private static final String BOOK_AUTHORS = "authors";
    private static final String BOOK_PAGES = "pages";
    private static final String BOOK_PRICE = "price";
    private static final String REGEX_DELIMITER = ", ";

    public Book createBook(Map<String,String> bookData) {
        String name = bookData.get(BOOK_NAME);
        String[] splitAuthors = bookData.get(BOOK_AUTHORS).split(REGEX_DELIMITER);
        List<String> authors = new ArrayList<>();
        Collections.addAll(authors, splitAuthors);
        int pages = Integer.parseInt(bookData.get(BOOK_PAGES));
        double price = Double.parseDouble(bookData.get(BOOK_PRICE));
        return new Book(name, authors, pages, price);
    }
}
