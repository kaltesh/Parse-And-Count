package services;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class LengthComparingService implements WordStore {
    public LengthComparingService(int amountOfWords) {
        this.amontOfWords = amountOfWords;
        this.map = new HashSet<>();
    }

    public int getAmontOfWords() {
        return this.amontOfWords;
    }

    private final int amontOfWords;
    private final HashSet<String> map;

    /**
     * @param word a string to count and store in a Hashmap
     */
    @Override
    public void store(String word) {
        map.add(word);
    }

    /**
     * Prints out all of the words stored in the field.
     */
    @Override
    public void print() {
        System.out.println("\nHere be all the words: ");
        for (String s : map) {
            System.out.print(s + ", ");
        }
    }

    /**
     * prints the first n longest words
     *
     * @param n number of words to be printed
     */
    @Override
    public void print(int n) {
        LinkedHashSet<String> sortedMap =
                map.stream()
                        .sorted((w1, w2) -> w2.length() - w1.length())
                        .limit(n)
                        .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("\n\nThe " + n + " longest \"words\": \n" + sortedMap);
    }
}
