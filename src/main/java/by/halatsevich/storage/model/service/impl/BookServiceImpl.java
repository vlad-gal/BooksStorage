package by.halatsevich.storage.model.service.impl;

import by.halatsevich.storage.exception.DaoException;
import by.halatsevich.storage.exception.ServiceException;
import by.halatsevich.storage.model.comparator.SortingTag;
import by.halatsevich.storage.model.creator.BookCreator;
import by.halatsevich.storage.model.dao.BookStorageDao;
import by.halatsevich.storage.model.dao.impl.BookStorageDaoImpl;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.service.BookService;
import by.halatsevich.storage.model.validator.BookValidator;
import by.halatsevich.storage.parameter.BookParameter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BookServiceImpl implements BookService {
    private static BookServiceImpl instance;

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            return new BookServiceImpl();
        }
        return instance;
    }

    public boolean addBookIntoStorage(Map<String, String> bookParameters) throws ServiceException {
        if (bookParameters == null) {
            throw new ServiceException("Parameters of the book are null");
        }
        String name = bookParameters.get(BookParameter.BOOK_NAME);
        List<String> authors = Arrays.asList(bookParameters.get(BookParameter.BOOK_AUTHORS).split(BookParameter.REGEX_DELIMITER));
        int pages = Integer.parseInt(bookParameters.get(BookParameter.BOOK_PAGES));
        double price = Double.parseDouble(bookParameters.get(BookParameter.BOOK_PRICE));
        if (!BookValidator.isValidNameLength(name)) {
            throw new ServiceException("Name length of the book is less than 1");
        }
        if (!BookValidator.isValidAuthors(authors)) {
            throw new ServiceException("Authors of the book are null");
        }
        if (!BookValidator.isValidCountOfPages(pages)) {
            throw new ServiceException("Count of page of the book is less than 1");
        }
        if (!BookValidator.isValidPrice(price)) {
            throw new ServiceException("Price of the book is less than 0.1");
        }
        BookCreator creator = new BookCreator();
        Book book = creator.createBook(bookParameters);
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        boolean result;
        try {
            result = bookStorageDao.addBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public boolean removeBookFromStorageById(long id) throws ServiceException {
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        boolean result;
        try {
            result = bookStorageDao.removeBookById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public boolean removeBooksFromStorageByName(String name) throws ServiceException {
        if (!BookValidator.isValidNameLength(name)) {
            throw new ServiceException("Name length of the book is less than 1");
        }
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        boolean result;
        try {
            result = bookStorageDao.removeBooksByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public List<Book> selectAllBooksFromStorage() {
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        return bookStorageDao.selectAll();
    }

    public Book findBookByIdIntoStorage(long id) throws ServiceException {
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        Book foundBook;
        try {
            foundBook = bookStorageDao.findBookById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return foundBook;
    }

    public List<Book> findBooksByNameIntoStorage(String bookName) throws ServiceException {
        if (!BookValidator.isValidNameLength(bookName)) {
            throw new ServiceException("Name length of the book is less than 1");
        }
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        return bookStorageDao.findBooksByName(bookName);
    }

    public List<Book> findBooksByAuthorsIntoStorage(List<String> authors) throws ServiceException {
        if (!BookValidator.isValidAuthors(authors)) {
            throw new ServiceException("Authors of the book are null");
        }
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        return bookStorageDao.findBooksByAuthors(authors);
    }

    public List<Book> findBooksByCountOfPagesIntoStorage(int pages) throws ServiceException {
        if (!BookValidator.isValidCountOfPages(pages)) {
            throw new ServiceException("Count of page of the book is less than 1");
        }
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        return bookStorageDao.findBooksByCountOfPages(pages);
    }

    public List<Book> findBooksByPriceIntoStorage(double price) throws ServiceException {
        if (!BookValidator.isValidPrice(price)) {
            throw new ServiceException("Price of the book is less than 0.1");
        }
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        return bookStorageDao.findBooksByPrice(price);
    }

    public List<Book> sortBooksByTagIntoStorage(SortingTag tag, boolean ascend) {
        BookStorageDao bookStorageDao = BookStorageDaoImpl.getInstance();
        return bookStorageDao.sortBooksByTag(tag, ascend);
    }
}
