package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.exception.ServiceException;
import by.halatsevich.storage.model.service.BookService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByPriceCommand implements Command {
    private static final String BOOK_PRICE = "price";

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        double price = Double.parseDouble(bookParameters.get(BOOK_PRICE));
        BookService bookService = new BookService();
        Map<String, String> result = new HashMap<>();
        try {
            List<Book> books = bookService.findBooksByPriceIntoStorage(price);
            result.put(RESULT_KEY, Arrays.toString(books.toArray()));
        } catch (ServiceException e) {
            result.put(RESULT_KEY, EXCEPTION_VALUE);
            return result;
        }
        return result;
    }
}
