package test.halatsevich.storage.model.dao.impl;

import by.halatsevich.storage.exception.DaoException;
import by.halatsevich.storage.model.comparator.SortingTag;
import by.halatsevich.storage.model.dao.BookStorageDao;
import by.halatsevich.storage.model.dao.impl.BookStorageDaoImpl;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.util.IdGenerator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class BookStorageDaoImplTest {
    BookStorageDao storageDao;
    Book book1 = new Book(1, "Name1", Arrays.asList("Author 1", "Author 2"), 111, 45.9);
    Book book2 = new Book(2, "Name2", Arrays.asList("Author 1"), 75, 49);
    Book book3 = new Book(3, "Name3", Arrays.asList("Author 1", "Author 2"), 76, 7.9);
    Book book4 = new Book(4, "Name1", Arrays.asList("Author 2"), 76, 7891.1);

    @BeforeMethod
    public void setUp() throws DaoException {
        IdGenerator.setId(1);
        storageDao = BookStorageDaoImpl.getInstance();
        storageDao.addBook(book1);
        storageDao.addBook(book2);
    }

    @AfterMethod
    public void tearDown() throws DaoException {
        List<Book> books = storageDao.selectAll();
        for (Book book : books) {
            storageDao.removeBookById(book.getBookId());
        }
    }

    @Test
    public void testAddBookSuccess() throws DaoException {
        boolean condition = storageDao.addBook(book3);
        assertTrue(condition);
    }

    @Test(expectedExceptions = DaoException.class,
            expectedExceptionsMessageRegExp = "Book is present in storage")
    public void testAddBookExceptionMessage() throws DaoException {
        storageDao.addBook(book1);
    }

    @Test
    public void testRemoveBookByIdSuccess() throws DaoException {
        storageDao.selectAll().get(0).setBookId(1);
        boolean condition = storageDao.removeBookById(1);
        assertTrue(condition);
    }

    @Test(expectedExceptions = DaoException.class,
            expectedExceptionsMessageRegExp = "Book was not found in a storage")
    public void testRemoveBookByIdFailure() throws DaoException {
        storageDao.removeBookById(10000);
    }

    @Test
    public void testRemoveBooksByNameSuccess() throws DaoException {
        boolean condition = storageDao.removeBooksByName("Name1");
        assertTrue(condition);
    }

    @Test(expectedExceptions = DaoException.class,
            expectedExceptionsMessageRegExp = "Book was not found in a storage")
    public void testRemoveBooksByNameFailure() throws DaoException {
        storageDao.removeBooksByName("111111");
    }

    @Test
    public void testSelectAllSuccess() {
        List<Book> actual = storageDao.selectAll();
        List<Book> expected = Arrays.asList(book1, book2);
        assertEquals(actual, expected);
    }

    @Test
    public void testSelectAllFailure() {
        List<Book> actual = storageDao.selectAll();
        List<Book> expected = Arrays.asList(book1, book2, book3, book4);
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindBookByIdSuccess() throws DaoException {
        storageDao.selectAll().get(0).setBookId(1);
        Book actual = storageDao.findBookById(1);
        assertEquals(actual, book1);
    }

    @Test(expectedExceptions = DaoException.class,
            expectedExceptionsMessageRegExp = "Book with this id was not found")
    public void testFindBookByIdExceptionMessage() throws DaoException {
        storageDao.findBookById(1000);
    }

    @Test
    public void testFindBooksByNameSuccess() {
        List<Book> actual = storageDao.findBooksByName("Name1");
        List<Book> expected = Arrays.asList(book1);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindBooksByNameFailure() {
        List<Book> actual = storageDao.findBooksByName("Name1");
        List<Book> expected = Arrays.asList(book4);
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindBooksByAuthorsSuccess() {
        List<Book> actual = storageDao.findBooksByAuthors(Arrays.asList("Author 1", "Author 2"));
        List<Book> expected = Arrays.asList(book1, book2);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindBooksByAuthorsFailure() {
        List<Book> actual = storageDao.findBooksByAuthors(Arrays.asList("Author 1", "Author 2"));
        List<Book> expected = Arrays.asList(book1);
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindBooksByCountOfPagesSuccess() {
        List<Book> actual = storageDao.findBooksByCountOfPages(111);
        List<Book> expected = Arrays.asList(book1);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindBooksByCountOfPagesFailure() {
        List<Book> actual = storageDao.findBooksByCountOfPages(111);
        List<Book> expected = Arrays.asList(book4);
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindBooksByPriceSuccess() {
        List<Book> actual = storageDao.findBooksByPrice(45.9);
        List<Book> expected = Arrays.asList(book1);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindBooksByPriceFailure() {
        List<Book> actual = storageDao.findBooksByPrice(111);
        List<Book> expected = Arrays.asList(book1);
        assertNotEquals(actual, expected);
    }

    @Test
    public void testSortBooksByTagSuccess() {
        List<Book> actual = storageDao.sortBooksByTag(SortingTag.PRICE, false);
        List<Book> expected = Arrays.asList(book2, book1);
        assertEquals(actual, expected);
    }

    @Test
    public void testSortBooksByTagFailure() {
        List<Book> actual = storageDao.sortBooksByTag(SortingTag.PRICE, true);
        List<Book> expected = Arrays.asList(book1, book2, book3, book4);
        assertNotEquals(actual, expected);
    }
}