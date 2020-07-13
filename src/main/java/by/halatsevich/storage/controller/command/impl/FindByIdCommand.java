package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.exception.ServiceException;
import by.halatsevich.storage.model.service.BookService;

import java.util.*;

public class FindByIdCommand implements Command {
    private static final String BOOK_ID = "id";

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        long id = Long.parseLong(bookParameters.get(BOOK_ID));
        BookService bookService = new BookService();
        Map<String, String> result = new HashMap<>();
        try {
            Book book = bookService.findBookByIdIntoStorage(id);
            result.put(RESULT_KEY,book.toString());
        } catch (ServiceException e) {
            result.put(RESULT_KEY, EXCEPTION_VALUE);
            return result;
        }
        return result;
    }
}
