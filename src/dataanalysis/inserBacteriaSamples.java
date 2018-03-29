package dataanalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author voghoei
 */
public class inserBacteriaSamples {

    public static void main(String[] args) {
        String plantLine = "";
        String countCurrentLine;
        BufferedReader br;
        BufferedWriter bw;
        ResultSet rs;
        int Nolines = 450641;
        int NoCol = 0;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bacteria", "root", "root");
            Statement statement = con.createStatement();

            br = new BufferedReader(new FileReader("E:\\2016 winter data\\Data for research\\qiime1.csv"));
            bw = new BufferedWriter(new FileWriter("E:\\2016 winter data\\Data for research\\qiime1_reverse.csv"));
            br.readLine();
            plantLine = br.readLine();
            NoCol = plantLine.split(",").length;
            String[][] table = new String[Nolines][NoCol];
            table[0][0] = "#OTU ID";

            for (int i = 1; i < NoCol; i++) {
                String Query = "insert into sample(name) values('" + plantLine.split(",")[i] + "')";
                statement.executeUpdate(Query);
                table[0][i] = plantLine.split(",")[i];
            }

            System.out.println(" end sample, start bacteria");
            for (int i = 1; i < Nolines; i++) {
                countCurrentLine = br.readLine();
                String bacteria = countCurrentLine.split(",")[0];
                table[i][0] = bacteria;
                String Query = "insert into bacteria(name) values('" + bacteria + "')";
                statement.executeUpdate(Query);
                rs = statement.executeQuery("select max(id) from bacteria");
                rs.next();
                int Bacteria_id = rs.getInt(1);
                for (int j = 1; j < NoCol; j++) {
                    Query = "select id from sample where name='" + plantLine.split(",")[j] + "'";
                    rs = statement.executeQuery(Query);
                    rs.next();
                    int Sample_id = rs.getInt(1);

                    String No = countCurrentLine.split(",")[j];
                    table[i][j] = countCurrentLine.split(",")[j];
                    if (Integer.parseInt(No) > 0) {
                        Query = "insert into sample_bacteria(sample_id,bacteria_id,no) values(" + Sample_id + "," + Bacteria_id + "," + No + ")";
                        statement.executeUpdate(Query);
                    }
                }
            }

            System.out.println(" Start File write");
            for (int j = 0; j < NoCol; j++) {
                for (int i = 0; i < Nolines; i++) {
                    bw.write(table[i][j]);
                    bw.write(",");
                }
                bw.newLine();
            }
            con.close();
            br.close();
            bw.close();
            System.out.println(" Done");
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
