package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ex094
 */
public class EmailFinder {

    String pattern = "\\b[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+\\b"; //Email Address Pattern
    URL url; //URL Instance Variable
    StringBuilder contents; //Stores our URL Contents
    Set<String> emailAddresses = new HashSet<>(); //Contains unique email addresses


    EmailFinder(String url) {

        try {
            this.url = new URL(url); //Initalizing our URL object
        } catch (MalformedURLException ex) {
            System.out.println("Please include Protocol in your URL e.g. http://www.google.com");
            System.exit(1);
        }
    }

    public void readContents() {
        try {
            //Open Connection to URL and get stream to read
            BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
            contents = new StringBuilder();
            //Read and Save Contents to StringBuilder variable
            String input = "";
            while ((input = read.readLine()) != null) {
                contents.append(input);
            }
            extractEmail();
        } catch (IOException ex) {
          //  System.out.println("Unable to read URL due to Unknown Host..  "+url);
        }
    }

    public void extractEmail() {
        //Creates a Pattern
        Pattern pat = Pattern.compile(pattern);
        //Matches contents against the given Email Address Pattern
        Matcher match = pat.matcher(contents);
        //If match found, append to emailAddresses
        while (match.find()) {
            emailAddresses.add(match.group());
        }

    }


    public ArrayList printAddresses() {

        ArrayList<String> temp = new ArrayList<>();
        for (String emails : emailAddresses) {
            //   System.out.println(emails);
            if (emails.contains(".edu") || emails.contains(".com") || emails.contains(".gov") || emails.contains(".org"))
                temp.add(emails);
        }
        return temp;

    }
}