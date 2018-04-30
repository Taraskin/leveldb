package info.taraskin.java.test.common;

import info.taraskin.java.test.db.DBInstance;
import info.taraskin.java.test.db.LevelDBInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TestableDBImpl implements Testable {

    private static final Logger logger = LoggerFactory.getLogger(TestableDBImpl.class);
    private static final long NANO = 1000000;

    private List<DBInstance> dbInstances = new ArrayList<>();
    private long count = 1000000;

    public void run() throws Exception {
        init();
        for (DBInstance dbInstance : dbInstances) {
            long time = System.nanoTime();
            dbInstance.createDb();
            logger.debug("{} - CREATE: {} ns", dbInstance.getTitle(), (System.nanoTime() - time) / NANO);
            time = System.nanoTime();
            dbInstance.fillDb(count);
            logger.debug("{}  - FILL: {} ns", dbInstance.getTitle(), (System.nanoTime() - time) / NANO);
            time = System.nanoTime();
            dbInstance.readDb();
            logger.debug("{}  - READ: {} ns", dbInstance.getTitle(), (System.nanoTime() - time) / NANO);
            dbInstance.closeDb();
            logger.debug("{}  - CLOSE: {} ns", dbInstance.getTitle(), (System.nanoTime() - time) / NANO);
        }
    }

    private void init() {
        dbInstances.clear();
        dbInstances.add(new LevelDBInstance());
    }
}
