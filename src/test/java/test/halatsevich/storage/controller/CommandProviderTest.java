package test.halatsevich.storage.controller;

import by.halatsevich.storage.controller.CommandProvider;
import by.halatsevich.storage.controller.CommandType;
import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.model.util.IdGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CommandProviderTest {
    CommandProvider provider;

    @BeforeMethod
    public void setUp() {
        IdGenerator.setId(1);
        provider = new CommandProvider();
    }

    @Test
    public void testDefineCommandSuccess() {
        Command actual = provider.defineCommand("Add_book");
        Command expected = CommandType.ADD_BOOK.getCommand();
        assertEquals(actual, expected);
    }

    @Test
    public void testDefineCommandEmpty() {
        Command actual = provider.defineCommand(null);
        Command expected = CommandType.ADD_BOOK.getCommand();
        assertNotEquals(actual, expected);
    }

    @Test
    public void testDefineCommandWrongRequest() {
        Command actual = provider.defineCommand("Hello");
        Command expected = CommandType.WRONG_REQUEST.getCommand();
        assertEquals(actual, expected);
    }
}