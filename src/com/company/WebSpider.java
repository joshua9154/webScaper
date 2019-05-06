package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.ArrayList;


public class WebSpider {
    private ArrayList<String> emails = new ArrayList<>();
    private EmailFinder finder;

    public ArrayList<String> getEmails() {
        return emails;
    }

    public ArrayList getLinks(String URL) {
        ArrayList<String> sites = new ArrayList<>();
        try {
            Document document = Jsoup.connect(URL).get();
            Elements linksOnPage = document.select("a[href]");

            finder = new EmailFinder(URL);
            finder.readContents();
            emails = finder.printAddresses();


            for (Element page : linksOnPage) {

                if (page.attr("href").contains("https")) {
                    sites.add(page.attr("abs:href"));
                }
            }
        } catch (IOException e) {

        }
        return sites;
    }
}
