
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author voghoei
 */
public class AlignColumn {

    public static void main(String[] args) {
       
    BufferedReader br ;   
    BufferedWriter bw ;   
    String currentLine;   
      
        try {
            br = new BufferedReader(new FileReader("D:\\Users\\voghoei\\VMShare\\mitochondria.txt"));
            bw = new BufferedWriter(new FileWriter("D:\\Users\\voghoei\\VMShare\\mitochondria.csv"));
            //ID, K , P, C, O, F, G, S , value, value            
            while ((currentLine = br.readLine()) != null) {
                
                
                String[] column = currentLine.split("\t");
                String[] categories = column[1].split(";");
                String[] arrlist = {"NA","NA","NA","NA","NA","NA","NA"};
                
                for(int i=0;i < categories.length;i++ ){
                    if( categories[i].trim().length()>3){
                        String common = categories[i].trim().substring(0,3);
                        switch (common.trim().toLowerCase()){
                            case "k__":  arrlist[0] =categories[i];
                                         break;
                            case "p__":  arrlist[1] =categories[i];
                                         break;
                            case "c__":  arrlist[2] =categories[i];
                                         break;
                            case "o__":  arrlist[3] =categories[i];
                                         break;
                            case "f__":  arrlist[4] =categories[i];
                                         break;
                            case "g__":  arrlist[5] =categories[i];
                                         break;
                            case "s__":  arrlist[6] =categories[i];
                                         break;                       
                        }
                    }
                }
                String NewLine=column[0]+",";
                
                for(int i=0;i < 7;i++ ){
                    NewLine+= ( arrlist[i]+", " );
                } 
                NewLine+=column[2]+",";
                NewLine+=column[3];
                bw.write(NewLine +"\n");
            }   
            
                
            bw.close();
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
           
        }
    }    
    
}
