/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdetails;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author beu29
 */
public class OrderDetailDTO implements Serializable {

    private int orderID;
    private String SKU;
    private String name;
    private BigDecimal price;
    private int quantity;
    private BigDecimal total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderID, String SKU, String name, BigDecimal price, int quantity, BigDecimal total) {
        this.orderID = orderID;
        this.SKU = SKU;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "orderID=" + orderID + ", SKU=" + SKU + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", total=" + total + '}';
    }

}
