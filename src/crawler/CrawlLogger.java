/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Logger;

import java.util.logging.SimpleFormatter;

/**
 *
 * @author rahma
 */

public class CrawlLogger {

    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static public void setup() throws IOException {
        // get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        try {
            // suppress the logging output to the console
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            if (handlers[0] instanceof ConsoleHandler) {
                rootLogger.removeHandler(handlers[0]);
            }

            logger.setLevel(Level.INFO);
            fileTxt = new FileHandler("LogCrawler.txt");

            // create a TXT formatter
            formatterTxt = new SimpleFormatter();
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);
        } catch (IOException e) {
        }
    }
}
