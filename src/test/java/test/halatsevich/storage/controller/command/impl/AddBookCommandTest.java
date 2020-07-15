package test.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.controller.command.impl.AddBookCommand;
import by.halatsevich.storage.exception.DaoException;
import by.halatsevich.storage.model.dao.BookStorageDao;
import by.halatsevich.storage.model.dao.impl.BookStorageDaoImpl;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.util.IdGenerator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class AddBookCommandTest {
    Command command;
    Map<String, String> bookParameters = new HashMap<>();

    @BeforeMethod
    public void setUp() {
        IdGenerator.setId(1);
        command = new AddBookCommand();
        bookParameters.put("name", "Планирование в аудите");
        bookParameters.put("authors", "С.М. Бычкова, А.В. Газорян");
        bookParameters.put("price", "594.0");
        bookParameters.put("pages", "293");
        bookParameters.put("sorting tag", "Name");
        bookParameters.put("ascend", "true");
    }

    @AfterMethod
    public void tearDown() throws DaoException {
        BookStorageDao storage = BookStorageDaoImpl.getInstance();
        List<Book> books = storage.selectAll();
        for (Book book : books) {
            storage.removeBookById(book.getBookId());
        }
    }

    @Test
    public void testExecuteSuccess() {
        Map<String, String> actual = command.execute(bookParameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("result of command ->", "true");
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteFailure() {
        bookParameters.put("name", "");
        Map<String, String> actual = command.execute(bookParameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("result of command ->", "Name length of the book is less than 1");
        assertEquals(actual, expected);
    }
}