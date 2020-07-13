package by.halatsevich.storage.controller;

import by.halatsevich.storage.controller.command.Command;

import java.util.Map;

public class Controller {
    private final CommandProvider provider = new CommandProvider();

    public Map<String, String> executeRequest(String command, Map<String, String> bookParameters) {
        Command defineCommand = provider.defineCommand(command);
        return defineCommand.execute(bookParameters);
    }
}
