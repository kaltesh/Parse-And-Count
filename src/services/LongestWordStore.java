package services;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Prints the longest words
 */
public class LongestWordStore implements WordStore {
    private final static Logger LOGGER = Logger.getGlobal();
    private final HashSet<String> allwords;

    public LongestWordStore() {
        this.allwords = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(String word) {
        LOGGER.log(Level.FINE, "words stored in a hashset ");
        allwords.add(word);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        System.out.println("\nHere are all the words: ");
        LOGGER.log(Level.FINE, "list of all words printed");
        for (String s : allwords) {
            System.out.print(s + ", ");
            LOGGER.log(Level.FINEST, "word from a list of all stored words printed");
        }
    }

    /**
     * {@inheritDoc}
     *
     * prints the n longest words
     */
    @Override
    public void print(int n) {
        TreeSet<String> allWordsByLength = new TreeSet<>((s1, s2) -> (s1.length() < s2.length()) ? 1 : -1);
        allWordsByLength.addAll(allwords);
        StringBuilder message = new StringBuilder("\n\nThe " + n + " longest words:\n");
        int i = 0;
        for (String s : allWordsByLength) {
            if (i < n) {
                message.append(s).append(", ");
                LOGGER.log(Level.FINEST, "word appended to longest words message");
                i++;
            } else break;
        }
        message.setLength(message.length() - 2);
        System.out.println(message);
        LOGGER.log(Level.FINE, "list of longest words printed");
    }
}
