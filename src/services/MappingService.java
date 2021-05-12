package services;

import java.util.*;
import java.util.stream.Collectors;

public class MappingService {

    /**
     * @param parsedHtml the parsed html as a String
     * @return the String converted to a LinkedList
     */
    public LinkedList<String> makeAListFromString(String parsedHtml) {
        String[] htmlAsArray = parsedHtml.split(" ");
        List<String> htmlAsList = Arrays.asList(htmlAsArray);
        return new LinkedList<>(htmlAsList);
    }

    /**
     * @param htmlWordsAsList the filtered words as a List
     * @return all the words and their occurrences in a HasMap
     */
    public Map<String, Long> countTheWords(List<String> htmlWordsAsList) {
        return htmlWordsAsList.stream()
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

    /**
     * @param map                       all the words and their occurrences in a HasMap
     * @param amountOfMostFrequentWords the amount of words we're looking for
     * @return List of entries containing the most frequent words and their occurrences
     */
    public List<Map.Entry<String, Long>> getMostCommonWords(Map<String, Long> map, int amountOfMostFrequentWords) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(amountOfMostFrequentWords)
                .collect(Collectors.toList());
    }
}
