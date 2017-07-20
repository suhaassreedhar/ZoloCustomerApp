package com.suhaas.zolocustomerapp.data.model;

/**
 * Created by suhaas on 20/7/17.
 */

public class Customer {

    private Long id;
    private Long phone_number;
    private String email_id;
    private String name;
    private String createdAt;
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
