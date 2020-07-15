package by.halatsevich.storage.controller.command;

import java.util.Map;

public interface Command {
    String RESULT_KEY = "result of command ->";

    Map<String, String> execute(Map<String, String> bookParameters);
}
