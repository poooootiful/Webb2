package Servlets;

import Models.DatabaseConnect;
import Models.Functions;
import com.sun.org.apache.xpath.internal.operations.Or;

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

@WebServlet(urlPatterns = "/AddStudent")
public class AddStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>AddStudent</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>AddStudent</h2>");
        out.println("<ul>\n" +
                "        <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Students\">Students</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddStudent\">AddStudent</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddCourse\">AddCourse</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Atendance\">Atendance</a></li>"+
                "    </ul>");
        out.println("<form name =\"AddStudent\" method=\"post\">\n" +
                "        Fist Name: <input type=\"text\" name=\"Fname\"><br>\n" +
                "        Last Name: <input type=\"text\" name=\"Lname\"><br>\n" +
                "        Origin: <input type=\"text\" name=\"Origin\"><br>\n" +
                "        Hobby: <input type=\"text\" name=\"Hobby\"><br>\n" +
                "        <input type=\"submit\" value=\"submit\">\n" +
                "    </form>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String FName = req.getParameter("Fname");
        String LName = req.getParameter("Lname");
        String Origin = req.getParameter("Origin");
        String Hobby = req.getParameter("Hobby");

        String sql = "INSERT INTO students (Fname, Lname, Origin, Hobby)\n" +
                "VALUES ('"+FName+"','"+LName+"','"+ Origin +"','"+Hobby+"');";
        Connection connection = DatabaseConnect.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();

        out.println("<html>\n" +
                "    <head>\n" +
                "        <title>Student Added</title>\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "    <h1>Student Added</h1>\n" +
                "    <ul>\n" +
                "        <li style=\"display:inline\"><a href=\"/Home\"></a>Home</li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Students\">Students</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddStudent\">AddStudent</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddCourse\">AddCourse</a></li>\n" +
                "    </ul>\n" +
                "    </body>\n" +
                "</html>\n");

    }
}
