package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.controller.command.CommandParameter;
import by.halatsevich.storage.exception.ServiceException;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.service.BookService;
import by.halatsevich.storage.model.service.impl.BookServiceImpl;
import by.halatsevich.storage.parameter.BookParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        String name = bookParameters.get(BookParameter.BOOK_NAME);
        BookService bookService = BookServiceImpl.getInstance();
        Map<String, String> result = new HashMap<>();
        try {
            List<Book> books = bookService.findBooksByNameIntoStorage(name);
            result.put(CommandParameter.RESULT_KEY, Arrays.toString(books.toArray()));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while finding books by name", e);
            result.put(CommandParameter.RESULT_KEY, e.getMessage());
            return result;
        }
        return result;
    }
}
