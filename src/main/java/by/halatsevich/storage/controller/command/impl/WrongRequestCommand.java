package by.halatsevich.storage.controller.command.impl;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.controller.command.CommandParameter;

import java.util.HashMap;
import java.util.Map;

public class WrongRequestCommand implements Command {

    @Override
    public Map<String, String> execute(Map<String, String> bookParameters) {
        Map<String, String> result = new HashMap<>();
        result.put(CommandParameter.RESULT_KEY, CommandParameter.WRONG_REQUEST);
        return result;
    }
}
