/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import orderdetails.OrderDetailDAO;
import orderdetails.OrderDetailDTO;
import orders.OrderDAO;
import product.ProductDAO;
import product.ProductDTO;

/**
 *
 * @author beu29
 */
public class Cart implements Serializable {

    private Map<ProductDTO, Integer> items;

    public Map<ProductDTO, Integer> getItems() {
        return items;
    }

    public void setItems(Map<ProductDTO, Integer> items) {
        this.items = items;
    }

    public void addItemToCart(String SKU)
            throws SQLException, NamingException {
        //1. Checking items has existed
        if (SKU == null) {
            return;
        }

        if (SKU.trim().isEmpty()) {
            return;
        }

        if (this.items == null) {
            this.items = new HashMap<>();
        }

        //2. Checking item exited in items
        int quantity = 1;
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.getProductBySKU(SKU);

        if (this.items.containsKey(dto)) {
            quantity = this.items.get(dto) + 1;
        }

        //3. Update items
        this.items.put(dto, quantity);
    }

    public void removeItemBySKU(String SKU)
            throws SQLException, NamingException {
        //1. checking items has existed
        if (this.items == null) {
            return;
        }
        //2. checking item existed in cart
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.getProductBySKU(SKU);
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public void removeItemsForCheckOut(ProductDTO dto) {
        if (this.items == null) {
            return;
        }

        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public Map<ProductDTO, Integer> showCheckedItems(String[] SKU)
            throws SQLException, NamingException {
        if (this.items == null) {
            return null;
        }

        Map<ProductDTO, Integer> list = new HashMap<>();
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = new ProductDTO();
        for (String sku : SKU) {
            dto = dao.getProductBySKU(sku);
            list.put(dto, items.get(dto));
        }
        return list;
    }
    
    public int checkOutItemsOfCart(String name, String address, String total, 
            Map<ProductDTO, Integer> checkedItems) 
        throws SQLException, NamingException{
        if (this.items == null) {
            return -1;
        }
        
        OrderDAO ordersDAO = new OrderDAO();
        int orderID = ordersDAO.createNewOrder(name, address, total);
        
        if (orderID > 0) {
            OrderDetailDAO orderDetailsDAO = new OrderDetailDAO();
            boolean result = 
                    orderDetailsDAO.createOrderDetails(orderID, checkedItems);
            if (result) {
                return orderID;
            }
        }
        return -1;
    }
    
    public int getQuantityBySKU(String SKU) {
        if (SKU == null || SKU.trim().isEmpty()) {
            return 0;
        }
        
        if (this.items == null) {
            return 0;
        }
        
        int quantity = 0;
        
        for (ProductDTO dto : this.items.keySet()) {
            if (SKU.equals(dto.getSKU())) {
                quantity = this.items.get(dto);
                return quantity;
            }
        }
        
        return 0;
    }
    
    public List<OrderDetailDTO> addItemsToOrderDetailsDTO
        (Map<ProductDTO, Integer> checkedItems, int orderID) {
        List<OrderDetailDTO> list = new ArrayList<>();
        
        for (ProductDTO productDTO : checkedItems.keySet()) {
            String SKU = productDTO.getSKU();
            String name = productDTO.getName();
            BigDecimal price = productDTO.getPrice();
            int quantity = checkedItems.get(productDTO);
            BigDecimal total;
            total = price.multiply(new BigDecimal(quantity));
            
            OrderDetailDTO orderDetailsDTO = 
                    new OrderDetailDTO(orderID, SKU, name, price, quantity, total);
            
            list.add(orderDetailsDTO);
        }
        
        return list;
    }

}
