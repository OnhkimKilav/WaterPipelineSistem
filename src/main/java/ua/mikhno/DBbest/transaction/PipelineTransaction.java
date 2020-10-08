package ua.mikhno.DBbest.transaction;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PipelineTransaction {
    public void transaction() {

        String url = "jdbc:h2:~/test";
        String user = "sa";
        String passwd = "";

        String queryClean = "TRUNCATE TABLE PIPELINE";
        String query = "INSERT INTO PIPELINE SELECT * FROM CSVREAD('src/main/Pipeline.csv')";

        try (Connection con = DriverManager.getConnection(url, user, passwd);
             Statement st = con.createStatement()) {
            st.executeUpdate(queryClean);
            st.executeUpdate(query);
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(PipelineTransaction.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
