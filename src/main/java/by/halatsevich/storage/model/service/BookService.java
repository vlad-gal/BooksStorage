package by.halatsevich.storage.model.service;

import by.halatsevich.storage.exception.ServiceException;
import by.halatsevich.storage.model.comparator.SortingTag;
import by.halatsevich.storage.model.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    boolean addBookIntoStorage(Map<String, String> bookParameters) throws ServiceException;

    boolean removeBookFromStorageById(long id) throws ServiceException;

    boolean removeBooksFromStorageByName(String name) throws ServiceException;

    List<Book> selectAllBooksFromStorage();

    Book findBookByIdIntoStorage(long id) throws ServiceException;

    List<Book> findBooksByNameIntoStorage(String bookName) throws ServiceException;

    List<Book> findBooksByAuthorsIntoStorage(List<String> authors) throws ServiceException;

    List<Book> findBooksByCountOfPagesIntoStorage(int pages) throws ServiceException;

    List<Book> findBooksByPriceIntoStorage(double price) throws ServiceException;

    List<Book> sortBooksByTagIntoStorage(SortingTag tag, boolean ascend);
}
