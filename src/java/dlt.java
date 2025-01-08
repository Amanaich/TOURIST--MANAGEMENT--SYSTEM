import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class dlt extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("t1");

        try (PrintWriter out = response.getWriter()) {
            // Load JDBC driver
            Class.forName("com.mysql.jdbc.Driver"); // Use the appropriate driver for your MySQL version
            
            // Establish connection
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
                // Prepare the SQL statement
                PreparedStatement ps = con.prepareStatement("DELETE FROM dataa WHERE email = ?");
                ps.setString(1, email);
                int result = ps.executeUpdate();

                if (result > 0) {
                    out.println("Account deletion successful.");
                } else {
                    out.println("Failed to delete account. Email may not exist.");
                }
            } catch (SQLException e) {
                out.println("Database error: " + e.getMessage());
            }
        }  catch(Exception e){
            out.println(e);
        }
        out.close();
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
