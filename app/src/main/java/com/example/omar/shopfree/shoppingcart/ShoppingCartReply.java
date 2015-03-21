
package com.example.omar.shopfree.shoppingcart;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

//@Generated("org.jsonschema2pojo")
public class ShoppingCartReply {

    @Expose
    private long responseId;
    @Expose
    private List<Product> products = new ArrayList<Product>();

    /**
     * @return The responseId
     */
    public long getResponseId() {
        return responseId;
    }

    /**
     * @param responseId The responseId
     */
    public void setResponseId(long responseId) {
        this.responseId = responseId;
    }

    /**
     * @return The products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products The products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
