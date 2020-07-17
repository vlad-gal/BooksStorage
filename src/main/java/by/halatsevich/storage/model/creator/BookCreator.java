package by.halatsevich.storage.model.creator;

import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.parameter.BookParameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BookCreator {

    public Book createBook(Map<String, String> bookData) {
        String name = bookData.get(BookParameter.BOOK_NAME);
        String[] splitAuthors = bookData.get(BookParameter.BOOK_AUTHORS).split(BookParameter.REGEX_DELIMITER);
        List<String> authors = new ArrayList<>();
        Collections.addAll(authors, splitAuthors);
        int pages = Integer.parseInt(bookData.get(BookParameter.BOOK_PAGES));
        double price = Double.parseDouble(bookData.get(BookParameter.BOOK_PRICE));
        return new Book(name, authors, pages, price);
    }
}
