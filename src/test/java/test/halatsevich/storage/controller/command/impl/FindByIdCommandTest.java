package test.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.controller.command.impl.FindByIdCommand;
import by.halatsevich.storage.exception.DaoException;
import by.halatsevich.storage.model.dao.BookStorageDao;
import by.halatsevich.storage.model.dao.impl.BookStorageDaoImpl;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.entity.BookStorage;
import by.halatsevich.storage.model.util.IdGenerator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindByIdCommandTest {
    Command command;
    Map<String, String> bookParameters = new HashMap<>();

    @BeforeMethod
    public void setUp() {
        IdGenerator.setId(1);
        command = new FindByIdCommand();
        bookParameters.put("id", "1");
        BookStorage instance = BookStorage.getInstance();
        instance.addBook(new Book(1, "Name1", Arrays.asList("Author 1", "Author 2"), 111, 45.9));
        instance.addBook(new Book(2, "Name2", Arrays.asList("Author 1"), 75, 49));
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
        expected.put("result of command ->", "Book{bookId=1, name='Name1', authors=[Author 1, Author 2], pages=111, price=45.9}");
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteFailure() {
        bookParameters.put("id", "1000");
        Map<String, String> actual = command.execute(bookParameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("result of command ->", "Book with this id was not found");
        assertEquals(actual, expected);
    }
}