package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.controller.command.CommandParameter;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.service.BookService;
import by.halatsevich.storage.model.service.impl.BookServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectAllCommand implements Command {

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        BookService bookService = BookServiceImpl.getInstance();
        Map<String, String> result = new HashMap<>();
        List<Book> books = bookService.selectAllBooksFromStorage();
        result.put(CommandParameter.RESULT_KEY, Arrays.toString(books.toArray()));
        return result;
    }
}
