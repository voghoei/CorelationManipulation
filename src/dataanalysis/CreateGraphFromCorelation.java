/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author voghoei
 */
public class CreateGraphFromCorelation {

    public static void main(String[] args) {
        String bacteriaLine;
        String countCurrentLine;
        String currentLine;
        BufferedReader br;
        BufferedReader brf;
        BufferedWriter bw;
        ResultSet rs;
        int counter = 0;
        int line = 1;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\voghoei\\Desktop\\Sample_data\\corResultFiltered.csv"));
            bw = new BufferedWriter(new FileWriter("C:\\Users\\voghoei\\Desktop\\Sample_data\\graph_corResultFiltered.csv"));
            String BacteriaLine = br.readLine();
            String[] bacteriaList = BacteriaLine.split(",");
            
            while ((currentLine = br.readLine()) != null) {
                
                String[] CurrentLine = currentLine.split(",");
                String Source = CurrentLine[0];

                //System.out.println("length:  " + countCurrentLine.split(",").length);
                for (int i = 1; i < line && i < bacteriaList.length; i++) {
                    String Destnition = bacteriaList[i];
                    String No = CurrentLine[i];
                    //System.out.println("No:  " +No);
                    if (Double.parseDouble(No) != 0){
                        bw.write(Source + "," + No + "," + Destnition);
                        bw.newLine();
                    }
                    counter++;

                }
                line++;
            }
            br.close();
            bw.close();
            System.out.println(counter + " Rows Data Inserted");
        } catch (IOException e) {
            System.err.println("Problem Connecting!  " + e);
        }
    }
}
