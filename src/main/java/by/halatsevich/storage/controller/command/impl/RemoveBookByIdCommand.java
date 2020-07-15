package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.exception.ServiceException;
import by.halatsevich.storage.model.service.BookService;

import java.util.HashMap;
import java.util.Map;

public class RemoveBookByIdCommand implements Command {
    private static final String BOOK_ID = "id";

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        long id = Long.parseLong(bookParameters.get(BOOK_ID));
        BookService bookService = BookService.getInstance();
        Map<String, String> result = new HashMap<>();
        try {
            boolean isRemove = bookService.removeBookFromStorageById(id);
            result.put(RESULT_KEY, String.valueOf(isRemove));
        } catch (ServiceException e) {
            result.put(RESULT_KEY, e.getMessage());
            return result;
        }
        return result;
    }
}
