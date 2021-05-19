package services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * creates a thread pool for the parsing and filtering of the words of given urls
 */
public class ParallelParse {
    private final static Logger LOGGER = Logger.getGlobal();

    /**
     * @param urls      to collect the words from
     * @param skipTags  elements of these tags to be removed from parsed words
     * @param skipWords set of words to be removed from parsed words
     * @return list of all words after filtering
     */
    public static ArrayList<String> getAllWords(String[] urls, String[] skipTags, HashSet<String> skipWords) {
        ExecutorService executor = Executors.newFixedThreadPool(urls.length);
        LOGGER.log(Level.FINE, "threadPool created");
        CopyOnWriteArrayList<LinkedList<String>> allWordsFromAllSitesSeparated = new CopyOnWriteArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            Thread worker = new CollectingService(new ParsingService(urls[i]), new FilteringService(skipTags, skipWords)) {
                /**
                 * {@inheritDoc}
                 * calls the wordFilter method from the CollectionService
                 * stores the words in a list,
                 * stores the list in another list
                 */
                @Override
                public void run() {
                    LinkedList<String> parsedHtmlAsList = wordFilter();
                    LOGGER.log(Level.FINE, "parsing and filtering words from one stored in a list");
                    allWordsFromAllSitesSeparated.add(parsedHtmlAsList);
                }
            };
            executor.execute(worker);
            LOGGER.log(Level.FINE, "thread starting");
        }
        executor.shutdown();
        LOGGER.log(Level.FINE, "executor service shutting down");
        while (!executor.isTerminated()) {
        }
        ArrayList<String> allWordsFromAllSites = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            allWordsFromAllSites.addAll(allWordsFromAllSitesSeparated.get(i));
            LOGGER.log(Level.FINEST, "getting all the words in one list");
        }
        return allWordsFromAllSites;
    }
}
