package io.audioshinigami.travelmantics.models;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Deal {

    private String title;
    private String description;
    private int price;
    private String imageUrl;
    private String imageName;
    private String absPath;

    public Deal(){

    }

    public Deal(String title, String description, int price, String imageUrl, String imageName) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAbsPath() {
        return absPath;
    }

    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> addMe =  new HashMap<>();
        addMe.put("title", getTitle());
        addMe.put("description", getDescription());
        addMe.put("price", getPrice());
        addMe.put("imageUrl",getImageUrl());
        addMe.put("imageName", getImageName());

        return addMe;
    } /*end to Map*/
}
