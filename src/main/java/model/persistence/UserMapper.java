package model.persistence;

import model.entities.User;

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

    public ArrayList<String> retrieveAllUserNames() throws SQLException {
        ArrayList<String> names = new ArrayList<>();

        String sql = "SELECT `fname`, `lname` FROM `startcode_test`.`usertable`";

        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            String fName = rs.getString("fname");
            String lName = rs.getString("lname");
            String name = fName + " " + lName;
            names.add(name);
        }

        return names;
    }

    public User retrieveUser(String name) throws SQLException {
        String[] fullName = name.split(" ");

        String sql = "SELECT * FROM startcode_test.usertable WHERE fname = '" + fullName[0] + "' AND lname = '" + fullName[1] + "'";
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        rs.next();
        String fName = rs.getString("fname");
        String lName = rs.getString("lname");
        String pw = rs.getString("pw");
        int phone = Integer.parseInt(rs.getString("phone"));
        String address = rs.getString("address");

        return new User(fName,lName,pw,phone,address);
    }
}
