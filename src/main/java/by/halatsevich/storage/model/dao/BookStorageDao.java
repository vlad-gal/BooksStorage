package by.halatsevich.storage.model.dao;

import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.exception.DaoException;
import by.halatsevich.storage.model.type.SortingTag;

import java.util.List;

public interface BookStorageDao {
    boolean addBook(Book book) throws DaoException;

    boolean removeBook(Book book) throws DaoException;

    List<Book> selectAll();

    Book findBookById(long id) throws DaoException;

    List<Book> findBooksByName(String name);

    List<Book> findBooksByAuthors(List<String> authors);

    List<Book> findBooksByCountOfPages(int pages);

    List<Book> findBooksByPrice(double price);

    List<Book> sortBooksByTag(SortingTag tag, boolean ascend);

}
