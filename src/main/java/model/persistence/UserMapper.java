package model.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserMapper {
    Connection con;

    public UserMapper() {
        try {
            this.con = DBconnector.connection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> retrieveAllUserNames() throws SQLException{
        ArrayList<String> names = new ArrayList<>();

        String sql = "SELECT `fname`, `lname` FROM `startcode_test`.`usertable`";

        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            String fName = rs.getString("fname");
            String lName = rs.getString("lname");
            String name = fName+" "+lName;
            names.add(name);
        }

        return names;
    }
}
