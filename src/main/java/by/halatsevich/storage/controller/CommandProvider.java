package by.halatsevich.storage.controller;

import by.halatsevich.storage.controller.command.Command;

public class CommandProvider {

    public Command defineCommand(String commandName) {
        if (commandName == null || commandName.isEmpty()) {
            return CommandType.WRONG_REQUEST.getCommand();
        }
        Command command;
        try {
            command = CommandType.valueOf(commandName.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            return CommandType.WRONG_REQUEST.getCommand();
        }
        return command;
    }
}
