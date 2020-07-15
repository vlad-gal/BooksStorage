package by.halatsevich.storage.model.util;

public class IdGenerator {
    private static final long MAX_ID = 1000;
    private static long id = 1;

    private IdGenerator() {
    }

    public static long generateId() {
        if (id >= MAX_ID) {
            id = 1;
        }
        return id++;
    }

    public static void setId(long id) {
        IdGenerator.id = id;
    }
}