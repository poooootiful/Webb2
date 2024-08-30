package Servlets;

import Models.DatabaseConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/Atendance")
public class Atendance extends HomeServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Atendance</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>Atendance</h2>");
        out.println("<ul>\n" +
                "        <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Students\">Students</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddStudent\">AddStudent</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddCourse\">AddCourse</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Atendance\">Atendance</a></li>"+
                "    </ul>");

        //Student table
        out.println("<table style = \"border: 1px solid\">\n" +
                "  <tr>\n" +
                "    <th style = \"border: 1px solid\">Id</th>\n" +
                "    <th style = \"border: 1px solid\">First Name</th>\n" +
                "    <th style = \"border: 1px solid\">Last Name</th>\n" +
                "    <th style = \"border: 1px solid\">Origin</th>\n" +
                "    <th style = \"border: 1px solid\">Hobby</th>\n" +
                "  </tr>\n");
        //Get info from database and put it in the table
        String ShowStudents = "SELECT*FROM students";
        try {
            Connection con = DatabaseConnect.getConnection();
            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(ShowStudents);

            while (result.next()) {
                out.println(("  <tr>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getInt("id") + "</td>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getString("Fname") + "</td>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getString("Lname") + "</td>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getString("Origin") + "</td>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getString("Hobby") + "</td>\n" +
                        "  </tr>\n"));
            }
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        out.println("</table>");

        //Course table
        out.println("<table style = \"border: 1px solid\">\n" +
                "  <tr>\n" +
                "    <th style = \"border: 1px solid\">Id</th>\n" +
                "    <th style = \"border: 1px solid\">Name</th>\n" +
                "    <th style = \"border: 1px solid\">Yhp</th>\n" +
                "    <th style = \"border: 1px solid\">Description</th>\n" +
                "  </tr>\n");
        //Get info from database and put it in the table
        String showCourses = "SELECT*FROM courses";
        try {
            Connection con = DatabaseConnect.getConnection();
            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(showCourses);

            while (result.next()) {
                out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getInt("id") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("Name") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("Yhp") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + result.getString("Description") + "</td>\n" +
                            "  </tr>\n"));
            }
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        out.println("</table>");

        //Atendance table
        out.println("<table style = \"border: 1px solid\">\n" +
                "  <tr>\n" +
                "    <th style = \"border: 1px solid\">Id</th>\n" +
                "    <th style = \"border: 1px solid\">CourseID</th>\n" +
                "    <th style = \"border: 1px solid\">StudentID</th>\n" +
                "  </tr>\n");
        //Get info from database and put it in the table
        String showAtendance = "SELECT*FROM atendance";
        try {
            Connection con = DatabaseConnect.getConnection();
            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(showAtendance);

            while (result.next()) {
                out.println(("  <tr>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getInt("id") + "</td>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getInt("CourseID") + "</td>\n" +
                        "    <td style = \"border: 1px solid\">" + result.getInt("StudentID") + "</td>\n" +
                        "  </tr>\n"));
            }
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        out.println("</table>");


        out.println("</body>");
        out.println("</html>");
    }
}
