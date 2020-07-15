package test.halatsevich.storage.controller;

import by.halatsevich.storage.controller.Controller;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ControllerTest {
    Controller controller;
    Map<String, String> bookParameters = new HashMap<>();

    @BeforeMethod
    public void setUp() throws Exception {
        controller = Controller.getInstance();
        bookParameters.put("name", "Планирование в аудите");
        bookParameters.put("authors", "С.М. Бычкова, А.В. Газорян");
        bookParameters.put("price", "594.0");
        bookParameters.put("pages", "293");
        bookParameters.put("sorting tag", "Name");
        bookParameters.put("ascend", "true");
    }

    @Test
    public void testExecuteRequestSuccess() {
        Map<String, String> actual = controller.executeRequest("AdD_BooK", bookParameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("result of command ->", "true");
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteRequestFailure() {
        Map<String, String> actual = controller.executeRequest("", bookParameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("result of command ->", "true");
        assertNotEquals(actual, expected);
    }
}