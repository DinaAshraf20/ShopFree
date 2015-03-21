
package com.example.omar.shopfree.products;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;


public class ProductsReply {

    @Expose
    private long responseId;
    @Expose
    private List<ProductJSON> products = new ArrayList<ProductJSON>();

    /**
     * 
     * @return
     *     The responseId
     */
    public long getResponseId() {
        return responseId;
    }

    /**
     * 
     * @param responseId
     *     The responseId
     */
    public void setResponseId(long responseId) {
        this.responseId = responseId;
    }

    /**
     * 
     * @return
     *     The products
     */
    public List<ProductJSON> getProducts() {
        return products;
    }

    /**
     * 
     * @param products
     *     The products
     */
    public void setProducts(List<ProductJSON> products) {
        this.products = products;
    }

}
