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
    public static List<String> getCourseInfo (int courseId) {
        List<String> CourseInfo = new ArrayList<>();

        String sql = "SELECT * FROM courses WHERE id = "+courseId+";";
        Connection connection = DatabaseConnect.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            CourseInfo.add(resultSet.getString("Name"));
            CourseInfo.add(resultSet.getString("Yhp"));
            CourseInfo.add(resultSet.getString("Description"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return CourseInfo;
    }



}
