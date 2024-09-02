package Servlets;

import Models.DatabaseConnect;
import Models.Functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/Students")
public class Students extends HttpServlet {

    //The Sql line used to get info
    String ShowStudents = "SELECT*FROM students";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //Connect to database
        Connection con = DatabaseConnect.getConnection();

        //Make a veriable for printing out html
        PrintWriter out = response.getWriter();

        //Html
        out.println("<html>");
        out.println("<head><title>Students</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>Students</h2>");
        out.println("<ul>\n" +
                "        <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Students\">Students</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddStudent\">AddStudent</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddCourse\">AddCourse</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Atendance\">Atendance</a></li>"+
                "    </ul>");
        out.println("<table style = \"border: 1px solid\">\n" +
                "  <tr>\n" +
                "    <th style = \"border: 1px solid\">Id</th>\n" +
                "    <th style = \"border: 1px solid\">First Name</th>\n" +
                "    <th style = \"border: 1px solid\">Last Name</th>\n" +
                "    <th style = \"border: 1px solid\">Origin</th>\n" +
                "    <th style = \"border: 1px solid\">Hobby</th>\n" +
                "  </tr>\n");
        //Get info from database and put it in the table
        try {
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
        out.println("<form name=\"StudentSearch\" method=\"post\" action=\"Students\">\n" +
                "        First Name: <input type=\"text\" name=\"Fname\">\n" +
                "        Last Name: <input type=\"text\" name=\"Lname\">\n" +
                "        <input type=\"submit\" value=\"Search\">\n" +
                "    </form>");

        //Closing tags for html
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String FName = req.getParameter("Fname");
        String LName = req.getParameter("Lname");

        int studentId = Functions.getStudentId(FName,LName);


        //Make a veriable for printing out html
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Students</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>Students</h2>");
        out.println("<ul>\n" +
                "        <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Students\">Students</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddStudent\">AddStudent</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddCourse\">AddCourse</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Atendance\">Atendance</a></li>"+
                "    </ul>");

        if (studentId>0) {
            out.println("<h3>All Courses for the student you searched</h3>");
            String sql = "SELECT * FROM atendance WHERE StudentID = "+studentId+";";
            Connection connection = DatabaseConnect.getConnection();

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                out.println("<table style = \"border: 1px solid\">\n" +
                        "  <tr>\n" +
                        "    <th style = \"border: 1px solid\">CourseID</th>\n" +
                        "    <th style = \"border: 1px solid\">StudentID</th>\n" +
                        "  </tr>\n");

                while (resultSet.next()) {
                    out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("CourseID") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("StudentID") + "</td>\n" +
                            "  </tr>\n"));
                }
                connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //Closing tags for table
            out.println("</table>");

        }
        if (studentId>0) {
            out.println("<h3>All Courses with id and name</h3>");
            String CoursesSql = "SELECT id, Name FROM courses;";
            Connection connection = DatabaseConnect.getConnection();
            out.println("<table style = \"border: 1px solid\">\n" +
                    "  <tr>\n" +
                    "    <th style = \"border: 1px solid\">CourseID</th>\n" +
                    "    <th style = \"border: 1px solid\">Name</th>\n" +
                    "  </tr>\n");

            try {
                Statement statement = connection.createStatement();
                ResultSet courseResult = statement.executeQuery(CoursesSql);


                while (courseResult.next()) {
                    out.println(("  <tr>\n" +
                                "    <td style = \"border: 1px solid\">" + courseResult.getInt("id") + "</td>\n" +
                                "    <td style = \"border: 1px solid\">" + courseResult.getString("Name") + "</td>\n" +
                                "  </tr>\n"));

                }
                connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //Closing tags for table
            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");


    }
}