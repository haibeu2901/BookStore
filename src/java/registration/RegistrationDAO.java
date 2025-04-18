/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.io.Serializable;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author beu29
 */
public class RegistrationDAO implements Serializable {

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. make connection to DB
            con = DBUtils.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? And password = ?";
                //3. create statement object to load SQL String
                //and set value to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    return true;
                }
            }//end if con is opened
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void searchFullName(String keyword)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. make connection to DB
            con = DBUtils.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Select username, password, LastName + ' ' + MiddleName + ' ' + FirstName AS [FullName], isAdmin "
                        + "From Registration "
                        + "Where (LastName + ' ' + MiddleName + ' ' + FirstName) Like ? ";
                //3. create statement object to load SQL String
                //and set value to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("FullName");
//                    String middlename = rs.getString("MiddleName");
//                    String firstname = rs.getString("FirstName");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);

                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    } //end if allocate memory to account list
                    this.accountList.add(dto);
                }
            }//end if con is opened
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. make connection to DB
            con = DBUtils.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. create statement object to load SQL String
                //and set value to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. execute query
                int affectedRow = stm.executeUpdate();
                //5. process result
                if (affectedRow > 0) {
                    return true;
                }
            }//end if con is opened
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAccount(String username, String password, boolean isAdmmin)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmmin);
                stm.setString(3, username);

                int affectedRow = stm.executeUpdate();
                if (affectedRow > 0) {
                    return true;
                }
            }//end if con is opened
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public String showFullName(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select LastName + ' ' + MiddleName + ' ' + FirstName AS [Full Name] "
                        + "From Registration "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("Full Name");
                    return fullname;
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean createNewAcccount(RegistrationDTO dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Insert Into Registration("
                        + "username, password, FirstName, MiddleName, LastName"
                        + ") Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setNString(3, dto.getFirstName());
                stm.setNString(4, dto.getMiddleName());
                stm.setNString(5, dto.getLastName());
                int affectedRow = stm.executeUpdate();
                if (affectedRow > 0) {
                    return true;
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean checkAdmin(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select isAdmin "
                        + "From Registration "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    return isAdmin;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public RegistrationDTO showProfile(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select username, password, LastName + ' ' + MiddleName + ' ' + FirstName AS [Full Name], isAdmin "
                        + "From Registration "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getNString("Full Name");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(userName, password, fullname, role);
                    return dto;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();;
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

}
