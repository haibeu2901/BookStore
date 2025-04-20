/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdetails;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author beu29
 */
public class OrderDetailDAO implements Serializable{
    
    private List<OrderDetailDTO> orderDetailsList;

    public List<OrderDetailDTO> getOrderDetailsList() {
        return orderDetailsList;
    }
    
}
