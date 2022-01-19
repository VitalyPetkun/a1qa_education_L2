package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {
    private static final Logger logger = LogManager.getLogger(MyLogger.class);

    public static void logInfo(String message) {
        logger.info("INFO: " + message);
    }

    public static void logWarn(String message) {
        logger.warn("WARN: " + message);
    }

    public static void logError(String message) {
        logger.error("ERROR: " + message);
    }
}