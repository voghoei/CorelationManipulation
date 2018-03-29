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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author voghoei
 */
public class BacteriaNoSelection {

    public static void main(String[] args) {
        String combinationLine;
        BufferedReader br;
        BufferedWriter bw;
        ResultSet rs = null;
        try {
            br = new BufferedReader(new FileReader("E:\\2016 winter data\\Data for research\\corelation_sample_Strong.csv"));
            bw = new BufferedWriter(new FileWriter("E:\\2016 winter data\\Data for research\\corelation_sample_Strong_result.csv"));

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bacteria", "root", "root");
            Statement statement = con.createStatement();

            while ((combinationLine = br.readLine()) != null) {
                String[] combinationTable = combinationLine.split(",");
                for (int i = 1; i <= 7; i++) {
                    int r = i;
                    int n = combinationTable.length;
                    printCombination(combinationTable, n, r, bw, con, statement, rs);
                    bw.newLine();
                }
            }
            br.close();
            bw.close();
            con.close();
            System.out.println(" Done");

        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(BacteriaNoSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String toString(String[] myBoard) {
        String result = "";
        for (int i = 0; i < myBoard.length; i++) {
            result += myBoard[i];
            result += ",";
        }
        return result;
    }

    static void combinationUtil(String arr[], String data[], int start, int end, int index, int r, BufferedWriter bw, Connection con, Statement statement, ResultSet rs) {
        try {

            if (index == r) {
                String Call = "call CorelationStat(";
                for (int j = 0; j < r; j++) {
                    System.out.print(data[j] + " ");
                    Call = Call + data[j] + ",";

                    bw.write(data[j] + ",");

                }
                for (int k = 1; k <= (7 - r); k++) {
                    Call += " '',";
                    bw.write(" ,");
                }
                Call = Call + r + ")";

                System.out.println("");
                System.out.println(Call);

                rs = statement.executeQuery(Call);
                rs.next();
                bw.write(rs.getString(1) + ",");
                bw.write(rs.getInt(2) + ",");

                bw.newLine();
                return;
            }
            for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
                data[index] = arr[i];
                combinationUtil(arr, data, i + 1, end, index + 1, r, bw, con, statement, rs);
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(BacteriaNoSelection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static void printCombination(String arr[], int n, int r, BufferedWriter bw, Connection con, Statement statement, ResultSet rs) {
        String data[] = new String[r];
        combinationUtil(arr, data, 0, n - 1, 0, r, bw, con, statement, rs);
    }
}
