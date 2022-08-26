package model.persistence;

import model.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @BeforeEach
    void setUp() {
        System.out.println("TESTINNNNGGGG");
        Connection con = null;
        try {
            con = DBconnector.connection();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `fname` VARCHAR(45) NULL,\n" +
                    "  `lname` VARCHAR(45) NULL,\n" +
                    "  `pw` VARCHAR(45) NULL,\n" +
                    "  `phone` VARCHAR(45) NULL,\n" +
                    "  `address` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));";

            con.prepareStatement(createTable).executeUpdate();
            String SQL = "INSERT INTO startcode_test.usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Hans");
            ps.setString(2, "Hansen");
            ps.setString(3, "Hemmelig123");
            ps.setString(4, "40404040");
            ps.setString(5, "Rolighedsvej 3");
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() throws SQLException, ClassNotFoundException {
        Connection con = DBconnector.connection();
        con.prepareStatement("DELETE FROM `startcode_test`.`usertable`").executeUpdate();
    }

    @Test
    void testConnection() throws SQLException, ClassNotFoundException {
        Connection con = DBconnector.connection();
        assertNotNull(con);
    }

    @Test
    void userList() throws SQLException {
        ArrayList<String> actual = new UserMapper().retrieveAllUserNames();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Hans Hansen");
        assertEquals(expected, actual);
    }

    @Test
    void getUser() throws SQLException {
        UserMapper um = new UserMapper();
        User actual = um.retrieveUser("Hans Hansen");
        User expected = new User("Hans", "Hansen","Hemmelig123",40404040,"Rolighedsvej 3");

        assertEquals(expected, actual);
    }
}