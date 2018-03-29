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
public class Save_CSV {
    

    public static void main(String[] args) {
       
    BufferedReader br ;   
    BufferedWriter bw ;   
    String countCurrentLine;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\voghoei\\Desktop\\Sample_data\\MyData.csv"));
            bw = new BufferedWriter(new FileWriter("C:\\Users\\voghoei\\Desktop\\Sample_data\\MyData1.csv"));
                         
            while ((countCurrentLine = br.readLine()) != null) {
                countCurrentLine = countCurrentLine.replaceAll("\t", ",");
                bw.write(countCurrentLine);
                bw.newLine();
            }   
            bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Save_CSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Save_CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
