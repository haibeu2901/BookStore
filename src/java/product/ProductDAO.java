/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author beu29
 */
public class ProductDAO implements Serializable{
    
    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }
    
}
