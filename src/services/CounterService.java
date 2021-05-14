package services;

import java.util.LinkedList;
import java.util.logging.Logger;


/**
 * Calls the methods in their specific order to complete the task
 */
public class CounterService {

    private final ParsingService parser;
    private final FilteringService filter;
    private final MappingService mapper;
    private final static Logger LOGGER = Logger.getGlobal();

    public CounterService(ParsingService parsingService, FilteringService filteringService, MappingService mappingService) {
        this.parser = parsingService;
        this.filter = filteringService;
        this.mapper = mappingService;
    }

    public void printUrlsMostCommonWords() {
        String parsedHtml = parser.parseHTML();
        parsedHtml = FilteringService.removeSpecialCharacters(parsedHtml);
        parsedHtml = filter.removeTagsContent(parsedHtml);
        parsedHtml = FilteringService.removeTagsAndAttributes(parsedHtml);
        LinkedList<String> parsedHtmlAsList = MappingService.makeAListFromString(parsedHtml);
//        region
//        filter.removeUnwantedWords(parsedHtmlAsList);
//        Map<String, Long> wordcount = MappingService.countTheWords(parsedHtmlAsList);
//        List<Map.Entry<String, Long>> mostCommonWords = mapper.getMostCommonWords(wordcount);
//        mapper.getMostCommonWords(wordcount);
//        LOGGER.log(Level.FINE, "tis done");
//        System.out.println("The " + mapper.getAmountOfMostFrequentWords() + " most frequent words: \n" + mostCommonWords + "\n");
//        endregion
//        PrintingService
        PrintingService printingService = new PrintingService(5);
        for (int i = 0; i < parsedHtmlAsList.size(); i++) {
            printingService.store(parsedHtmlAsList.get(i));
        }
        printingService.print();
        printingService.print(printingService.getAmountOfWords());

//        LengthComparingService
        LengthComparingService lengthComparingService = new LengthComparingService(5);
        for (int i = 0; i < parsedHtmlAsList.size(); i++) {
            lengthComparingService.store(parsedHtmlAsList.get(i));
        }
        lengthComparingService.print();
        lengthComparingService.print(lengthComparingService.getAmontOfWords());

    }
}
