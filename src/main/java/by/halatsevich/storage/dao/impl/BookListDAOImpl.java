package by.halatsevich.storage.dao.impl;

import by.halatsevich.storage.dao.BookListDAO;
import by.halatsevich.storage.entity.Book;
import by.halatsevich.storage.entity.BookStorage;
import by.halatsevich.storage.exception.ProjectException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookListDAOImpl implements BookListDAO {
    private BookStorage bookStorage = BookStorage.getInstance();

    public boolean addBook(Book book) {
        for (int i = 0; i < bookStorage.size(); i++) {
            if (bookStorage.getBook(i).equals(book)) {
                return false;
            }
        }
        return bookStorage.addBook(book);
    }

    public boolean removeBook(Book book) throws ProjectException {
        if (bookStorage.removeBook(book)) {
            return true;
        } else {
            throw new ProjectException("Book was not found in a storage");
        }
    }

    public Book findBookById(long id) throws ProjectException {
        if (id >= bookStorage.size()) {
            throw new ProjectException("Book with this id was not found");
        }
        return bookStorage.getBook((int) id);
    }

    public List<Book> findBooksByName(String name) {
        List<Book> foundBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            String bookName = bookStorage.getBook(i).getName();
            if (bookName.equals(name)) {
                foundBooks.add(bookStorage.getBook(i));
            }
        }
        return foundBooks;
    }

    public List<Book> findBooksByAuthors(List<String> authors) {
        List<Book> foundBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            List<String> booksAuthors = bookStorage.getBook(i).getAuthors();
            Book book = bookStorage.getBook(i);
            for (int j = 0; j < booksAuthors.size(); j++) {
                for (int k = 0; k < authors.size(); k++) {
                    if (foundBooks.contains(book)) {
                        continue;
                    }
                    if (booksAuthors.get(j).equals(authors.get(k))) {
                        foundBooks.add(bookStorage.getBook(i));
                    }
                }
            }
        }
        return foundBooks;
    }

    public List<Book> findBooksByCountOfPages(int pages) {
        List<Book> foundBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            int bookPages = bookStorage.getBook(i).getPages();
            if (bookPages == pages) {
                foundBooks.add(bookStorage.getBook(i));
            }
        }
        return foundBooks;
    }

    public List<Book> findBooksByPrice(double price) {
        List<Book> foundBooks = new ArrayList<>();
        for (int i = 0; i < bookStorage.size(); i++) {
            double bookPages = bookStorage.getBook(i).getPrice();
            if (bookPages == price) {
                foundBooks.add(bookStorage.getBook(i));
            }
        }
        return foundBooks;
    }

    public List<Book> sortBooksByTag(List<Book> books, Comparator<Book> comparator) {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(comparator);
        return sortedBooks;
    }
}
