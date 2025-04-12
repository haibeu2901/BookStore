/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author beu29
 */
public class ProductDTO implements Serializable {

    private String SKU;
    private String name;
    private BigDecimal price;
    private String description;
    private int quantity;

    public ProductDTO() {
    }

    public ProductDTO(String SKU, String name, BigDecimal price, String description, int quantity) {
        this.SKU = SKU;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "SKU=" + SKU + ", name=" + name + ", price=" + price + ", description=" + description + ", quantity=" + quantity + '}';
    }

}
