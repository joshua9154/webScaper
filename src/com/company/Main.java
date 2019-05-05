package com.company;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class spiders implements Runnable {

    private String startLink;
    private ArrayList<String> sitesFound;
    private ArrayList<String> emailsFound;
    private HashSet<String> siteLists;

    public spiders(String startLink, HashSet siteLists) {
        this.startLink = startLink;
        this.siteLists = siteLists;
    }

    public ArrayList<String> getSitesFound() {
        return sitesFound;
    }

    public ArrayList<String> getEmailsFound() {
        return emailsFound;
    }

    @Override
    public void run() {
        WebSpider crawler = new WebSpider();
        sitesFound = crawler.getLinks(startLink, siteLists);
        emailsFound = crawler.getEmails();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        WebSpider crawler = new WebSpider();
        HashSet<String> siteLists = new HashSet<>();
        ArrayList<String> emailList = new ArrayList<>();
        int threadCount = 2;
        int iterations = 10;
        ArrayList<String> sites;

        sites = crawler.getLinks("http://www.Touro.edu/", siteLists);

        final int MaxRunnables = 50;
        ExecutorService ex = Executors.newFixedThreadPool(4);
        for (int i = 0; i < MaxRunnables; i++) {
            Runnable runnable1 = new spiders( sites.get(i),siteLists);
            ex.execute(runnable1);
        }
       /*
          WebSpider crawler = new WebSpider();
       HashSet<String> siteLists = new HashSet<>();
        ArrayList<String> emailList = new ArrayList<>();
        int threadCount = 2;
        int iterations = 10;
        ArrayList<String> sites;

        sites = crawler.getLinks("http://www.Touro.edu/", siteLists);

        for (int m = 1; m < iterations; m++) {
            int cycle = m * threadCount;
            for (int i = m; i < cycle; i++) {
                System.out.println(i);
               // System.out.println(cycle);
                Runnable temp = new spiders(sites.get(i), siteLists);
                Thread temp1 = new Thread(temp);
                temp1.run();
                ArrayList<String> links = ((spiders) temp).getSitesFound();
                ArrayList<String> emails = ((spiders) temp).getSitesFound();

                for (int j = 0; j < links.size(); j++) {
                    if (!siteLists.contains(links.get(j))) {
                      //  System.out.println(links.get(j));
                        siteLists.add(links.get(j));
                        sites.add(links.get(j));
                    }
                }

                for (int k = 0; k < emails.size(); k++) {
                    if (!emailList.contains(emails.get(k))) {
                        emailList.add(emails.get(k));
                    }
                }
            }
            System.out.println(sites.toString());
        }
*/

        //WebCrawler spider = new WebCrawler();
        // spider.getPageLinks("http://www.Touro.edu/", 0);
        // spider.getPageLinks("http://www.Touro.edu/", 0);

        // ArrayList<String>sites= new ArrayList<>();
        //   sites.add("http://www.Touro.edu/");
      /*       sites=  crawler.getLinks("http://www.Touro.edu/");



        for(int i =0;i<10000000;i++) {
            //System.out.println(i);
            //System.out.println(sites.get(i));
            ArrayList<String> temp=(crawler.getLinks(sites.get(i)));
            for (String j:temp) {
                if(!sites.contains(j))
                    sites.add(j);
            }
        }*/

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
