package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.controller.command.CommandParameter;
import by.halatsevich.storage.exception.ServiceException;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.service.BookService;
import by.halatsevich.storage.model.service.impl.BookServiceImpl;
import by.halatsevich.storage.parameter.BookParameter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByPriceCommand implements Command {

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        double price = Double.parseDouble(bookParameters.get(BookParameter.BOOK_PRICE));
        BookService bookService = BookServiceImpl.getInstance();
        Map<String, String> result = new HashMap<>();
        try {
            List<Book> books = bookService.findBooksByPriceIntoStorage(price);
            result.put(CommandParameter.RESULT_KEY, Arrays.toString(books.toArray()));
        } catch (ServiceException e) {
            result.put(CommandParameter.RESULT_KEY, e.getMessage());
            return result;
        }
        return result;
    }
}
