package services;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class ParsingService {

    public String parseHTML(String url) {
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
