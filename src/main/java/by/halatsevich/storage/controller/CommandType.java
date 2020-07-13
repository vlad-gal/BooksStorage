package by.halatsevich.storage.controller;

import by.halatsevich.storage.controller.command.Command;
import by.halatsevich.storage.controller.command.impl.*;

public enum CommandType {
    ADD_BOOK(new AddBookCommand()),
    REMOVE_BOOK(new RemoveBookCommand()),
    SORT_BY_TAG(new SortByTagCommand()),
    FIND_BY_ID(new FindByIdCommand()),
    FIND_BY_NAME(new FindByNameCommand()),
    FIND_BY_AUTHORS(new FindByAuthorsCommand()),
    FIND_BY_PAGES(new FindByPagesCommand()),
    FIND_BY_PRICE(new FindByPriceCommand()),
    FIND_ALL(new SelectAllCommand()),
    WRONG_REQUEST(new WrongRequestCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
