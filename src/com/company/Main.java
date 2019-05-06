package com.company;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SharedObject {
    public static List<String> siteList = Collections.synchronizedList(new ArrayList<String>());
    public static List<String> emailList = Collections.synchronizedList(new ArrayList<String>());
    public static Collection<String> visitedSites = Collections.synchronizedCollection(new HashSet<>());

}

class spiders extends Thread {
    static Object lock = new Object();
    static int MAX_COUNT = 100;
    private ArrayList<String> sitesFound;
    private ArrayList<String> emailsFound;

    @Override
    public void run() {

        for (int i = 0; i < MAX_COUNT; i++) {
            //   synchronized ( lock )
            try {
                sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            {
                WebSpider crawler = new WebSpider();
                Random rand = new Random();
                int r = rand.nextInt(SharedObject.siteList.size() - 1);
                SharedObject.siteList.get(r);
                if (!SharedObject.visitedSites.contains(SharedObject.siteList.get(r))) {

                    SharedObject.visitedSites.add(SharedObject.siteList.get(r));
                    sitesFound = crawler.getLinks(SharedObject.siteList.get(r));
                    emailsFound = crawler.getEmails();
                    for (String j : sitesFound) {
                        if (!SharedObject.siteList.contains(j)) {
                            SharedObject.siteList.add(j);
                        }
                    }
                    for (String k : emailsFound) {
                        if (!SharedObject.emailList.contains(k)) {
                            SharedObject.emailList.add(k);
                        }
                    }
                }

            }
        }
      //  System.out.println("ID " + currentThread().getId());
        // System.out.println("Sites Found "+SharedObject.siteList.size());
        // System.out.println("Sites Visted "+SharedObject.visitedSites.size());
        System.out.println("Emails Found " + SharedObject.emailList.size());
       // System.out.println(SharedObject.emailList.get(SharedObject.emailList.size() - 1));
        System.out.println(SharedObject.emailList.toString());
        // System.out.println(Thread.currentThread().getId());

    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        WebSpider crawler = new WebSpider();

        SharedObject.siteList = crawler.getLinks("http://www.Touro.edu/");

        final int MaxRunnables = 1000000;
        ExecutorService ex = Executors.newFixedThreadPool(50);
        for (int i = 0; i < MaxRunnables; i++) {
            Runnable runnable1 = new spiders();
            ex.execute(runnable1);
        }
    }
}
