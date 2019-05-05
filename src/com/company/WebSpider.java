package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;

public class WebSpider {
    private HashSet<String> links = new HashSet<>();
    private ArrayList<String> emails = new ArrayList<>();
    private EmailFinder finder;
    private int marker =10;

    public ArrayList getLinks(String URL) {
        ArrayList<String> sites= new ArrayList<>();
        if (!links.contains(URL)) {
            try {
                links.add(URL);
             //   System.out.println(URL);
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                finder = new EmailFinder(URL);
                finder.readContents();
               // finder.extractEmail();
                ArrayList<String> temp = finder.printAddresses();
                for (int i = 0; i < temp.size(); i++) {
                    if (!emails.contains(temp.get(i))) {
                        emails.add(temp.get(i));
                        if(emails.size()==marker){
                            marker=marker+10;
                            System.out.println(emails.size());
                            System.out.println(emails.toString());}

                    }
                }

                for (Element page : linksOnPage) {

                    if (page.attr("href").contains("https")) {
                               sites.add(page.attr("abs:href"));
                     //   getLinks(page.attr("abs:href"));

                    }
                }
            } catch (IOException e) {

            }

        }
return sites;
    }
}
