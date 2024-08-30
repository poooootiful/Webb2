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
        String StudentIdSql = "SELECT id from students WHERE Fname = \""+FName+"\" AND Lname = \""+LName+"\";";
        int studentid = 0;

        //Make a veriable for printing out html
        PrintWriter out = response.getWriter();


        try {
            Connection con = DatabaseConnect.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(StudentIdSql);

            while (resultSet.next()) {
                studentid = Functions.getCoursesId(resultSet.getInt("id"));
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Integer> courseIds = new ArrayList<>();
        String courseIdsSql = "SELECT * FROM atendance WHERE StudentID = "+studentid+";";
        try {
            Connection con = DatabaseConnect.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(courseIdsSql);

            while (resultSet.next()) {
                int courseid = resultSet.getInt("CourseID");
                courseIds.add(courseid);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int length = courseIds.size();

        for (int i=0; length>i ;i++) {
            int courseId = courseIds.get(i);
            courseIds.remove(1);
            String courseSql = "SELECT * FROM courses WHERE id = "+courseId+";";

            out.println("<table style = \"border: 1px solid\">\n" +
                    "  <tr>\n" +
                    "    <th style = \"border: 1px solid\">Id</th>\n" +
                    "    <th style = \"border: 1px solid\">Name</th>\n" +
                    "    <th style = \"border: 1px solid\">Yhp</th>\n" +
                    "    <th style = \"border: 1px solid\">Description</th>\n" +
                    "  </tr>\n");

            try {
                Connection con = DatabaseConnect.getConnection();
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(courseSql);

                while (resultSet.next()) {
                    out.println(("  <tr>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getInt("id") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("Name") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("Yhp") + "</td>\n" +
                            "    <td style = \"border: 1px solid\">" + resultSet.getString("Description") + "</td>\n" +
                            "  </tr>\n"));
                }
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }



    }
}