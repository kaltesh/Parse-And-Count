import logger.MyLogger;
import services.CounterService;
import services.FilteringService;
import services.MappingService;
import services.ParsingService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Instantiates the services and calls the methods to complete the following task:
 * UseLogger tester = new UseLogger();
 * try {
 * MyLogger.setup();
 * } catch (IOException e) {
 * e.printStackTrace();
 * throw new RuntimeException("Problems with creating the log files");
 * }
 * tester.doSomeThingAndLog();
 * }Parses a given url, removes given words, removes given html elements, removes all tags and attributes,
 * prints out the selected amount of most frequent words.
 * And soon it's going to be logging.
 */
public class ParseAndFilterOfURLs {
    private final static Logger LOGGER = Logger.getGlobal();

    public static void main(String[] args) {
        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }

        String url = "https://www.lipsum.com/";
        String[] skipTags = new String[]{"head", "style"};
        HashSet<String> skipWords = new HashSet<>(Arrays.asList("to", "if", "of"));
        int amountOfWords = 10;

        ParsingService parsingService = new ParsingService(url);
        FilteringService filteringService = new FilteringService(skipTags, skipWords);
        MappingService mappingService = new MappingService(amountOfWords);

        CounterService counter = new CounterService(parsingService, filteringService, mappingService);

        counter.printUrlsMostCommonWords();
    }
}
