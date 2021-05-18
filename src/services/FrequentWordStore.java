package services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Map.Entry.comparingByValue;

/**
 * prints the most frequent words
 */
public class FrequentWordStore implements WordStore {
    private final HashMap<String, Long> wordsAndOccurrences;
    private final static Logger LOGGER = Logger.getGlobal();

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
        LOGGER.log(Level.FINE, "word stored in hashmap");
    }

    /**
     * Prints out all of the words stored in the field.
     */
    @Override
    public void print() {
        System.out.println("here be all the words and their occurrences ");
        for (Map.Entry<String, Long> entry : wordsAndOccurrences.entrySet()) {
            LOGGER.log(Level.FINEST, "word and occurrence printed");
            System.out.print(entry.getKey() + " = " + entry.getValue() + ", ");
        }
        System.out.println();
        LOGGER.log(Level.FINE, "all words and occurrence printed");

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
        LOGGER.log(Level.FINE, "words placed in an ArrayList to be sorted");
        Map<String, Long> mostFrequentWords = new LinkedHashMap<>(); // TODO ML: I think we need only an int index, and increment it until
        for (Map.Entry<String, Long> entry : wordsAndOccurwordsAndOccurrencesAsList) {
            if (mostFrequentWords.size() < n) {
                LOGGER.log(Level.FINEST, "filling up list of most frequent words to be printed");
                mostFrequentWords.put(entry.getKey(), entry.getValue());
            } else break;
        }
        System.out.println("\nThe " + n + " most frequent words: \n" + mostFrequentWords);
        LOGGER.log(Level.FINE, "most frequent words printed");
    }
}
