package com.duanmau.prjmap.retrofit;

import com.google.gson.annotations.SerializedName;

public class Product {
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("description")
    private String description;

    private String created_at;

    private String updated_at;


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getName() {
        return name;
    }
}
