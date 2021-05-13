package services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Calls the methods in their specific order to complete the task
 */
public class CounterService {

    private final ParsingService parser;
    private final FilteringService filter;
    private final MappingService mapper;

    public CounterService(ParsingService parsingService, FilteringService filteringService, MappingService mappingService){
        this.parser = parsingService;
        this.filter = filteringService;
        this.mapper = mappingService;
    }

    public void getUrlsMostCommonWords() {
        String parsedHtml = parser.parseHTML();
        parsedHtml = FilteringService.removeSpecialCharacters(parsedHtml);
        parsedHtml = filter.removeTagsContent(parsedHtml);
        parsedHtml = FilteringService.removeTagsAndAttributes(parsedHtml);
        LinkedList<String> parsedHtmlAsList = MappingService.makeAListFromString(parsedHtml);
        filter.removeUnwantedWords(parsedHtmlAsList);
        Map<String, Long> wordcount = MappingService.countTheWords(parsedHtmlAsList);
        List<Map.Entry<String, Long>> mostCommonWords = mapper.getMostCommonWords(wordcount);

        System.out.println("The " + mapper.getAmountOfMostFrequentWords() + " most frequent words: \n" + mostCommonWords);
    }
}
