package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.model.exception.ServiceException;
import by.halatsevich.storage.model.service.BookService;

import java.util.HashMap;
import java.util.Map;

public class RemoveBookCommand implements Command {

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        BookService bookService = new BookService();
        Map<String, String> result = new HashMap<>();
        try {
            boolean isRemove = bookService.removeBookFromStorage(bookParameters);
            result.put(RESULT_KEY, String.valueOf(isRemove));
        } catch (ServiceException e) {
            result.put(RESULT_KEY, EXCEPTION_VALUE);
            return result;
        }
        return result;
    }
}
