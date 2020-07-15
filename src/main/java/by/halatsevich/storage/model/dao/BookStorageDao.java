package by.halatsevich.storage.model.dao;

import by.halatsevich.storage.exception.DaoException;
import by.halatsevich.storage.model.comparator.type.SortingTag;
import by.halatsevich.storage.model.entity.Book;

import java.util.List;

public interface BookStorageDao {
    boolean addBook(Book book) throws DaoException;

    boolean removeBookById(long id) throws DaoException;

    boolean removeBooksByName(String name) throws DaoException;

    List<Book> selectAll();

    Book findBookById(long id) throws DaoException;

    List<Book> findBooksByName(String name);

    List<Book> findBooksByAuthors(List<String> authors);

    List<Book> findBooksByCountOfPages(int pages);

    List<Book> findBooksByPrice(double price);

    List<Book> sortBooksByTag(SortingTag tag, boolean ascend);
}
