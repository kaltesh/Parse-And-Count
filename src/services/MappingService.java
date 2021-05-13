package services;

import java.util.*; // TODO ML: https://stackoverflow.com/questions/147454/why-is-using-a-wild-card-with-a-java-import-statement-bad
import java.util.stream.Collectors;

public class MappingService {

    private final int amountOfMostFrequentWords;

    public MappingService(int amountOfMostFrequentWords) {
        this.amountOfMostFrequentWords = amountOfMostFrequentWords;
    }

    /**
     * @param parsedHtml the parsed html as a String
     * @return the String converted to a LinkedList
     */
    public LinkedList<String> makeAListFromString(String parsedHtml) { // TODO ML: this can be static, please check all methods
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
                .collect(Collectors.toList());
    }
}
