package com.example.omar.shopfree.product;

import com.google.gson.annotations.Expose;

/**
 * Created by Nayle on 3/20/2015.
 */
public class Product {

    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String image;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }


    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}