package services;

import java.util.ArrayList;

/**
 * calls the methods to print thr words with the selected filters
 */
public class PrintingService {
    private final WordStore wordStore;
    private final ArrayList<String> parsedHtmlAsList;
    private final int amountOfWords;

    public PrintingService(WordStore wordStore, ArrayList<String> parsedHtmlAsList, int amountOfWords) {
        this.wordStore = wordStore;
        this.parsedHtmlAsList = parsedHtmlAsList;
        this.amountOfWords = amountOfWords;
    }

    public void wordPrinter() {
        for (int i = 0; i < parsedHtmlAsList.size(); i++) {
            wordStore.store(parsedHtmlAsList.get(i));
        }
        wordStore.print();
        wordStore.print(amountOfWords);

    }
}
