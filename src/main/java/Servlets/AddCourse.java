package Servlets;

import Models.DatabaseConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/AddCourse")
public class AddCourse  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>AddCourse</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>AddCourse</h2>");
        out.println("<ul>\n" +
                "        <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Students\">Students</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddStudent\">AddStudent</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddCourse\">AddCourse</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Atendance\">Atendance</a></li>"+
                "    </ul>");
        out.println("<form name =\"AddStudent\" method=\"post\">\n" +
                "        Name: <input type=\"text\" name=\"Name\"><br>\n" +
                "        Yhp: <input type=\"text\" name=\"Yhp\"><br>\n" +
                "        Description: <input type=\"text\" name=\"Description\"><br>\n" +
                "        <input type=\"submit\" value=\"submit\">\n" +
                "    </form>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String Name = req.getParameter("Name");
        String Yhp = req.getParameter("Yhp");
        String Description = req.getParameter("Description");

        String sql = "INSERT INTO courses (Name, Yhp, Description)\n" +
                "VALUES ('"+Name+"',"+Yhp+",'"+Description+"');";
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
                "        <title>Course Added</title>\n" +
                "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "    <h1>Course Added</h1>\n" +
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
