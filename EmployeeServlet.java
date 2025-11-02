import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empIdParam = request.getParameter("empid");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company", "root", "yourpassword");
            stmt = con.createStatement();

            String query;
            if (empIdParam != null && !empIdParam.isEmpty()) {
                query = "SELECT * FROM employee WHERE EmpID = " + empIdParam;
            } else {
                query = "SELECT * FROM employee";
            }

            rs = stmt.executeQuery(query);
            out.println("<h2>Employee Records</h2>");
            out.println("<form method='get' action='EmployeeServlet'>");
            out.println("Search by ID: <input type='text' name='empid'>");
            out.println("<input type='submit' value='Search'></form><br>");
            out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");

            boolean found = false;
            while (rs.next()) {
                found = true;
                out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" +
                            rs.getString("Name") + "</td><td>" +
                            rs.getDouble("Salary") + "</td></tr>");
            }
            out.println("</table>");

            if (!found) out.println("<p>No matching record found.</p>");

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    }
}