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
import registration.RegistrationCreateError;
import registration.RegistrationDAO;
import registration.RegistrationDTO;
import utils.ApplicationConstant;

/**
 *
 * @author beu29
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String firstname = request.getParameter("txtFirstname");
        String middlename = request.getParameter("txtMiddlename");
        String lastname = request.getParameter("txtLastname");
        boolean foundErr = false;
        RegistrationCreateError errors = new RegistrationCreateError();

        ServletContext context = this.getServletContext();
        Properties properties = (Properties) context.getAttribute("SITE_MAP");

        String url = properties.getProperty(
                ApplicationConstant.SignUpFeatures.SIGN_UP_JSP);

        try {
            //1. Check user constraint validation
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthViolent(properties.getProperty(
                        ApplicationConstant.SignUpFeatures.USERNAME_LENGTH_VIOLENT_NOTICE));
            }
            if (!password.trim().matches(properties.getProperty(
                    ApplicationConstant.SignUpFeatures.PASSWORD_REGEX))) {
                foundErr = true;
                errors.setPasswordViolent(properties.getProperty(
                        ApplicationConstant.SignUpFeatures.PASSWORD_VIOLENT_NOTICE));
            } else if (!password.trim().equals(confirm.trim())) {
                foundErr = true;
                errors.setConfirmNotMatch(properties.getProperty(
                        ApplicationConstant.SignUpFeatures.CONFIRM_NOTMATCH_NOTICE));
            }
            if (firstname.trim().length() < 2 || firstname.trim().length() > 20) {
                foundErr = true;
                errors.setFirstNameLengthViolent(properties.getProperty(
                        ApplicationConstant.SignUpFeatures.FIRSTNAME_LENGTH_VIOLENT_NOTICE));
            }
            if (middlename.trim().length() < 0 || middlename.trim().length() > 20) {
                foundErr = true;
                errors.setMiddleNameLengthViolent(properties.getProperty(
                        ApplicationConstant.SignUpFeatures.MIDDLENAME_LENGTH_VIOLENT_NOTICE));
            }
            if (lastname.trim().length() < 2 || lastname.trim().length() > 20) {
                foundErr = true;
                errors.setLastNameLengthViolent(properties.getProperty(
                        ApplicationConstant.SignUpFeatures.LASTNAME_LENGTH_VIOLENT_NOTICE));
            }
            //1.1 If so, notify to user correct them
            if (foundErr) {
                request.setAttribute("SIGNUPERRS", errors);
                return;
            } //en if Errors occur
            //1.2 If no error occurs, call DAO to insert to DB
            RegistrationDAO dao = new RegistrationDAO();
            username = username.trim();
            password = password.trim();
            RegistrationDTO dto
                    = new RegistrationDTO(username, password, firstname, middlename, lastname, false);
            boolean result = dao.createNewAcccount(dto);
            //2. If task is success, redirect to Login Page
            if (result) {
                url = properties.getProperty(
                        ApplicationConstant.SignUpFeatures.LOGIN_PAGE);
            } //end if insert is success
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("SignUpServlet_SQL: " + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("SIGNUPERRS", errors);
            }//end process username is existed in DB
        } catch (NamingException ex) {
            log("SignUpServlet_Naming: " + ex.getMessage());
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
            Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
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
