package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/Home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Home</title></head>");
        out.println("<body style=\"background-color:Gray;\">");
        out.println("<h2>Home</h2>");
        out.println("<ul>\n" +
                "        <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Students\">Students</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddStudent\">AddStudent</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/AddCourse\">AddCourse</a></li>\n" +
                        "<li style=\"display:inline\"><a href=\"/Atendance\">Atendance</a></li>"+
                "    </ul>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Home</title></head>");
        out.println("<body>");
        out.println("<h2>Home</h2>");
        out.println("<ul>\n" +
                "        <li style=\"display:inline\"><a href=\"/Home\">Home</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Students\">Students</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Courses\">Courses</a></li>\n" +
                "        <li style=\"display:inline\"><a href=\"/Attendance\">Attendance</a></li>\n" +
                "    </ul>");
        out.println("</body>");
        out.println("</html>");
    }

}