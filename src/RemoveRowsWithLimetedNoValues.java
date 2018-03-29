
import dataanalysis.Save_CSV;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class RemoveRowsWithLimetedNoValues {
    public static void main(String[] args) {

        BufferedReader br;
        BufferedWriter bw;
        String currentLine;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\voghoei\\Desktop\\Sample_data\\tqiime.csv"));
            bw = new BufferedWriter(new FileWriter("C:\\Users\\voghoei\\Desktop\\Sample_data\\tqiimepLimited.csv"));
            currentLine = br.readLine();
            bw.write(currentLine);
            bw.newLine();
            while ((currentLine = br.readLine()) != null) {
                String[] Line = currentLine.split(",");
                String NewLine = Line[0] + ",";
                
                int Counter=0;
                for (int i = 1; i < Line.length; i++) {

                    if (Line[i].contains("NA")) {
                        Line[i] = "0";
                    }
                    if (Double.parseDouble(Line[i]) < 0.8) {
                        Line[i] = "0";
                    }
                   
                    NewLine = NewLine + Line[i] + ",";
                    if (!Line[i].equals("0"))
                        Counter++;
                }
                
                if (Counter > 10){
                    bw.write(NewLine);
                    bw.newLine();
                }
            }
            bw.close();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Save_CSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Save_CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
