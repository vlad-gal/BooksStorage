package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.controller.command.CommandParameter;
import by.halatsevich.storage.exception.ServiceException;
import by.halatsevich.storage.model.service.BookService;
import by.halatsevich.storage.model.service.impl.BookServiceImpl;
import by.halatsevich.storage.parameter.BookParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RemoveBooksByNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        String name = bookParameters.get(BookParameter.BOOK_NAME);
        BookService bookService = BookServiceImpl.getInstance();
        Map<String, String> result = new HashMap<>();
        try {
            boolean isRemove = bookService.removeBooksFromStorageByName(name);
            result.put(CommandParameter.RESULT_KEY, String.valueOf(isRemove));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while removing books by name", e);
            result.put(CommandParameter.RESULT_KEY, e.getMessage());
            return result;
        }
        return result;
    }
}
