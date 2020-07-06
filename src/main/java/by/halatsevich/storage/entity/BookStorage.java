package by.halatsevich.storage.entity;

import java.util.ArrayList;
import java.util.List;

public class BookStorage {
    private static BookStorage instance;
    private List<Book> storage = new ArrayList<>();

    private BookStorage() {
    }

    public static BookStorage getInstance() {
        if (instance == null) {
            instance = new BookStorage();
        }
        return instance;
    }

    public boolean addBook(Book book) {
        return storage.add(book);
    }

    public boolean removeBook(Object o) {
        return storage.remove(o);
    }

    public Book getBook(int index) {
        return storage.get(index);
    }

    public Book setBook(int index, Book element) {
        return storage.set(index, element);
    }

    public int size() {
        return storage.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookStorage{");
        sb.append("storage=").append(storage);
        sb.append('}');
        return sb.toString();
    }
}
