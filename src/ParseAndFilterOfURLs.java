import services.FilteringService;
import services.MappingService;
import services.ParsingService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParseAndFilterOfURLs {
    public static void main(String[] args) {

        ParsingService parser = new ParsingService();
        String parsedHtml = parser.parseHTML("https://google.com/");
        System.out.println("URL parsed: \n" + parsedHtml + "\n");

        FilteringService filter = new FilteringService();
        parsedHtml = filter.removeSpecialCharacters(parsedHtml);
        System.out.println("Special characters trimmed from words : \n" + parsedHtml + "\n");

        String[] skipTags = new String[]{"head", "style"};
        parsedHtml = filter.removeTagsContent(parsedHtml, skipTags);
//        System.out.println("Head and style tags' content removed: \n" + parsedHtml + "\n");

        parsedHtml = filter.removeTagsAndAttributes(parsedHtml);
        System.out.println("All tags removed: \n" + parsedHtml + "\n");

        MappingService mapper = new MappingService();
        LinkedList<String> parsedHtmlAsLinkedList = mapper.makeAListFromString(parsedHtml);
        System.out.println("Sting converted into a list, :\n" + parsedHtmlAsLinkedList + "\n");

        List<String> skipWords = Arrays.asList("in", "the", "as", "a", "and", "to", "by", "of");
        filter.removeUnwantedWords(parsedHtmlAsLinkedList, skipWords);
        System.out.println("Unwanted words removed: \n" + parsedHtmlAsLinkedList + "\n");

        Map<String, Long> wordcountmapper = mapper.countTheWords(parsedHtmlAsLinkedList);
        System.out.println("Words and occurencesare collected to a map: \n" + wordcountmapper + "\n");

        int numberOfMostFrequentWords = 10;
        List<Map.Entry<String, Long>> mostCommonWords = mapper.getMostCommonWords(wordcountmapper, numberOfMostFrequentWords);
        System.out.println(numberOfMostFrequentWords + " most frequent words : \n" + mostCommonWords);
    }
}
