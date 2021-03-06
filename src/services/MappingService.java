package services;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MappingService {
    private final static Logger LOGGER = Logger.getGlobal();

    /**
     * @param parsedHtml the parsed html as a String
     * @return the String converted to a LinkedList
     */
    public static LinkedList<String> makeAListFromString(String parsedHtml) {
        String[] htmlAsArray = parsedHtml.split(" ");
        List<String> htmlAsList = Arrays.asList(htmlAsArray);
        LOGGER.log(Level.FINE, "words collected into a list");
        return new LinkedList<>(htmlAsList);
    }
}
