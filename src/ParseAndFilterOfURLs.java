import services.CounterService;

import java.util.Arrays;
import java.util.List;

public class ParseAndFilterOfURLs {
    public static void main(String[] args) {

        String url = "https://www.lipsum.com/";
        String[] skipTags = new String[]{"head", "style"};
        List<String> skipWords = Arrays.asList("if", "of", "the", "lorem");
        int numberOfWords = 100;

        CounterService counter = new CounterService(url, skipTags, skipWords, numberOfWords);

        counter.getUrlsMostCommonWords();
    }
}
