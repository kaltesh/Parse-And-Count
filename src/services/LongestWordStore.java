package services;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class LongestWordStore implements WordStore {
    private final HashSet<String> wordsByLength;

    public LongestWordStore() {
        this.wordsByLength = new HashSet<>();
    }

    /**
     * @param word a string to count and store in a Hashmap
     */
    @Override
    public void store(String word) {
        wordsByLength.add(word);
    }

    /**
     * Prints out all of the words stored in the field.
     */
    @Override
    public void print() {
        System.out.println("\nHere are all the words: ");
        for (String s : wordsByLength) {
            System.out.print(s + ", ");
        }
    }

    /**
     * prints the first n longest words
     *
     * @param n number of words to be printed
     */
    @Override
    public void print(int n) { // TODO ML: could you please implement this without stream? :)
        LinkedHashSet<String> sortedMap =
                wordsByLength.stream()
                        .sorted((w1, w2) -> w2.length() - w1.length())
                        .limit(n)
                        .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("\n\nThe " + n + " longest \"words\": \n" + sortedMap);
    }
}
