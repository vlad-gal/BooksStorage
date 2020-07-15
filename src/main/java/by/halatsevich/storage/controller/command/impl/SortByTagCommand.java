package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.model.comparator.type.SortingTag;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.service.BookService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByTagCommand implements Command {
    private static final String SORTING_TAG = "sorting tag";
    private static final String SORTING_ASCEND = "ascend";

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        String sortingTag = bookParameters.get(SORTING_TAG).toUpperCase();
        String sortingAscend = bookParameters.get(SORTING_ASCEND);
        SortingTag tag;
        try {
            tag = SortingTag.valueOf(sortingTag);
        } catch (IllegalArgumentException e) {
            tag = SortingTag.NAME;
        }
        boolean ascend = Boolean.parseBoolean(sortingAscend);
        BookService bookService = BookService.getInstance();
        Map<String, String> result = new HashMap<>();
        List<Book> books = bookService.sortBooksByTagIntoStorage(tag, ascend);
        result.put(RESULT_KEY, Arrays.toString(books.toArray()));
        return result;
    }
}
