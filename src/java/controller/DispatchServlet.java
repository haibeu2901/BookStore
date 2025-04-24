/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ApplicationConstant;

/**
 *
 * @author beu29
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        
        //which buttin did user clicked?
        String button = request.getParameter("btAction");
        
        ServletContext context = this.getServletContext();
        Properties properties = (Properties)context.getAttribute("SITE_MAP");
        
        String url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.LOGIN_PAGE);
        try {
            if (button == null) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.STARTUP_APP_CONTROLLER);
            } 
            
            
            else if (button.equals("Login")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.LOGIN_CONTROLLER);
            } else if (button.equals("Logout")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.LOGOUT_CONTROLLER);
            } else if (button.equals("Sign Up")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.SIGN_UP_CONTROLLER);
            }
            
            
            else if (button.equals("Search")){
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.SEARCH_FULLNAME_CONTROLLER);
            } else if (button.equals("Delete")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.CONFIRM_DELETE_CONTROLLER);
            } else if (button.equals("Confirm")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.DELETE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Update")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.UPDATE_ACCOUNT_CONTROLLER);
            } 
            
            
            else if (button.equals("Buy")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.BUY_BOOK_CONTROLLER);
            } else if (button.equals("Add Book to Your Cart")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.ADD_BOOK_TO_CART_CONTROLLER);
            } else if (button.equals("View Your Cart")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.VIEW_CART_PAGE);
            }   else if (button.equals("Remove Selected Books")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.REMOVE_BOOK_FROM_CART);
            } else if (button.equals("Check Out Selected Books")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.CONFIRM_CHECK_OUT_CONTROLLER);
            } else if (button.equals("Check Out")) {
                url = properties.getProperty(
                        ApplicationConstant.DispatchFeatures.CHECK_OUT_ORDER_CONTROLLER);
            } //end of if button is click 
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
