import logger.MyLogger;
import services.CollectingService;
import services.FilteringService;
import services.FrequentWordStore;
import services.LongestWordStore;
import services.ParsingService;
import services.PrintingService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Instantiates the services and calls the methods to complete the following task:
 * Parses a given url, removes given html elements, removes all tags and attributes, removes given words,
 * prints out the selected amount of most frequent words.
 * And soon it's going to be logging.
 */
public class ParseAndFilterOfURLs {
    public static void main(String[] args) {

        String url = "https://www.lipsum.com/";
        String[] skipTags = new String[]{"head", "style"};
        HashSet<String> skipWords = new HashSet<>(Arrays.asList("a", "in", "to", "if", "of", "the", "and"));
        int amountOfWords = 5;

        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }

        ParsingService parsingService = new ParsingService(url);
        FilteringService filteringService = new FilteringService(skipTags, skipWords);

        CollectingService counter = new CollectingService(parsingService, filteringService);
        LinkedList<String> wordsFiltered = counter.wordFilter();

        PrintingService mostFrequentWords = new PrintingService(new FrequentWordStore(), wordsFiltered, amountOfWords);
        PrintingService longestWords = new PrintingService(new LongestWordStore(), wordsFiltered, amountOfWords);

        mostFrequentWords.wordPrinter();
        longestWords.wordPrinter();
    }
}
