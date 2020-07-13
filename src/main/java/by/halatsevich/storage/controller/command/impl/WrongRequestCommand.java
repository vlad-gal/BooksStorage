package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;

import java.util.HashMap;
import java.util.Map;

public class WrongRequestCommand implements Command {
    private static final String WRONG_REQUEST = "wrong request command";
    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        Map<String,String> result = new HashMap<>();
        result.put(RESULT_KEY,WRONG_REQUEST);
        return result;
    }
}
