package services;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class FilteringService {

    /**
     * @param allWords all parsed html as String
     * @return all parsed words without some special characters as String
     */
    public String removeSpecialCharacters(String allWords) {
        String regex = "[,.;:']";
        allWords = allWords.replaceAll(regex, "");
        return allWords;
    }

    /**
     * @param parsedHtml the parsed html as String
     * @param skiptags   the tags whiches' content we want removed
     * @return the parsed html without the given tags' content removed as String
     */
    public String removeTagsContent(String parsedHtml, String @NotNull [] skiptags) {
        Document doc = Jsoup.parse(parsedHtml);
        for (int i = 0; i < skiptags.length; i++) {
            Elements elements = doc.select(skiptags[i]);
            elements.remove();
        }
        parsedHtml = doc.toString();
        return parsedHtml;
    }

    /**
     * @param parsedHtml the parsed html as String
     * @return the parsed html without all the attributes and tags
     */
    public String removeTagsAndAttributes(String parsedHtml) {
        parsedHtml = Jsoup.clean(parsedHtml, Whitelist.none());
        return parsedHtml;
    }

    /**
     * @param allWords  the words of the parsed html as List
     * @param skipWords the words we want removed
     */
    public void removeUnwantedWords(LinkedList<String> allWords, List<String> skipWords) {
        for (int i = allWords.size() - 1; i >= 0; i--) {
            if (skipWords.contains(allWords.get(i))) {
                allWords.remove(i);
            }
        }
    }
}
