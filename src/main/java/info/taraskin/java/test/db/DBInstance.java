package info.taraskin.java.test.db;

public interface DBInstance {
    String getTitle();
    void createDb();
    void fillDb(long count);
    void readDb();
    void closeDb() throws Exception;
}
