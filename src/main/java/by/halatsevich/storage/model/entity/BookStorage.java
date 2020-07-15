package by.halatsevich.storage.model.entity;

import by.halatsevich.storage.model.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class BookStorage {
    private static BookStorage instance;
    private static final int MAX_CAPACITY = 500;
    private List<Book> storage;

    private BookStorage() {
        storage = new ArrayList<>();
    }

    public static BookStorage getInstance() {
        if (instance == null) {
            instance = new BookStorage();
        }
        return instance;
    }

    public boolean addBook(Book book) {
        if (storage.size() >= MAX_CAPACITY) {
            return false;
        }
        book.setBookId(IdGenerator.generateId());
        return storage.add(book);
    }

    public boolean removeBook(Book book) {
        return storage.remove(book);
    }

    public Book getBook(int index) {
        return storage.get(index);
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
