package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

class WebCrawler {
    private final int MAX_DEPTH = 8;
    private HashSet<String> links = new HashSet<>();
    private ArrayList<String> emails = new ArrayList<>();
    private EmailFinder finder;
    private int marker =50;

    public HashSet<String> getLinks() {
        return links;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void getPageLinks(String URL, int depth) {
        if ((!links.contains(URL) && depth < MAX_DEPTH)) {
            try {
                links.add(URL);
                System.out.println(URL);
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                finder = new EmailFinder(URL);
                finder.readContents();
                finder.extractEmail();
                ArrayList<String> temp = finder.printAddresses();
                for (int i = 0; i < temp.size(); i++) {
                    if (!emails.contains(temp.get(i))) {
                        emails.add(temp.get(i));
                        if(emails.size()==marker){
                            marker=marker+50;
                            System.out.println(emails.size());
                            System.out.println(emails.toString());}

                    }
                }

                depth++;
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth);

                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());

            }
        }
    }


}
