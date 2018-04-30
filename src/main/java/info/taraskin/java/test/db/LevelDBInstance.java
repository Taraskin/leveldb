package info.taraskin.java.test.db;

import org.apache.commons.io.FileUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;

import static org.iq80.leveldb.impl.Iq80DBFactory.bytes;
import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

public class LevelDBInstance implements DBInstance {

    private static final Logger logger = LoggerFactory.getLogger(LevelDBInstance.class);

    private static final String DB_STORE = "levelDBStore";

    private DB levelDBStore;

    @Override
    public String getTitle() {
        return "LevelDB";
    }

    @Override
    public void createDb() {
        Options options = new Options();
        try {
            levelDBStore = factory.open(new File(DB_STORE), options);
        } catch (IOException e) {
            logger.error("Can't create {}", DB_STORE);
        }
    }

    @Override
    public void fillDb(long count) {
        for (long i = 0; i < count; i++) {
            levelDBStore.put(bytes("testKey " + i), bytes("testValue " + i));
        }
    }

    @Override
    public void readDb() {
        levelDBStore.get(bytes("testKey"));
    }

    @Override
    public void closeDb() throws Exception {
        levelDBStore.close();
        File file = new File(DB_STORE);
//        FileUtils.deleteDirectory(file);
        if(!FileSystemUtils.deleteRecursively(file)) {
            System.out.println("Problem occurs when deleting the directory : " + DB_STORE);
        }
    }
}
