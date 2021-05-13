package services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CounterService {
    private final String url;
    private final String[] skipTags;
    private final List<String> skipWords;
    private final int numberOfWords;

    public CounterService(String url, String[] skipTags, List<String> skipWords, int numberOfWords) {
        this.url = url;
        this.skipTags = skipTags;
        this.skipWords = skipWords;
        this.numberOfWords = numberOfWords;
    }

    public void getUrlsMostCommonWords() {
        ParsingService parser = new ParsingService(url);
        FilteringService filter = new FilteringService(skipTags, skipWords);
        MappingService mapper = new MappingService(numberOfWords);

        String parsedHtml = parser.parseHTML();
        parsedHtml = filter.removeSpecialCharacters(parsedHtml);
        parsedHtml = filter.removeTagsContent(parsedHtml);
        parsedHtml = filter.removeTagsAndAttributes(parsedHtml);
        LinkedList<String> parsedHtmlAsList = mapper.makeAListFromString(parsedHtml);
        filter.removeUnwantedWords(parsedHtmlAsList);
        Map<String, Long> wordcount = mapper.countTheWords(parsedHtmlAsList);
        List<Map.Entry<String, Long>> mostCommonWords = mapper.getMostCommonWords(wordcount);

        System.out.println("The " + numberOfWords + " most frequent words: \n" + mostCommonWords);
    }
}
