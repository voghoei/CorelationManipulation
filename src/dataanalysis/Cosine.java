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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

/**
 *
 * @author voghoei
 */
public class Cosine {
     public static void main(String[] args) throws FileNotFoundException, IOException {
        String countCurrentLine="";
        int Nolines = 3504;
        String firstlLine="";
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\voghoei\\Desktop\\Sample_data\\distance.csv"));
        
        for (int firstlineCounter = 1; firstlineCounter < Nolines; firstlineCounter++) {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\voghoei\\Desktop\\Sample_data\\9b_qiime.filt1000.norm.tsv.csv"));
            
            for (int passline = 1; passline <= firstlineCounter; passline++){
                br.readLine(); 
            }
            firstlLine= br.readLine();
            String BName1 = firstlLine.substring(0,firstlLine.indexOf(","));
            firstlLine = firstlLine.substring(firstlLine.indexOf(",")+1,firstlLine.length());
            List<Double> Bacteria1 = Stream.of(firstlLine.split(",")).map(Double::parseDouble).collect(Collectors.toList());
            int secondLineCounter =firstlineCounter +1; 
            while ((countCurrentLine = br.readLine()) != null) {
                String BName2 = countCurrentLine.substring(0,countCurrentLine.indexOf(","));
                countCurrentLine = countCurrentLine.substring(countCurrentLine.indexOf(",")+1,countCurrentLine.length());
                List<Double> Bacteria2 = Stream.of(countCurrentLine.split(",")).map(Double::parseDouble).collect(Collectors.toList());
                
                List<Double> Bacteria1Temp = new ArrayList<Double>(Bacteria1);
                                
                for(int i = 0 ; i<Bacteria2.size(); i++){
                    if (Bacteria1Temp.get(i)== 0.0 && Bacteria2.get(i)== 0.0){
                        Bacteria1Temp.remove(i);
                        Bacteria2.remove(i);
                        i--;
                    }
                }
                
                double[] B1= new double[Bacteria1Temp.size()];
                double[] B2= new double[Bacteria2.size()];
                for(int i = 0 ; i<Bacteria2.size(); i++){
                    B1[i] = Bacteria1Temp.get(i);
                    B2[i] = Bacteria2.get(i);
                    
                }             
                
                double cosineSum=0;              
                for(int i = 0 ; i<Bacteria2.size(); i++){
                    //distance
                    cosineSum +=Math.abs(B1[i] -B2[i]);       
                    //cosine
//                   cosineSum += Math.cos(Math.abs(B1[i] -B2[i]));                    
                } 
                
                if(cosineSum>5000){
                    bw.write(BName1 +" , "+ cosineSum +" , "+BName2);
                    bw.newLine();
                }
                
                
            }  
            br.close();            
        }
        bw.close();
        System.out.println("Done!!!!!");
        
        
    }
    
}
