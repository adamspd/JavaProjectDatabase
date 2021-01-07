package gui;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CSVParsing extends JMenuBar {

    public CSVParsing() {
        String csvFile = "C:\\Users\\Adams Pierre David\\IdeaProjects\\JavaProjectDatabase\\fic.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String [] data = line.split(csvSplitBy);
                System.out.printf(data[1]/*, "\n", data[1], "\n", data[2]*/);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
