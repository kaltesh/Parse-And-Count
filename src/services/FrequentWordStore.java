package services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;

public class FrequentWordStore implements WordStore {
    private final HashMap<String, Long> wordsAndOccurrences;

    public FrequentWordStore() {
        this.wordsAndOccurrences = new HashMap<>();
    }

    /**
     * @param word a string to count and store in a Hashmap
     */
    @Override
    public void store(String word) {
        Long count = wordsAndOccurrences.get(word);
        wordsAndOccurrences.put(word, count == null ? 1 : ++count);
    }

    /**
     * Prints out all of the words stored in the field.
     */
    @Override
    public void print() {
        System.out.println("here be all the words and their occurrences ");
        for (Map.Entry<String, Long> entry : wordsAndOccurrences.entrySet()) {
            System.out.print(entry.getKey() + " = " + entry.getValue() + ", ");
        }
        System.out.println();
    }

    /**
     * Prints out the first n words stored in the field.
     *
     * @param n number of words to be printed
     */
    @Override
    public void print(int n) {
        List<Map.Entry<String, Long>> wordsAndOccurwordsAndOccurrencesAsList = new ArrayList<>(wordsAndOccurrences.entrySet());
        wordsAndOccurwordsAndOccurrencesAsList.sort(comparingByValue(Comparator.reverseOrder()));
        Map<String, Long> mostFrequentWords = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : wordsAndOccurwordsAndOccurrencesAsList) {
            if (mostFrequentWords.size() < n) {
                mostFrequentWords.put(entry.getKey(), entry.getValue());
            } else break;
        }
        System.out.println("\nThe " + n + " most frequent words: \n" + mostFrequentWords);
    }
}
