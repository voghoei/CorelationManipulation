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
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author voghoei
 */
public class ReverseFile {

    public static void main(String[] args) {
        String plantLine = "";
        String countCurrentLine;
        BufferedReader br;
        BufferedWriter bw;
        ResultSet rs;
        int counter = 0;
        int line = 0;
        int Nolines = 141;
        int NoCol = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; instance= DESKTOP-8SM3HF1\\SQLEXPRESS;databaseName=DataAnalysis;integratedSecurity=true");
            Statement statement = conn.createStatement();

            br = new BufferedReader(new FileReader("E:\\2016 winter data\\Data for research\\qiime1.csv"));
            bw = new BufferedWriter(new FileWriter("E:\\2016 winter data\\Data for research\\qiime1_reverse.csv"));
            br.readLine();
            countCurrentLine = br.readLine();
            NoCol = countCurrentLine.split(",").length;
            String[][] table = new String[Nolines][NoCol];

            for (int i = 0; i < Nolines; i++) {
                for (int j = 0; j < NoCol; j++) {
                    table[i][j] = countCurrentLine.split(",")[j];
                }
                line++;
                countCurrentLine = br.readLine();
                
            }
            System.out.println("line=  "+line);
            
            line=0;
            for (int j = 0; j < NoCol; j++) {
                for (int i = 0; i < Nolines; i++) {
                    bw.write(table[i][j]);   
                    bw.write(",");
                }
                bw.newLine();
                line++;
            }
            System.out.println("Count=  "+line);
            br.close();
            bw.close();
            System.out.println(counter + " Rows Data Inserted");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.err.println("Problem Connecting!  " + e);
        }

    }

    public static String toString(String[][] myBoard) {
        String result = "";
        for (int i = 0; i < myBoard.length; i++) {
            for (int j = 0; j < myBoard[i].length; j++) {
                result += myBoard[i][j];
            }            
            result += "\n";
        }
        return result;
    }

}
