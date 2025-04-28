/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registration.RegistrationDAO;
import registration.RegistrationDTO;
import utils.ApplicationConstant;

/**
 *
 * @author beu29
 */
@WebServlet(name = "ConfirmDeleteServlet", urlPatterns = {"/ConfirmDeleteServlet"})
public class ConfirmDeleteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("pk");
        String lastSearchValue = request.getParameter("lastSearchValue");
        
        ServletContext context = this.getServletContext();
        Properties properties = (Properties)context.getAttribute("SITE_MAP");
        
        String url = properties.getProperty(
                        ApplicationConstant.ConfirmDeleteFetures.ERROR_PAGE);
        
        try {
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO dto = dao.showProfile(username);
            
            HttpSession session = request.getSession(false);
            
            if (session == null) {
                url = properties.getProperty(
                        ApplicationConstant.ConfirmDeleteFetures.LOGIN_PAGE);
                return;
            }
            
            session.setAttribute("SHOW_PROFILE", dto);
            url = properties.getProperty(
                    ApplicationConstant.ConfirmDeleteFetures.CONFIRM_DELETE_PAGE);
            
        } catch (SQLException ex) {
            log("ConfirmDeleteServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ConfirmDeleteServlet_Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ConfirmDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ConfirmDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
