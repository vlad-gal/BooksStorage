package test.halatsevich.storage.model.validator;

import by.halatsevich.storage.model.validator.BookValidator;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BookValidatorTest {

    @Test
    public void testIsValidNameLengthSuccess() {
        boolean condition = BookValidator.isValidNameLength("hello");
        assertTrue(condition);
    }

    @Test
    public void testIsValidNameLengthFailure() {
        boolean condition = BookValidator.isValidNameLength("");
        assertFalse(condition);
    }

    @Test
    public void testIsValidCountOfPagesSuccess() {
        boolean condition = BookValidator.isValidCountOfPages(22);
        assertTrue(condition);
    }

    @Test
    public void testIsValidCountOfPagesFailure() {
        boolean condition = BookValidator.isValidCountOfPages(-10);
        assertFalse(condition);
    }

    @Test
    public void testIsValidPriceSuccess() {
        boolean condition = BookValidator.isValidPrice(783.5);
        assertTrue(condition);
    }

    @Test
    public void testIsValidPriceFailure() {
        boolean condition = BookValidator.isValidPrice(0);
        assertFalse(condition);
    }

    @Test
    public void testIsValidAuthorsSuccess() {
        boolean condition = BookValidator.isValidAuthors(Arrays.asList("1", "2", "3"));
        assertTrue(condition);
    }

    @Test
    public void testIsValidAuthorsFailure() {
        boolean condition = BookValidator.isValidAuthors(Arrays.asList("1", null, "3"));
        assertFalse(condition);
    }
}