package com.company;


import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        WebCrawler spider = new WebCrawler();
       // spider.getPageLinks("http://www.Touro.edu/", 0);
        spider.getPageLinks("http://www.Touro.edu/", 0);
        HashSet<String> link = spider.getLinks();
        ArrayList<String> email=spider.getEmails();
        for (String i:email) {
            System.out.println(i);
        }
        for (String i:link) {
            System.out.println(i);
        }
        System.out.println(email.size());
    }
}
