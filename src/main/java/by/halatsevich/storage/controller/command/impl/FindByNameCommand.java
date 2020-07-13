package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.exception.ServiceException;
import by.halatsevich.storage.model.service.BookService;

import java.util.*;

public class FindByNameCommand implements Command {
    private static final String BOOK_NAME = "name";

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        String name = bookParameters.get(BOOK_NAME);
        BookService bookService = new BookService();
        Map<String, String> result = new HashMap<>();
        try {
            List<Book> books = bookService.findBooksByNameIntoStorage(name);
            result.put(RESULT_KEY, Arrays.toString(books.toArray()));
        } catch (ServiceException e) {
            result.put(RESULT_KEY, EXCEPTION_VALUE);
            return result;
        }
        return result;
    }
}
