package by.halatsevich.storage.model.dao.impl;

import by.halatsevich.storage.exception.DaoException;
import by.halatsevich.storage.model.comparator.BookComparator;
import by.halatsevich.storage.model.comparator.type.SortingTag;
import by.halatsevich.storage.model.dao.BookStorageDao;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.entity.BookStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookStorageDaoImpl implements BookStorageDao {
    private static BookStorageDaoImpl instance;

    private BookStorageDaoImpl() {
    }

    public static BookStorageDaoImpl getInstance() {
        if (instance == null) {
            return new BookStorageDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean addBook(Book book) throws DaoException {
        BookStorage bookStorage = BookStorage.getInstance();
        for (int i = 0; i < bookStorage.size(); i++) {
            if (bookStorage.getBook(i).equals(book)) {
                throw new DaoException("Book is present in storage");
            }
        }
        return bookStorage.addBook(book);
    }

    @Override
    public boolean removeBookById(long id) throws DaoException {
        BookStorage bookStorage = BookStorage.getInstance();
        for (int i = 0; i < bookStorage.size(); i++) {
            Book book = bookStorage.getBook(i);
            if (book.getBookId() == id) {
                return bookStorage.removeBook(book);
            }
        }
        throw new DaoException("Book was not found in a storage");
    }

    @Override
    public boolean removeBooksByName(String name) throws DaoException {
        BookStorage bookStorage = BookStorage.getInstance();
        List<Book> removedBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            Book book = bookStorage.getBook(i);
            if (book.getName().equals(name)) {
                removedBooks.add(book);
            }
        }
        if (removedBooks.isEmpty()) {
            throw new DaoException("Book was not found in a storage");
        }
        for (Book book : removedBooks) {
            bookStorage.removeBook(book);
        }
        return true;
    }

    @Override
    public List<Book> selectAll() {
        BookStorage bookStorage = BookStorage.getInstance();
        List<Book> allBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            allBooks.add(bookStorage.getBook(i));
        }
        return allBooks;
    }

    @Override
    public Book findBookById(long id) throws DaoException {
        BookStorage bookStorage = BookStorage.getInstance();
        for (int i = 0; i < bookStorage.size(); i++) {
            if (bookStorage.getBook(i).getBookId() == id) {
                return bookStorage.getBook(i);
            }
        }
        throw new DaoException("Book with this id was not found");
    }

    @Override
    public List<Book> findBooksByName(String name) {
        BookStorage bookStorage = BookStorage.getInstance();
        List<Book> foundBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            String bookName = bookStorage.getBook(i).getName();
            if (bookName.equals(name)) {
                foundBooks.add(bookStorage.getBook(i));
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> findBooksByAuthors(List<String> authors) {
        BookStorage bookStorage = BookStorage.getInstance();
        List<Book> foundBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            List<String> booksAuthors = bookStorage.getBook(i).getAuthors();
            Book book = bookStorage.getBook(i);
            for (String booksAuthor : booksAuthors) {
                for (String author : authors) {
                    if (foundBooks.contains(book)) {
                        continue;
                    }
                    if (booksAuthor.equals(author)) {
                        foundBooks.add(bookStorage.getBook(i));
                    }
                }
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> findBooksByCountOfPages(int pages) {
        BookStorage bookStorage = BookStorage.getInstance();
        List<Book> foundBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            int bookPages = bookStorage.getBook(i).getPages();
            if (bookPages == pages) {
                foundBooks.add(bookStorage.getBook(i));
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> findBooksByPrice(double price) {
        BookStorage bookStorage = BookStorage.getInstance();
        List<Book> foundBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            double bookPages = bookStorage.getBook(i).getPrice();
            if (bookPages == price) {
                foundBooks.add(bookStorage.getBook(i));
            }
        }
        return foundBooks;
    }

    @Override
    public List<Book> sortBooksByTag(SortingTag tag, boolean ascend) {
        BookStorage bookStorage = BookStorage.getInstance();
        Comparator<Book> bookComparator = new BookComparator(tag, ascend);
        List<Book> sortedBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            sortedBooks.add(bookStorage.getBook(i));
        }
        sortedBooks.sort(bookComparator);
        return sortedBooks;
    }
}
