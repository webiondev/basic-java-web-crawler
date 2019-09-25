
package crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Represents main class for console output [old version].
 * @author Mohammad Rahman
 * 
 * @version 1
 * @since 1
*/
public class Main {

   /* public static void main(String[] args) {
        String menuItem, value, url, tag;
       List<String> products;
        // TODO code application logic here
        //display menu 1. crawl 2. database
        System.out.println("Choose from menu: 1. crawl default 2. crawl for specific tags 3. database");
        Scanner option = new Scanner(System.in);
        menuItem = option.nextLine();

        try {
            switch (menuItem) {

                case "1":
                    System.out.println("Please enter the product URL for \"http://magento-test.finology.com.my\" :");
                    Scanner site = new Scanner(System.in);
                    url = site.nextLine();
                    products=new ArrayList<>();
                    products=Crawler.getInformarion(url);
                    Database.insert(products);
                    //System.out.println(products.get(3));
                    break;
                case "2":
                    System.out.println("Please enter the tags for crawling [each tag separated by space] OR enter to use default tags:");
                    Scanner tags = new Scanner(System.in);
                    tag = tags.nextLine();
                    //Crawler.displayTagInfo(url, tag);

                    break;
                case "3":
                    //display results
                    break;
                default:
                    System.out.println("Please choose from menu");
                //
                // 
            }
        } catch (Exception e) {
            System.out.println("Choice not recognised");
        }
    }*/
}
