package by.halatsevich.storage.model.service;

import by.halatsevich.storage.model.creator.BookCreator;
import by.halatsevich.storage.model.dao.BookStorageDao;
import by.halatsevich.storage.model.dao.DaoFactory;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.exception.DaoException;
import by.halatsevich.storage.model.exception.ServiceException;
import by.halatsevich.storage.model.type.SortingTag;
import by.halatsevich.storage.model.validator.BookValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BookService {
    private static final String BOOK_NAME = "name";
    private static final String BOOK_AUTHORS = "authors";
    private static final String BOOK_PAGES = "pages";
    private static final String BOOK_PRICE = "price";
    private static final String REGEX_DELIMITER = ", ";

    public boolean addBookIntoStorage(Map<String, String> bookParameters) throws ServiceException {
        if (bookParameters == null) {
            throw new ServiceException("Book's parameters are null");
        }
        String name = bookParameters.get(BOOK_NAME);
        List<String> authors = Arrays.asList(bookParameters.get(BOOK_AUTHORS).split(REGEX_DELIMITER));
        int pages = Integer.parseInt(bookParameters.get(BOOK_PAGES));
        double price = Double.parseDouble(bookParameters.get(BOOK_PRICE));
        if (!BookValidator.isValidNameLength(name)) {
            throw new ServiceException("Book's name length is less than 1");
        }
        if (!BookValidator.isValidAuthors(authors)) {
            throw new ServiceException("Book's authors are null");
        }
        if (!BookValidator.isValidCountOfPages(pages)) {
            throw new ServiceException("Book's count of page is less than 1");
        }
        if (!BookValidator.isValidPrice(price)) {
            throw new ServiceException("Book's price is less than 0.1");
        }
        BookCreator creator = new BookCreator();
        Book book = creator.createBook(bookParameters);
        DaoFactory factory = DaoFactory.getInstance();
        BookStorageDao bookStorageDao = factory.getBookStorageDao();
        boolean result;
        try {
            result = bookStorageDao.addBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return result;
    }

    public boolean removeBookFromStorage(Map<String, String> bookParameters) throws ServiceException {
        if (bookParameters == null) {
            throw new ServiceException("Book's parameters are null");
        }
        String name = bookParameters.get(BOOK_NAME);
        List<String> authors = Arrays.asList(bookParameters.get(BOOK_AUTHORS).split(REGEX_DELIMITER));
        int pages = Integer.parseInt(bookParameters.get(BOOK_PAGES));
        double price = Double.parseDouble(bookParameters.get(BOOK_PRICE));
        if (!BookValidator.isValidNameLength(name)) {
            throw new ServiceException("Book's name length is less than 1");
        }
        if (!BookValidator.isValidAuthors(authors)) {
            throw new ServiceException("Book's authors are null");
        }
        if (!BookValidator.isValidCountOfPages(pages)) {
            throw new ServiceException("Book's count of page is less than 1");
        }
        if (!BookValidator.isValidPrice(price)) {
            throw new ServiceException("Book's price is less than 0.1");
        }
        BookCreator creator = new BookCreator();
        Book book = creator.createBook(bookParameters);
        DaoFactory factory = DaoFactory.getInstance();
        BookStorageDao bookStorageDao = factory.getBookStorageDao();
        boolean result;
        try {
            result = bookStorageDao.removeBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return result;
    }

    public List<Book> selectAllBooksFromStorage() {
        DaoFactory factory = DaoFactory.getInstance();
        BookStorageDao bookStorageDao = factory.getBookStorageDao();
        return bookStorageDao.selectAll();
    }

    public Book findBookByIdIntoStorage(long id) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        BookStorageDao bookStorageDao = factory.getBookStorageDao();
        Book foundBook;
        try {
            foundBook = bookStorageDao.findBookById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return foundBook;
    }

    public List<Book> findBooksByNameIntoStorage(String bookName) throws ServiceException {
        if (!BookValidator.isValidNameLength(bookName)) {
            throw new ServiceException("Book's length name is less than 1");
        }
        DaoFactory factory = DaoFactory.getInstance();
        BookStorageDao bookStorageDao = factory.getBookStorageDao();
        return bookStorageDao.findBooksByName(bookName);
    }

    public List<Book> findBooksByAuthorsIntoStorage(List<String> authors) throws ServiceException {
        if (!BookValidator.isValidAuthors(authors)) {
            throw new ServiceException("Book's authors are null");
        }
        DaoFactory factory = DaoFactory.getInstance();
        BookStorageDao bookStorageDao = factory.getBookStorageDao();
        return bookStorageDao.findBooksByAuthors(authors);
    }

    public List<Book> findBooksByCountOfPagesIntoStorage(int pages) throws ServiceException {
        if (!BookValidator.isValidCountOfPages(pages)) {
            throw new ServiceException("Book's page count is less than 1");
        }
        DaoFactory factory = DaoFactory.getInstance();
        BookStorageDao bookStorageDao = factory.getBookStorageDao();
        return bookStorageDao.findBooksByCountOfPages(pages);
    }

    public List<Book> findBooksByPriceIntoStorage(double price) throws ServiceException {
        if (!BookValidator.isValidPrice(price)) {
            throw new ServiceException("Book's price is less than 0.1");
        }
        DaoFactory factory = DaoFactory.getInstance();
        BookStorageDao bookStorageDao = factory.getBookStorageDao();
        return bookStorageDao.findBooksByPrice(price);
    }

    public List<Book> sortBooksByTagIntoStorage(SortingTag tag, boolean ascend) {
        DaoFactory factory = DaoFactory.getInstance();
        BookStorageDao bookStorageDao = factory.getBookStorageDao();
        return bookStorageDao.sortBooksByTag(tag, ascend);
    }
}
