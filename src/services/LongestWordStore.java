package services;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
    public void print(int n) {
        Set<String> sortedSet = new TreeSet<>((s1, s2) -> {
            if (s1.length() < s2.length()) {
                return 1;
            } else
                return -1;
        });
        sortedSet.addAll(wordsByLength);
        System.out.print("\n\nThe " + n + " longest word:\n");
        int i = 0;
        for (String s : sortedSet) {
            if (i < n) {
                System.out.print(s + ", ");
                i++;
            } else break;
        }
    }
}
