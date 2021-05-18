package services;

import java.util.LinkedList;

/**
 * calls the methods to print thr words with the selected filters
 */
public class PrintingService { // TODO ML: typo in javadoc, IntelliJ doesn't show it to you?
    private final WordStore wordStore;
    private final LinkedList<String> parsedHtmlAsList;
    private final int amountOfWords;

    public PrintingService(WordStore wordStore, LinkedList<String> parsedHtmlAsList, int amountOfWords) {
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
