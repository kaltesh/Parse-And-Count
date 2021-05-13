package services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class FilteringService {
    private final String[] skipTags;
    private final List<String> skipWords;

    public FilteringService(String[] skipTags, List<String> skipWords) {
        this.skipTags = skipTags;
        this.skipWords = skipWords;
    }

    /**
     * @param allWords all parsed html as String
     * @return all parsed words without some special characters as String
     */
    public String removeSpecialCharacters(String allWords) {
        String regex = "[\"@,.;:'-]";
        allWords = allWords.replaceAll(regex, " ");
        allWords = allWords.toLowerCase();
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
            doc.normalise();
        }
        parsedHtml = doc.toString();
        return parsedHtml;
    }

    /**
     * @param parsedHtml the parsed html as String
     * @return the parsed html without all the attributes and tags
     */
    public String removeTagsAndAttributes(String parsedHtml) {
//        String cleanHtml = Jsoup.clean(html, "", Whitelist.relaxed(), settings);
        parsedHtml = Jsoup.clean(parsedHtml, Whitelist.none());
        parsedHtml = parsedHtml.replaceAll("\\s{2,}", " ").trim();
        return parsedHtml;
    }

    /**
     * @param allWords the words of the parsed html as List
     */
    public void removeUnwantedWords(LinkedList<String> allWords) {
        for (int i = allWords.size() - 1; i >= 0; i--) {
            if (skipWords.contains(allWords.get(i))) {
                allWords.remove(i);
            }
        }
    }
}
