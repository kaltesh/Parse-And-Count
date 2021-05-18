package logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Sets the format of the stored logs
 */
class MyHtmlFormatter extends Formatter {

    /**
     * {@inheritDoc}
     */
    public String format(LogRecord logRecord) {
        StringBuffer buf = new StringBuffer(1000);
        buf.append("<tr>\n");

        if (logRecord.getLevel().intValue() >= Level.WARNING.intValue()) {
            buf.append("\t<td style=\"color:red\">");
            buf.append("<b>");
            buf.append(logRecord.getLevel());
            buf.append("</b>");
        } else {
            buf.append("\t<td>");
            buf.append(logRecord.getLevel());
        }
        buf.append("</td>\n");
        buf.append("\t<td>");
        buf.append(calculateDateAndTime(logRecord.getMillis()));
        buf.append("</td>\n");
        buf.append("\t<td>");
        buf.append(formatMessage(logRecord));
        buf.append("</td>\n");
        buf.append("</tr>\n");

        return buf.toString();
    }

    /**
     * @param milliSecs event time in milliseconds since 1970.
     * @return the time and date in an acceptable format
     */
    private String calculateDateAndTime(long milliSecs) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultDate = new Date(milliSecs);
        return dateFormat.format(resultDate);
    }

    /**
     * {@inheritDoc}
     */
    public String getHead(Handler h) {
        return "<!DOCTYPE html>\n<head>\n<style>\n"
                + "table { width: 100% }\n"
                + "th { font:bold 10pt Tahoma; }\n"
                + "td { font:10pt Tahoma; }\n"
                + "h1 {font:normal 11pt Tahoma;}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>" + (new Date()) + "</h1>\n"
                + "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n"
                + "<tr align=\"left\">\n"
                + "\t<th style=\"width:10%\">Loglevel</th>\n"
                + "\t<th style=\"width:15%\">Time</th>\n"
                + "\t<th style=\"width:75%\">Log Message</th>\n"
                + "</tr>\n";
    }

    /**
     * {@inheritDoc}
     */
    public String getTail(Handler h) {
        return "</table>\n</body>\n</html>";
    }
}