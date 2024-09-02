package Models;

import javax.xml.stream.events.StartDocument;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Functions {
    public static int getCoursesId (int studentId) {
        String sql ="SELECT CourseID FROM atendance WHERE StudentID = "+studentId+";";
        Connection connection = DatabaseConnect.getConnection();
        int id = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                id = resultSet.getInt("CourseID");
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    public static int getStudentId (String fName, String lName) {
        String StudentIdSql = "SELECT id from students WHERE Fname = \""+fName+"\" AND Lname = \""+lName+"\";";
        int studentId = 0;
        Connection connection = DatabaseConnect.getConnection();


        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(StudentIdSql);
            while (resultSet.next()) {
                studentId = resultSet.getInt("id");
            }
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        };

        return studentId;

    }



}
