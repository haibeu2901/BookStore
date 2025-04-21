/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
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

}
