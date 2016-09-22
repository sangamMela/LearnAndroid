package com.example.akshaykumar.learnandroid.data;

import java.io.Serializable;

public class AkhadaasInformation implements Serializable{
    private int id;
    private String name, details, image, community, contact;

    public AkhadaasInformation() {
    }

    public AkhadaasInformation(int id, String name, String details, String image, String community, String contact) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.image = image;
        this.community = community;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}