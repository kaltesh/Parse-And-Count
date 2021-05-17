package services;

import java.util.LinkedList;


/**
 * Calls the methods in their specific order to parse and filter the given url
 */
public class CollectingService {
    private final ParsingService parser;
    private final FilteringService filter;

    public CollectingService(ParsingService parsingService, FilteringService filteringService) {
        this.parser = parsingService;
        this.filter = filteringService;
    }

    public LinkedList<String> wordFilter() {
        String parsedHtml = parser.parseHTML();
        parsedHtml = FilteringService.removeSpecialCharacters(parsedHtml);
        parsedHtml = filter.removeTagsContent(parsedHtml);
        parsedHtml = FilteringService.removeTagsAndAttributes(parsedHtml);
        LinkedList<String> parsedHtmlAsList = MappingService.makeAListFromString(parsedHtml);
        filter.removeUnwantedWords(parsedHtmlAsList);
        return parsedHtmlAsList;
    }
}
