package com.company;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SharedObject{
    public static List<String> siteList = Collections.synchronizedList(new ArrayList<String>());
    public static List<String> emailList = Collections.synchronizedList(new ArrayList<String>());
    public static List<String> vistedSites = Collections.synchronizedList(new ArrayList<String>());

}

class spiders extends Thread {
    static Object lock = new Object();
    static final int MAX_COUNT = 10;
    private String startLink;
    private ArrayList<String> sitesFound;
    private ArrayList<String> emailsFound;
  /*  private HashSet<String> siteLists;

    public spiders(String startLink) {
        this.startLink = startLink;
       // this.siteLists = siteLists;
    }

    public ArrayList<String> getSitesFound() {
        return sitesFound;
    }

    public ArrayList<String> getEmailsFound() {
        return emailsFound;
    }
*/
    @Override
    public void run() {
        for (int i=0;i<MAX_COUNT;i++)
        {
             synchronized ( lock )

            {   WebSpider crawler = new WebSpider();
                Random rand = new Random();
                int r=rand.nextInt(SharedObject.siteList.size()-1);
                SharedObject.siteList.get(r);
                if(!SharedObject.vistedSites.contains(SharedObject.siteList.get(r))){

                    SharedObject.vistedSites.add(SharedObject.siteList.get(r));
                sitesFound = crawler.getLinks(SharedObject.siteList.get(r));
                emailsFound=crawler.getEmails();
                for (String j:sitesFound) {
                    if(!SharedObject.siteList.contains(j)){
                        SharedObject.siteList.add(j);
                    }
                }
                for (String k:emailsFound) {
                    if(!SharedObject.emailList.contains(k)){
                        SharedObject.emailList.add(k);
                    }
                }}
             /*   SharedObject.list.addAll(sitesFound);
                System.out.printf( "%10s %10d : %10d%n",
                         Thread.currentThread().getId(), i);
                try {
                    Thread.sleep( (int) (Math.random()* 5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}*/
                // System.out.println(i);
            }
        }
        System.out.println("ID "+Thread.currentThread().getId());
        System.out.println(SharedObject.emailList.size());
        System.out.println(SharedObject.emailList.toString());
       // System.out.println(Thread.currentThread().getId());
       /* WebSpider crawler = new WebSpider();
        sitesFound = crawler.getLinks(startLink);
        emailsFound = crawler.getEmails();*/
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

        SharedObject.siteList = crawler.getLinks("http://www.Touro.edu/");

        final int MaxRunnables = 50;
        ExecutorService ex = Executors.newFixedThreadPool(4);
        for (int i = 0; i < MaxRunnables; i++) {
            Runnable runnable1 = new spiders();
            ex.execute(runnable1);
        }
/*
          WebSpider crawler = new WebSpider();
        HashSet<String> sitesVisted = new HashSet<>();
        ArrayList<String> emailList = new ArrayList<>();
        int threadCount = 10;
        int iterations = 100000;
        ArrayList<String> sitesToGo;

        sitesToGo = crawler.getLinks("http://www.Touro.edu/");

        for (int m = 1; m < iterations; m++) {
            int cycle = m * threadCount;
         //   int mixer=0;
            //to have the program jump through the sitesToGo list
           // if(sitesToGo.size()>1000)
             //   mixer=sitesToGo.size()/10;
            for (int i = m; i < cycle; i++) {
               // System.out.println(i);
               // System.out.println(cycle);
                //System.out.println(sitesToGo.get(i));
                if(!sitesVisted.contains(sitesToGo.get(i))&&!sitesToGo.get(i).contains("w3")){
                    sitesVisted.add(sitesToGo.get(i));
                Runnable temp = new spiders(sitesToGo.get(i));
                Thread temp1 = new Thread(temp);
                temp1.run();
                ArrayList<String> links = ((spiders) temp).getSitesFound();
                ArrayList<String> emails = ((spiders) temp).getEmailsFound();

                for (int j = 0; j < links.size(); j++) {
                    if (!sitesToGo.contains(links.get(j))) {
                      //  System.out.println(links.get(j));
                     //   sitesVisted.add(links.get(j));
                        sitesToGo.add(links.get(j));
                    }
                }

                for (int k = 0; k < emails.size(); k++) {
                    if (!emailList.contains(emails.get(k))) {
                        emailList.add(emails.get(k));
                    }
                }
            }}
            System.out.println(sitesToGo.size());
            System.out.println(emailList.size());
            System.out.println(emailList.toString());

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
