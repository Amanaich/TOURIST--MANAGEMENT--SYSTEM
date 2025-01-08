import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class contact extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form parameters
         String nm = request.getParameter("t1");
        String mn = request.getParameter("t2");
        String email = request.getParameter("t3");
        
        

        try (PrintWriter out = response.getWriter()) {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Database connection
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
                
                
                String query = "INSERT INTO  data VALUES (?,?,?)";
                try (PreparedStatement ps = con.prepareStatement(query)) {
  
            ps.setString(1, nm);
            ps.setString(2, mn);
            ps.setString(3, email);
          
                    // Execute the query
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        out.println("Registration successful!");
                    } else {
                        out.println("Registration failed.");
                    }
                }
            } catch (SQLException e) {
                out.println("Database error: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to handle user registration";
    }
 

}
