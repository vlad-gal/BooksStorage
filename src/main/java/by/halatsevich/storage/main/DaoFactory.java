//package by.halatsevich.storage.main;
//
//import by.halatsevich.storage.model.dao.BookStorageDao;
//import by.halatsevich.storage.model.dao.impl.BookStorageDaoImpl;
//
//public class DaoFactory {
//    private static DaoFactory instance;
//    private BookStorageDao storageDaoImpl;
//
//    private DaoFactory() {
//    }
//
//    public static DaoFactory getInstance() {
//        if (instance == null) {
//            instance = new DaoFactory();
//        }
//        return instance;
//    }
//
//    public BookStorageDao getBookStorageDao() {
//        if (storageDaoImpl == null) {
//            storageDaoImpl = new BookStorageDaoImpl();
//        }
//        return storageDaoImpl;
//    }
//}
