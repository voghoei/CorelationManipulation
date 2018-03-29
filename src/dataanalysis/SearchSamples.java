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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author voghoei
 */
public class SearchSamples {

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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; instance= DESKTOP-8SM3HF1\\SQLEXPRESS;databaseName=DataAnalysis;integratedSecurity=true");
            Statement statement = conn.createStatement();

            br = new BufferedReader(new FileReader("E:\\2016 winter data\\Data for research\\qiime1_reverse.csv"));
            bw = new BufferedWriter(new FileWriter("E:\\2016 winter data\\Data for research\\corelation_sample_result.csv"));
            HashMap<String, HashSet> hmap = new HashMap<String, HashSet>();
            bacteriaLine = br.readLine();

            while ((countCurrentLine = br.readLine()) != null && line < 3) {
                int NoCol = countCurrentLine.split(",").length;

                String Sample = countCurrentLine.split(",")[0];

                HashSet<String> hset = new HashSet<String>();

                for (int i = 1; i < NoCol; i++) {
                    String No = countCurrentLine.split(",")[i];
                    String Bacteria = bacteriaLine.split(",")[i];
                    if (!No.equals("NA")) {
                        if (Float.parseFloat(No) > 0) {
                            hset.add(Bacteria);
                            System.out.println(" i " + i + " Sample: " + Sample + " No:  " + No + " Bacteria: " + Bacteria);
                        }
                    }
                }
                hmap.put(Sample, hset);
                line++;
            }
            System.out.println(" hmap :     " + hmap.toString());

            brf = new BufferedReader(new FileReader("E:\\2016 winter data\\Data for research\\corelation_sample.csv"));
            while ((currentLine = brf.readLine()) != null) {
                String[] sampleBacterias = currentLine.split(",");
                for (int i = 0; i < sampleBacterias.length; i++) {
                    bw.write(sampleBacterias[i] + ",");
                }

                for (int i = 0; i < sampleBacterias.length; i++) {
                    for (int j = i; j < sampleBacterias.length; j++) {
                        bw.write(hmap.get(sampleBacterias[i].length()) + ",");
                    }
                    for (int j = i; j < sampleBacterias.length; j++) {
                        for (int k = i+1; k <= j; j++) {
                            if (hmap.get(sampleBacterias[i]).isEmpty())
                                    break;
                            bw.write(hmap.get(sampleBacterias[i].length()) + ",");
                        }
                    }
                }

            }
            br.close();
            bw.close();

            System.out.println(counter + " Rows Data Inserted");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.err.println("Problem Connecting!  " + e);
        }

    }

}
