package by.halatsevich.storage.model.validator;

import java.util.List;

public class BookValidator {
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MIN_COUNT_AUTHORS = 1;
    private static final int MIN_PAGES = 1;
    private static final double MIN_PRICE = 0.1;

    public static boolean isValidNameLength(String name) {
        return (name.length() >= MIN_NAME_LENGTH);
    }

    public static boolean isValidCountOfPages(int pages) {
        return (pages >= MIN_PAGES);
    }

    public static boolean isValidPrice(double price) {
        return (price >= MIN_PRICE);
    }

    public static boolean isValidAuthors(List<String> authors) {
        if (authors == null) {
            return false;
        }
        for (String author : authors) {
            if (author == null || author.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
