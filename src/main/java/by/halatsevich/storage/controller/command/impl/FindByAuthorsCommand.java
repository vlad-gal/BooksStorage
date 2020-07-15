package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.exception.ServiceException;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.service.BookService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByAuthorsCommand implements Command {
    private static final String BOOK_AUTHORS = "authors";
    private static final String REGEX_DELIMITER = ",";

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        List<String> authors = Arrays.asList(bookParameters.get(BOOK_AUTHORS).split(REGEX_DELIMITER));
        BookService bookService = BookService.getInstance();
        Map<String, String> result = new HashMap<>();
        try {
            List<Book> books = bookService.findBooksByAuthorsIntoStorage(authors);
            result.put(RESULT_KEY, Arrays.toString(books.toArray()));
        } catch (ServiceException e) {
            result.put(RESULT_KEY, e.getMessage());
            return result;
        }
        return result;
    }
}
