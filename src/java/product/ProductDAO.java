/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void showBookList()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select SKU, Name, Price, Description, Quantity "
                        + "From Product ";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String SKU = rs.getString("SKU");
                    String name = rs.getString("Name");
                    BigDecimal price = rs.getBigDecimal("Price");
                    String description = rs.getString("Description");
                    int quantity = rs.getInt("Quantity");

                    ProductDTO dto
                            = new ProductDTO(SKU, name, price, description, quantity);

                    if (this.productList == null) {
                        this.productList = new ArrayList<>();
                    }
                    this.productList.add(dto);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public ProductDTO getProductBySKU(String SKU) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select Name, Price, Description, Quantity "
                        + "From Product "
                        + "Where SKU = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, SKU);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("Name");
                    BigDecimal price = rs.getBigDecimal("Price");
                    String description = rs.getString("Description");
                    int quantity = rs.getInt("Quantity");
                    
                    ProductDTO result = new ProductDTO(SKU, name, price, description, quantity);
                    return result;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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

}
