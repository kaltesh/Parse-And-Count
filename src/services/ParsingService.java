package services;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Contains the method for parsing given URL
 */
public class ParsingService {
    private final String url;

    public ParsingService(String url) {
        this.url = url;
    }

    /**
     * @return parsed html as String
     */
    public String parseHTML() {
        StringBuilder theSiteAsSB = new StringBuilder();
        Scanner scanner = null;
        try {
            URL urlToParse = new URL(url);
            scanner = new Scanner(urlToParse.openStream());
            while (scanner.hasNext()) {
                theSiteAsSB.append(scanner.next()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return theSiteAsSB.toString();
    }
}
