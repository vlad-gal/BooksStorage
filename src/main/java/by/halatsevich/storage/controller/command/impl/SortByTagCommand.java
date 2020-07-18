package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.controller.command.CommandParameter;
import by.halatsevich.storage.model.comparator.SortingTag;
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

public class SortByTagCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        String sortingTag = bookParameters.get(BookParameter.SORTING_TAG).toUpperCase();
        String sortingAscend = bookParameters.get(BookParameter.SORTING_ASCEND);
        SortingTag tag;
        try {
            tag = SortingTag.valueOf(sortingTag);
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, "Error while define sorting tag", e);
            tag = SortingTag.NAME;
        }
        boolean ascend = Boolean.parseBoolean(sortingAscend);
        BookService bookService = BookServiceImpl.getInstance();
        Map<String, String> result = new HashMap<>();
        List<Book> books = bookService.sortBooksByTagIntoStorage(tag, ascend);
        result.put(CommandParameter.RESULT_KEY, Arrays.toString(books.toArray()));
        return result;
    }
}
