/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author beu29
 */
public class OrderDAO implements Serializable {

    public class OrdersDAO implements Serializable {

        public int createNewOrder(String name, String address, String total)
                throws SQLException, NamingException {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;

            try {
                con = DBHelpers.makeConnection();
                if (con != null) {
                    String sql = "Insert Into Orders(Name, Address, Total) "
                            + "Output inserted.OrderID, inserted.Date "
                            + "Values (?, ?, ?)";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, name);
                    stm.setString(2, address);
                    stm.setBigDecimal(3, new BigDecimal(total));
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        return orderID;
                    }
                } //end if con connect success
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
            return -1;
        }

    }
}
