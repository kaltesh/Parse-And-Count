package services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintingService implements WordStore {
    public PrintingService(int amountOfWords) {
        this.amontOfWords = amountOfWords;
        this.map = new HashMap<>();
    }

    public int getAmountOfWords() {
        return this.amontOfWords;
    }

    private final int amontOfWords;
    private final HashMap<String, Long> map;

    /**
     * @param word a string to count and store in a Hashmap
     */
    @Override
    public void store(String word) {
        if (!map.containsKey(word)) {
            map.put(word, 1L);
        } else {
            Long count = map.get(word);
            map.put(word, map.get(word) + 1);
        }
    }

    /**
     * Prints out all of the words stored in the field.
     */
    @Override
    public void print() {
        System.out.println("here be all the words and their occurrences ");
        for (Map.Entry<String, Long> entry : map.entrySet()) {
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
        LinkedHashMap<String, Long> sortedMap =
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(n)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (w1, w2) -> w1, LinkedHashMap::new));

        System.out.println("\nResults slightly differently  :\n" + sortedMap);

    }
}

