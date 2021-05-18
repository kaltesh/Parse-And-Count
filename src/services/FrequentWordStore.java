package services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Map.Entry.comparingByValue;

/**
 * prints the most frequent words
 */
public class FrequentWordStore implements WordStore {
    private final static Logger LOGGER = Logger.getGlobal();
    private final HashMap<String, Long> wordsAndOccurrences;

    public FrequentWordStore() {
        this.wordsAndOccurrences = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(String word) {
        Long count = wordsAndOccurrences.get(word);
        wordsAndOccurrences.put(word, count == null ? 1 : ++count);
        LOGGER.log(Level.FINE, "word stored in hashmap");
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public void print(int n) {
        List<Map.Entry<String, Long>> wordsAndOccurrencesAsList = new ArrayList<>(wordsAndOccurrences.entrySet());
        wordsAndOccurrencesAsList.sort(comparingByValue(Comparator.reverseOrder()));
        LOGGER.log(Level.FINE, "words placed in an ArrayList to be sorted");
        StringBuilder message = new StringBuilder("\nThe" + n + " most frequent words: \n");
        for (int i = 0; i < n; i++) {
            message.append(wordsAndOccurrencesAsList.get(i)).append(", ");
            LOGGER.log(Level.FINEST, "word appended to most frequent words message");
        }
        message.setLength(message.length() - 2);
        System.out.println(message);
        LOGGER.log(Level.FINE, "most frequent words printed");
    }
}
