/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysis;

import java.io.BufferedReader;
import java.io.FileReader;
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
public class InsertInfo {

      public static void main(String[] args) {
        String plantLine;
        String countCurrentLine;
        BufferedReader br ;
        ResultSet rs ;
        int counter = 0;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; instance= DESKTOP-8SM3HF1\\SQLEXPRESS;databaseName=DataAnalysis;integratedSecurity=true");
            Statement statement = conn.createStatement();

            br = new BufferedReader(new FileReader("E:\\2016 winter data\\Soil\\Data for research\\med1000.csv"));
            br.readLine();
            plantLine = br.readLine();
            
             
            while ((countCurrentLine = br.readLine()) != null) {
                String sampleID = countCurrentLine.split(",")[0];
                String barcodeSequence = countCurrentLine.split(",")[1];
                String linkerPrimerSequence = countCurrentLine.split(",")[2];
                String tissue_date = countCurrentLine.split(",")[3];
                String time = countCurrentLine.split(",")[4];
                String rna_plate = countCurrentLine.split(",")[5];
                String dna_plate = countCurrentLine.split(",")[6];
                String row = countCurrentLine.split(",")[7];
                String col = countCurrentLine.split(",")[8];
                String plate_row = countCurrentLine.split(",")[9];
                String plate_col = countCurrentLine.split(",")[10];
                String collector = countCurrentLine.split(",")[11];
                String date = countCurrentLine.split(",")[12];
                String subpop = countCurrentLine.split(",")[13];
                String raw_depth = countCurrentLine.split(",")[14];
                String med_depth = countCurrentLine.split(",")[15];
                String mazz_name = countCurrentLine.split(",")[16];
                                
                
                String Query = "insert into Sample_info ( [sampleID],[barcodeSequence],[linkerPrimerSequence],[tissue_date],[time] ,[rna_plate],[dna_plate],[row],[col],[plate_row],[plate_col],[collector],[date],[subpop],[raw_depth],[med_depth],[mazz_name] )"
                                                    + " values('"+sampleID+"','"+barcodeSequence+"','"+linkerPrimerSequence+"','"+tissue_date+"','"+time+"','"+rna_plate+"','"+dna_plate+"','"+row+"','"+col+"','"+plate_row+"','"+plate_col+"','"+collector+"','"+date+"','"+subpop+"','"+raw_depth+"','"+med_depth+"','"+mazz_name+"')";
                statement.executeUpdate(Query); 
                counter++;
                                
            }
            System.out.println(counter + " Rows Data Inserted");

        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.err.println("Problem Connecting!  " + e);
        }

    }
    
}
