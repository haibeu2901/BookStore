/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author beu29
 */
public class OrderDTO implements Serializable {

    private int orderID;
    private String name;
    private String address;
    private Timestamp date;
    private BigDecimal total;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, String name, String address, Timestamp date, BigDecimal total) {
        this.orderID = orderID;
        this.name = name;
        this.address = address;
        this.date = date;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderID=" + orderID + ", name=" + name + ", address=" + address + ", date=" + date + ", total=" + total + '}';
    }

}
