package logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * creates a text and a html file for the logs
 */
public class MyLogger {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    static private FileHandler fileHTML;
    static private Formatter formatterHTML;

    /**
     * Creates the text and the html files for the logs, adds the header and the tail
     */
    static public void setup() throws IOException { // TODO ML: could you please print the log to the standard output?

        Logger logger = Logger.getGlobal();

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.INFO);
        fileTxt = new FileHandler("Logging.txt");
        fileHTML = new FileHandler("Logging.html");

        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        formatterHTML = new MyHtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }
}