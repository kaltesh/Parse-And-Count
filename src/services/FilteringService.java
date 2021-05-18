package services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Contains the methods to remove data from given strings and collections
 */
public class FilteringService {
    private final String[] skipTags;
    private final HashSet<String> skipWords;
    private final static Logger LOGGER = Logger.getGlobal(); // TODO ML: please start the class with static variables

    public FilteringService(String[] skipTags, HashSet<String> skipWords) {
        this.skipTags = skipTags;
        this.skipWords = skipWords;
    }

    /**
     * @param allWords all parsed html as String
     * @return all parsed words without some special characters as String
     */
    public static String removeSpecialCharacters(String allWords) {
        String regex = "[\"@,.;:'()=&?-]";
        allWords = allWords.replaceAll(regex, " ");
        allWords = allWords.toLowerCase();
        LOGGER.log(Level.SEVERE, "special characters removed from words");
        return allWords;
    }

    /**
     * @param parsedHtml the parsed html as String
     * @return the parsed html without the given tags' content removed as String
     */
    public String removeTagsContent(String parsedHtml) {
        Document doc = Jsoup.parse(parsedHtml);

        for (int i = 0; i < skipTags.length; i++) {
            Elements elements = doc.select(skipTags[i]);
            elements.remove();
            LOGGER.log(Level.FINEST, "Element removed");
        }
        parsedHtml = doc.toString();
        LOGGER.log(Level.FINE, "Elements removed");
        return parsedHtml;
    }

    /**
     * @param parsedHtml the parsed html as String
     * @return the parsed html without all the attributes and tags
     */
    public static String removeTagsAndAttributes(String parsedHtml) {
        parsedHtml = Jsoup.clean(parsedHtml, Whitelist.none());
        parsedHtml = parsedHtml.replaceAll("\\s{2,}", " ").trim();
//        parsedHtml = parsedHtml.replaceAll("\\s{2,}", " ").trim(); // TODO ML: please ddon't leave comment lines like this in the source
        LOGGER.log(Level.FINE, "tags and attributes removed");
        return parsedHtml;
    }

    /**
     * @param allWords the words of the parsed html as List
     */
    public void removeUnwantedWords(LinkedList<String> allWords) {
        for (int i = allWords.size() - 1; i >= 0; i--) {
            if (skipWords.contains(allWords.get(i))) {
                allWords.remove(i);
                LOGGER.log(Level.FINEST, "word removed");
            }
        }
        LOGGER.log(Level.FINE, "all given words removed");
    }
}
