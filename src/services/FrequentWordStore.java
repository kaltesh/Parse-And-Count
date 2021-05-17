package services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void print(int n) { // TODO ML: could you please implement this without stream? :)
        LinkedHashMap<String, Long> sortedMap =
                wordsAndOccurrences.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(n)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (w1, w2) -> w1, LinkedHashMap::new));

        System.out.println("\nThe " + n + " most frequent words :\n" + sortedMap);
    }
}
