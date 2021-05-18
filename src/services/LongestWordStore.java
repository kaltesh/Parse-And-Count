package services;

import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Prints the longest words
 */
public class LongestWordStore implements WordStore {
    private final static Logger LOGGER = Logger.getGlobal();
    private final TreeSet<String> wordsByLength;

    public LongestWordStore() {
        this.wordsByLength = new TreeSet<>((s1, s2) -> (s1.length() < s2.length()) ? 1 : -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void store(String word) {
        LOGGER.log(Level.FINE, "words stored in a hashset ");
        wordsByLength.add(word);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public void print(int n) {
        StringBuilder message = new StringBuilder("\n\nThe " + n + " longest words:\n");
        int i = 0;
        for (String s : wordsByLength) {
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
