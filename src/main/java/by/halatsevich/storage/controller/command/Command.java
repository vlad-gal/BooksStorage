package by.halatsevich.storage.controller.command;

import java.util.Map;

public interface Command {
    String RESULT_KEY = "result";
    String EXCEPTION_VALUE = "exception";

    Map<String, String> execute(Map<String, String> bookParameters);
}
