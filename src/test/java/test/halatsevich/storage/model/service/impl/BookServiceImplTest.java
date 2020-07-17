package test.halatsevich.storage.model.service.impl;

import by.halatsevich.storage.exception.ServiceException;
import by.halatsevich.storage.model.comparator.SortingTag;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.service.impl.BookServiceImpl;
import by.halatsevich.storage.model.util.IdGenerator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class BookServiceImplTest {
    BookServiceImpl service = null;
    Map<String, String> bookParameters = new HashMap<>();
    Map<String, String> bookParameters1 = new HashMap<>();
    Map<String, String> bookParameters2 = new HashMap<>();
    Map<String, String> bookParameters3 = new HashMap<>();
    Book book1;
    Book book2;
    Book book3;
    Book book4;

    @BeforeMethod
    public void setUp() throws ServiceException {
        IdGenerator.setId(1);
        service = BookServiceImpl.getInstance();
        bookParameters.put("name", "Планирование в аудите");
        bookParameters.put("authors", "С.М. Бычкова, А.В. Газорян");
        bookParameters.put("price", "594.0");
        bookParameters.put("pages", "293");
        bookParameters.put("sorting tag", "Name");
        bookParameters.put("ascend", "true");
        bookParameters1.put("name", "Name1");
        bookParameters1.put("authors", "Author 1, Author 2");
        bookParameters1.put("price", "45.9");
        bookParameters1.put("pages", "111");
        bookParameters2.put("name", "Name2");
        bookParameters2.put("authors", "Author 1");
        bookParameters2.put("price", "49");
        bookParameters2.put("pages", "75");
        bookParameters3.put("name", "Name3");
        bookParameters3.put("authors", "Author 1, Author 2");
        bookParameters3.put("price", "7.9");
        bookParameters3.put("pages", "76");
        service.addBookIntoStorage(bookParameters1);
        service.addBookIntoStorage(bookParameters2);
        book1 = new Book(1, "Name1", Arrays.asList("Author 1", "Author 2"), 111, 45.9);
        book2 = new Book(2, "Name2", Arrays.asList("Author 1"), 75, 49);
        book3 = new Book(3, "Name3", Arrays.asList("Author 1", "Author 2"), 76, 7.9);
        book4 = new Book(4, "Name1", Arrays.asList("Author 2"), 76, 7891.1);
    }

    @AfterMethod
    public void tearDown() throws ServiceException {
        List<Book> books = service.selectAllBooksFromStorage();
        for (Book b : books) {
            service.removeBookFromStorageById(b.getBookId());
        }
        service = null;
        book1 = null;
        book2 = null;
        book3 = null;
        book4 = null;
        bookParameters.clear();
        bookParameters1.clear();
        bookParameters2.clear();
        bookParameters3.clear();
    }

    @Test
    public void testAddBookIntoStorageSuccess() throws ServiceException {
        boolean condition = service.addBookIntoStorage(bookParameters3);
        assertTrue(condition);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddBookIntoStorageFailure() throws ServiceException {
        service.addBookIntoStorage(bookParameters1);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Parameters of the book are null")
    public void testAddBookIntoStorageNullDataExceptionMessage() throws ServiceException {
        service.addBookIntoStorage(null);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Name length of the book is less than 1")
    public void testAddBookIntoStorageInvalidNameExceptionMessage() throws ServiceException {
        bookParameters.put("name", "");
        service.addBookIntoStorage(bookParameters);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Authors of the book are null")
    public void testAddBookIntoStorageInvalidAuthorsExceptionMessage() throws ServiceException {
        bookParameters.put("authors", "");
        service.addBookIntoStorage(bookParameters);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Count of page of the book is less than 1")
    public void testAddBookIntoStorageInvalidPagesExceptionMessage() throws ServiceException {
        bookParameters.put("pages", "0");
        service.addBookIntoStorage(bookParameters);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Price of the book is less than 0.1")
    public void testAddBookIntoStorageInvalidPriceExceptionMessage() throws ServiceException {
        bookParameters.put("price", "0");
        service.addBookIntoStorage(bookParameters);
    }

    @Test
    public void testRemoveBookFromStorageByIdSuccess() throws ServiceException {
        List<Book> books = service.selectAllBooksFromStorage();
        books.get(0).setBookId(1);
        boolean condition = service.removeBookFromStorageById(1);
        assertTrue(condition);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testRemoveBookFromStorageByIdFailure() throws ServiceException {
        service.removeBookFromStorageById(1000);
    }

    @Test
    public void testRemoveBooksFromStorageByNameSuccess() throws ServiceException {
        boolean condition = service.removeBooksFromStorageByName("Name1");
        assertTrue(condition);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testRemoveBooksFromStorageByNameFailure() throws ServiceException {
        service.removeBooksFromStorageByName("hello");
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Name length of the book is less than 1")
    public void testRemoveBooksFromStorageByNameInvalidNameExceptionMessage() throws ServiceException {
        service.removeBooksFromStorageByName("");
    }

    @Test
    public void testSelectAllBooksFromStorageSuccess() throws ServiceException {
        List<Book> actual = service.selectAllBooksFromStorage();
        List<Book> expected = Arrays.asList(book1, book2);
        assertEquals(actual, expected);
    }

    @Test
    public void testSelectAllBooksFromStorageFailure() {
        List<Book> actual = service.selectAllBooksFromStorage();
        List<Book> expected = Arrays.asList(book1);
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindBookByIdIntoStorageSuccess() throws ServiceException {
        service.selectAllBooksFromStorage().get(0).setBookId(1);
        Book actual = service.findBookByIdIntoStorage(1);
        assertEquals(actual, book1);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindBookByIdIntoStorageFailure() throws ServiceException {
        service.findBookByIdIntoStorage(1000);
    }

    @Test
    public void testFindBooksByNameIntoStorageSuccess() throws ServiceException {
        List<Book> actual = service.findBooksByNameIntoStorage("Name1");
        List<Book> expected = Arrays.asList(book1);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Name length of the book is less than 1")
    public void testFindBooksByNameIntoStorageInvalidNameExceptionMessage() throws ServiceException {
        service.findBooksByNameIntoStorage("");
    }

    @Test
    public void testFindBooksByAuthorsIntoStorageSuccess() throws ServiceException {
        List<Book> actual = service.findBooksByAuthorsIntoStorage(Arrays.asList("Author 1"));
        List<Book> expected = Arrays.asList(book1, book2);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Authors of the book are null")
    public void testFindBooksByAuthorsIntoStorageInvalidAuthorsExceptionMessage() throws ServiceException {
        service.findBooksByAuthorsIntoStorage(Arrays.asList("", "A1"));
    }

    @Test
    public void testFindBooksByCountOfPagesIntoStorageSuccess() throws ServiceException {
        List<Book> actual = service.findBooksByCountOfPagesIntoStorage(75);
        List<Book> expected = Arrays.asList(book2);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Count of page of the book is less than 1")
    public void testFindBooksByCountOfPagesIntoStorageInvalidPagesExceptionMessage() throws ServiceException {
        service.findBooksByCountOfPagesIntoStorage(0);
    }

    @Test
    public void testFindBooksByPriceIntoStorageSuccess() throws ServiceException {
        List<Book> actual = service.findBooksByPriceIntoStorage(49);
        List<Book> expected = Arrays.asList(book2);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = "Price of the book is less than 0.1")
    public void testFindBooksByPriceIntoStorageInvalidPriceExceptionMessage() throws ServiceException {
        service.findBooksByPriceIntoStorage(0);
    }

    @Test
    public void testSortBooksByTagIntoStorageSuccess() {
        List<Book> actual = service.sortBooksByTagIntoStorage(SortingTag.PRICE, false);
        List<Book> expected = Arrays.asList(book2, book1);
        assertEquals(actual, expected);
    }

    @Test
    public void testSortBooksByTagIntoStorageFailure() {
        List<Book> actual = service.sortBooksByTagIntoStorage(SortingTag.PRICE, true);
        List<Book> expected = Arrays.asList(book1, book2, book3, book4);
        assertNotEquals(actual, expected);
    }
}