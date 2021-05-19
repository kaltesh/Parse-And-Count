import logger.MyLogger;
import services.FrequentWordStore;
import services.LongestWordStore;
import services.ParallelParse;
import services.PrintingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Instantiates the services and calls the methods to complete the following task:
 * Parses a given url, removes given html elements, removes all tags and attributes, removes given words,
 * prints out the selected amount of most frequent words.
 * And soon it's going to be logging.
 */
public class ParseAndFilterOfURLs {
    public static void main(String[] args) {
        String[] urls = new String[]{"https://justinjackson.ca/words.html", "https://justinjacksonsdf.ca/words.htmla",
                "https://justinjackson.ca/words.html", "https://justinjackson.ca/words.html", "https://www.lipsum.com/",
                "https://www.lipsum.com/", "https://www.lipsum.com/"};
        String[] skipTags = new String[]{"head", "style"};
        HashSet<String> skipWords = new HashSet<>(Arrays.asList("a", "in", "to", "if", "of", "the", "and"));
        int amountOfWords = 5;

        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }

        ArrayList<String> wordsFilteredFromAllURLs = ParallelParse.getAllWords(urls, skipTags, skipWords);

        PrintingService mostFrequentWords = new PrintingService(new FrequentWordStore(), wordsFilteredFromAllURLs, amountOfWords);
        PrintingService longestWords = new PrintingService(new LongestWordStore(), wordsFilteredFromAllURLs, amountOfWords);

        mostFrequentWords.wordPrinter();
        longestWords.wordPrinter();
    }
}
