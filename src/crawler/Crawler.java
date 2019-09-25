package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jsoup.select.Elements;

/**
 * Represents Crawler class for crawling.
 *
 * @author Mohammad Rahman
 *
 * @version 1
 * @since 1
 */
public class Crawler {

    //get the logger ready
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * gets the crawled info from default WebSite
     *
     * @param url
     * @return HashMap
     */
    public static HashMap<String, String> getInformarion(String url) {

        LOGGER.setLevel(Level.INFO);
        HashMap<String, String> extraInfo = new HashMap<>();

        try {

            if (!url.contains("http")) {
                url = "http://" + url;
            }
            Document document = Jsoup.connect(url).get();

            //crawl and get information then add to data structure
            if (url.endsWith(".html")) {
                LOGGER.info("URL validation passed");
                String title = document.title();
                String price = document.getElementsByClass("price").first().text();
                String description = document.getElementsByClass("product attribute description").text();

                extraInfo.put("Name", title);
                extraInfo.put("Description", description);
                extraInfo.put("Price", price);
                extraInfo.put("URL", url);
                Element table = document.select("table").get(0);// Select table
                Elements rows = table.select("tr");// Select tr’s

                for (int j = 0; j < rows.size(); j++) {// Iterate through table data {

                    extraInfo.put(rows.get(j).select("th").text(), rows.get(j).select("td").text());
                }

                LOGGER.info("Acquired info from " + url + " recorded in memory");

            } else {
                System.err.println("Enter the product URL also");
                LOGGER.warning("Invalid product URL entered");
                JOptionPane.showMessageDialog(null, "Invalid product URL entered");
            }
        } catch (Exception e) {
            System.out.println(extraInfo);
            System.err.println(e.getMessage() + " " + e.getCause() + " " + "Enter a valid URL");
            LOGGER.warning("Invalid URL entered");
            JOptionPane.showMessageDialog(null, "Enter valid URL");
        }

        return extraInfo;

    }

    /**
     * gets the crawled info from any WebSite
     *
     * @param url
     * @param tag
     * @return HashMap
     */
    public static HashMap<String, String> displayTagInfo(String url, String tag) {

        HashMap<String, String> tagInfo = new HashMap<>();
        try {
            if (!url.contains("http")) {
                url = "http://" + url;
            }

            Document document = Jsoup.connect(url).get();

            //check if a webpage or a website root
            if (url.endsWith(".html") || url.endsWith(".com")) {
                if (!tag.isEmpty()) {
                    String[] tagArray = tag.split(" ");

                    for (int i = 0; i < tagArray.length; i++) {

                        Elements rows = document.select(tagArray[i]);// Select tr’s

                        for (int j = 0; j < rows.size(); j++) {// Iterate through table data {
                            tagInfo.put(String.valueOf(rows.get(j).select(tagArray[i])), rows.get(j).select(tagArray[i]).text());

                        }

                    }

                } else {

                    System.out.println("Tags not found");
                    LOGGER.warning("Tags not found");
                    JOptionPane.showMessageDialog(null, "Tags not found");
                }
            } else {
                System.err.println("Enter the product URL also");
                LOGGER.warning("Product URL not found");
                JOptionPane.showMessageDialog(null, "Enter product URL also");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + " " + "Product URL not found! Enter a valid URL");
            LOGGER.warning("Invalid URL entered");
            JOptionPane.showMessageDialog(null, "Product URL not found! Enter a valid URL");
        }

        return tagInfo;
    }

}
