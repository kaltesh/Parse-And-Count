package services;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Prints the longest words
 */
public class LongestWordStore implements WordStore {
    private final HashSet<String> wordsByLength;
    private final static Logger LOGGER = Logger.getGlobal();

    public LongestWordStore() {
        this.wordsByLength = new HashSet<>();
    }

    /**
     * @param word a string to count and store in a Hashmap
     */
    @Override
    public void store(String word) {
        LOGGER.log(Level.FINE, "words stored in a hashset ");
        wordsByLength.add(word);
    }

    /**
     * Prints out all of the words stored in the field.
     */
    @Override
    public void print() {
        System.out.println("\nHere are all the words: ");
        LOGGER.log(Level.FINE, "list of all words printed");
        for (String s : wordsByLength) {
            System.out.print(s + ", ");
            LOGGER.log(Level.FINEST, "word from a list of all stored words printed");
        }
    }

    /**
     * prints the first n longest words
     *
     * @param n number of words to be printed
     */
    @Override
    public void print(int n) {
        Set<String> sortedSet = new TreeSet<>((s1, s2) -> (s1.length() < s2.length()) ? 1 : -1);
        LOGGER.log(Level.FINE, "treeSet with string length comparator initialized");
        sortedSet.addAll(wordsByLength);
        LOGGER.log(Level.FINE, "treeSet populated with words");
        System.out.print("\n\nThe " + n + " longest word:\n");
        int i = 0;
        for (String s : sortedSet) {
            if (i < n) {
                System.out.print(s + ", ");
                LOGGER.log(Level.FINEST, "longest words being pringed");
                i++;
            } else break;
        }
        LOGGER.log(Level.FINE, "list of longest words printed");
    }
}
