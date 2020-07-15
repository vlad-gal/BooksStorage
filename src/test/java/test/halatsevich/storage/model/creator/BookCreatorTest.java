package test.halatsevich.storage.model.creator;

import by.halatsevich.storage.model.creator.BookCreator;
import by.halatsevich.storage.model.entity.Book;
import by.halatsevich.storage.model.util.IdGenerator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class BookCreatorTest {
    BookCreator creator;
    Map<String, String> bookParameters = new HashMap<>();

    @BeforeClass
    public void setUp() {
        creator = new BookCreator();
        bookParameters.put("name", "Планирование в аудите");
        bookParameters.put("authors", "С.М. Бычкова, А.В. Газорян");
        bookParameters.put("price", "594.0");
        bookParameters.put("pages", "293");
        bookParameters.put("sorting tag", "Name");
        bookParameters.put("ascend", "true");
        IdGenerator.setId(1);
    }

    @Test
    public void testCreateBookSuccess() {
        Book actual = creator.createBook(bookParameters);
        Book expected = new Book("Планирование в аудите",
                Arrays.asList("С.М. Бычкова", "А.В. Газорян"), 293, 594.0);
        assertEquals(actual, expected);
    }

    @Test
    public void testCreateBookFailure() {
        Book actual = creator.createBook(bookParameters);
        Book expected = new Book("Планирование в аудите",
                Arrays.asList("С.М. Бычкова", "А.В. Газорян"), 44, 594.0);
        assertNotEquals(actual, expected);
    }
}