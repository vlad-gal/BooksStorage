package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.exception.ServiceException;
import by.halatsevich.storage.model.service.BookService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByPagesCommand implements Command {
    private static final String BOOK_PAGES = "pages";

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        int pages = Integer.parseInt(bookParameters.get(BOOK_PAGES));
        BookService bookService = new BookService();
        Map<String, String> result = new HashMap<>();
        try {
            List<Book> books = bookService.findBooksByCountOfPagesIntoStorage(pages);
            result.put(RESULT_KEY, Arrays.toString(books.toArray()));
        } catch (ServiceException e) {
            result.put(RESULT_KEY, EXCEPTION_VALUE);
            return result;
        }
        return result;
    }
}
