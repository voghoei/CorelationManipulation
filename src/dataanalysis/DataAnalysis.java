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

/**
 *
 * @author voghoei
 */
public class DataAnalysis {

    public static void main(String[] args) {
        String plantLine;
        String countCurrentLine;
        BufferedReader br;
        BufferedWriter bw;
        ResultSet rs;
        int counter = 0;
        int line = 1;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; instance= DESKTOP-8SM3HF1\\SQLEXPRESS;databaseName=DataAnalysis;integratedSecurity=true");
            Statement statement = conn.createStatement();

            br = new BufferedReader(new FileReader("E:\\2016 winter data\\Data for research\\short version_Sample_data\\PB_reverse_limited corelation.csv"));
            bw = new BufferedWriter(new FileWriter("E:\\2016 winter data\\Data for research\\short version_Sample_data\\graph Corelation all.csv"));
            plantLine = br.readLine();

            while ((countCurrentLine = br.readLine()) != null) {
                System.out.println(" line =  " + line );  
                String bacteria = countCurrentLine.split(",")[0];
                //System.out.println("length:  " + countCurrentLine.split(",").length);
                for (int i = 1; i < line; i++) {
                    String No = countCurrentLine.split(",")[i];
                    String Position = plantLine.split(",")[i];
                    
                    if (!No.equals("NA") ) {    
                        //if ((Float.parseFloat(No) > 0.8 || Float.parseFloat(No)<-0.8)&& Float.parseFloat(No)!=1 ) {   
                            //System.out.println(" i " + i + " bacteria: " + bacteria + " Position:  " + Position + " No: " + No);  
                            bw.write(bacteria + "," + No + ","+ Position );
                            bw.newLine();
                            counter++;
                        //}   
                    }                    
                }
                line++;
            }
            br.close();
            bw.close();
            System.out.println(counter + " Rows Data Inserted");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.err.println("Problem Connecting!  " + e);
        }

    }

}
