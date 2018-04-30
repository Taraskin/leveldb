package info.taraskin.java.test;

import info.taraskin.java.test.common.Testable;
import info.taraskin.java.test.common.TestableDBImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClass {

    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) throws Exception {
        logger.info("Starting...");
        Testable testable = new TestableDBImpl();
        testable.run();
    }
}
