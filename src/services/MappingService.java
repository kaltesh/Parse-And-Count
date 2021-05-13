package services;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 * Contains the methods for storing and mapping
 */
public class MappingService {
    public MappingService(int amountOfMostFrequentWords) {
        this.amountOfMostFrequentWords = amountOfMostFrequentWords;
    }

    public int getAmountOfMostFrequentWords() {
        return amountOfMostFrequentWords;
    }

    private final int amountOfMostFrequentWords;
    private final static Logger LOGGER = Logger.getGlobal();

    /**
     * @param parsedHtml the parsed html as a String
     * @return the String converted to a LinkedList
     */
    public static LinkedList<String> makeAListFromString(String parsedHtml) {
        String[] htmlAsArray = parsedHtml.split(" ");
        List<String> htmlAsList = Arrays.asList(htmlAsArray);
        LOGGER.log(Level.FINE, "words collected into a list");

        return new LinkedList<>(htmlAsList);
    }

    /**
     * @param htmlWordsAsList the filtered words as a List
     * @return all the words and their occurrences in a HasMap
     */
    public static Map<String, Long> countTheWords(List<String> htmlWordsAsList) {
        int i = 0;
        return htmlWordsAsList.stream()
                .peek(word -> LOGGER.log(Level.FINEST, "a word is getting stored in hashmap"))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

    }

    /**
     * @param map all the words and their occurrences in a HasMap
     * @return List of entries containing the most frequent words and their occurrences
     */
    public List<Map.Entry<String, Long>> getMostCommonWords(Map<String, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(amountOfMostFrequentWords)
                .peek(word -> LOGGER.log(Level.FINEST, "one of the most frequent words being collected"))
                .collect(Collectors.toList());
    }
}
