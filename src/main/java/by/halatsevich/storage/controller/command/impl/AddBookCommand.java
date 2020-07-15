package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.exception.ServiceException;
import by.halatsevich.storage.model.service.BookService;

import java.util.HashMap;
import java.util.Map;

public class AddBookCommand implements Command {

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        BookService bookService = BookService.getInstance();
        Map<String, String> result = new HashMap<>();
        try {
            boolean isAdd = bookService.addBookIntoStorage(bookParameters);
            result.put(RESULT_KEY, String.valueOf(isAdd));
        } catch (ServiceException e) {
            result.put(RESULT_KEY, e.getMessage());
            return result;
        }
        return result;
    }
}
