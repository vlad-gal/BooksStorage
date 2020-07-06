package by.halatsevich.storage.generator;

public class IdGenerator {
    private static final long MAX_ID = 1000;
    private static long id;

    private IdGenerator(){
    }

    public static long generateId(){
        if (id >= MAX_ID){
            id = 0;
        }
        return id++;
    }

}