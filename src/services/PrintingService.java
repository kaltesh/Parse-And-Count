package services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PrintingService implements WordStore { // TODO ML: could you please find some better class name (without 'Service')?
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
            Long count = map.get(word); // TODO ML: this variable is not used!
            map.put(word, map.get(word) + 1);
        }
        
        // TODO ML: this is a more compact solution, and you need to get the word only once from the map, please check it :)
//        Long count = map.get(word);
//        map.put(word, count == null ? 1 : count+1 );
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
    public void print(int n) { // TODO ML: could you please implement this without stream? :)
        LinkedHashMap<String, Long> sortedMap =
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(n)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (w1, w2) -> w1, LinkedHashMap::new));

        System.out.println("\nResults slightly differently  :\n" + sortedMap);

    }
}
