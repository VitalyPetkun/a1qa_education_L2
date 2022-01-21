package utils;

import org.apache.logging.log4j.LogManager;

public class Logger {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    public static void logInfo(String message) {
        logger.info("INFO: " + message);
    }

    public static void logStep(String message) {
        logger.warn("STEP: " + message);
    }

    public static void logError(String message) {
        logger.error("ERROR: " + message);
    }
}