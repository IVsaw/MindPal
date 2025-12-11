package com.example.mindpal;

public class ProfItem {

    //semuanya harus di declare dulu di xml nya baru bisa dipanggil di sini
    private String name;
    private String review;
    private int imageResourceId;

    public ProfItem(String name, String review, int imageResourceId) {
        this.name = name;
        this.review = review;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getReview(){
        return review;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }
}