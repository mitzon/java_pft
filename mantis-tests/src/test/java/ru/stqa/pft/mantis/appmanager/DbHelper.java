package ru.stqa.pft.mantis.appmanager;

import java.sql.*;
import java.util.ArrayList;

public class DbHelper {

    protected ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        this.app = app;
    }

    public ArrayList<String> returnUserLoginAndPassword() {
        Connection conn = null;
        ArrayList<String> userInfo = new ArrayList<>();
        String username = null;
        String email = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" +
                    "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select username, email from mantis_user_table where enabled = 1 and access_level = 25 limit 1;");
            while (rs.next()) {
                username = rs.getString("username");
                userInfo.add(username);
                email = rs.getString("email");
                userInfo.add(email);
            }
            rs.close();
            st.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Ex: " + ex.getMessage());
        }
        return userInfo;
    }
}
