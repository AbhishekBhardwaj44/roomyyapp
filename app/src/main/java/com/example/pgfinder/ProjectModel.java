package com.example.pgfinder;

public class ProjectModel {
   private String  location,name,sharing,attachedB,price,available,contnumber;
   private String uploadImage;

    public ProjectModel() {




    }

    public ProjectModel(String location, String name, String sharing, String attachedB, String price, String available, String contnumber, String uploadImage) {
        this.location = location;
        this.name = name;
        this.sharing = sharing;
        this.attachedB = attachedB;
        this.price = price;
        this.available = available;
        this.contnumber = contnumber;
        this.uploadImage = uploadImage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSharing() {
        return sharing;
    }

    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    public String getAttachedB() {
        return attachedB;
    }

    public void setAttachedB(String attachedB) {
        this.attachedB = attachedB;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getContnumber() {
        return contnumber;
    }

    public void setContnumber(String contnumber) {
        this.contnumber = contnumber;
    }

    public String getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(String uploadImage) {
        this.uploadImage = uploadImage;
    }
}
