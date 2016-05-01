package frequency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

//Author: G. Allen Johnson
//Midterm Project - Part 5
//Class: CS 17.11 - D. Pearson
//Version: 1.14

public class WordCount {

    public static void main(String[] args) throws IOException {

        File inFile = new File("words.txt");
        File outFile = new File("word_frequency_verification.txt");
        BufferedReader dIn = null;
        FileWriter dOut = null;

        // Used TreeMap to get a sorted order.
        Map<String, Integer> wordNum = new TreeMap<String, Integer>();

        try {
            dIn = new BufferedReader(new FileReader(inFile));
            dOut = new FileWriter(outFile);

            // Had to use PrintWriter to get the bloody output to print in
            // separate lines in the text file.
            PrintWriter outputStream = new PrintWriter(dOut);

            String s;
            while ((s = dIn.readLine()) != null) {

                // Separates strings around non-Words
                String[] line = Pattern.compile("\\W").split(s);
                for (String i : line) {

                    // Filters out the whitespace and builds the map
                    if (!i.equals("")) {
                        Integer f = wordNum.get(i);
                        wordNum.put(i, f == null ? 1 : f + 1);
                    }
                }
            }

            // This is apparently the only way to iterate through map
            // entries
            for (Map.Entry<String, Integer> entry : wordNum.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
                outputStream.println(entry.getKey() + " : " + entry.getValue());
            }
            // This wouldn't recognize in my finally block, so I had to close it
            // out here:
            outputStream.close();

        } catch (IOException ioe) {
            System.out.println("Caught IOException: " + ioe);
        } finally {
            if (dIn != null) {
                dIn.close();
            }
            if (dOut != null) {
                dOut.close();
            }
        }
    }
}
