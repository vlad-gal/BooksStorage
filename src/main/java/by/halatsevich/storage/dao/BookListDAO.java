package by.halatsevich.storage.dao;

import by.halatsevich.storage.entity.Book;
import by.halatsevich.storage.exception.ProjectException;

import java.util.Comparator;
import java.util.List;

public interface BookListDAO {
    boolean addBook(Book book);

    boolean removeBook(Book book) throws ProjectException;

    Book findBookById(long id) throws ProjectException;

    List<Book> findBooksByName(String name);

    List<Book> findBooksByAuthors(List<String> authors);

    List<Book> findBooksByCountOfPages(int pages);

    List<Book> findBooksByPrice(double price);

    List<Book> sortBooksByTag(List<Book> books, Comparator<Book> comparator);

}
