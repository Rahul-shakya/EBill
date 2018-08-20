package com.example.asus.ebill;

public class Orders {

    public String field;
    public String time;
    public String service;
    public String status;
    public String image;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {

        return status;
    }

    public void setFieldt(String field) {
        this.field = field;
    }

    public void setTimet(String time) {
        this.time = time;
    }

    public void setServicet(String service) {
        this.service = service;
    }

    public String getFieldt() {

        return field;
    }

    public String getTimet() {
        return time;
    }

    public String getServicet() {
        return service;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
