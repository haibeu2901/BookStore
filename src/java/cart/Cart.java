/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;
import java.util.Map;
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

}
