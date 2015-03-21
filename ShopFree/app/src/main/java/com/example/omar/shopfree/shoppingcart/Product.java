
package com.example.omar.shopfree.shoppingcart;


import com.google.gson.annotations.Expose;

//@Generated("org.jsonschema2pojo")
public class Product {

    @Expose
    private String name;
    @Expose
    private String image;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
