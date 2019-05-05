package com.company;


import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        WebSpider crawler =new WebSpider();
        WebCrawler spider = new WebCrawler();
       // spider.getPageLinks("http://www.Touro.edu/", 0);
       // spider.getPageLinks("http://www.Touro.edu/", 0);

        ArrayList<String>sites= new ArrayList<>();
     //   sites.add("http://www.Touro.edu/");
             sites=  crawler.getLinks("http://www.Touro.edu/");



        for(int i =0;i<10000000;i++) {
            //System.out.println(i);
            //System.out.println(sites.get(i));
            ArrayList<String> temp=(crawler.getLinks(sites.get(i)));
            for (String j:temp) {
                if(!sites.contains(j))
                    sites.add(j);
            }
        }

      /*  for (String i:test) {
            if(i!=null)
            System.out.println(i);
        }*/
       /* HashSet<String> link = spider.getLinks();
        ArrayList<String> email=spider.getEmails();
        for (String i:email) {
            System.out.println(i);
        }
        for (String i:link) {
            System.out.println(i);
        }
        System.out.println(email.size());*/
    }
}
