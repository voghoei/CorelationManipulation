/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author voghoei
 */
public class LimitCorrelationFile {
    public static void main(String[] args) {
       
    BufferedReader br ;   
    BufferedWriter bw ;   
    String countCurrentLine;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\voghoei\\Desktop\\Sample_data\\q1.csv"));
            bw = new BufferedWriter(new FileWriter("C:\\Users\\voghoei\\Desktop\\Sample_data\\qLimit5 0.9.csv"));
                         
            while ((countCurrentLine = br.readLine()) != null) {
                Double corr = Double.parseDouble(countCurrentLine.split(",")[1]);
                if (corr>0.9){
                   bw.write(countCurrentLine);
                    bw.newLine(); 
                }                
            }   
            br.close();
            bw.close();
            System.out.println("Done !!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Save_CSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Save_CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
