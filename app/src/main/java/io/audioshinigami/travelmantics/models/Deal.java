package io.audioshinigami.travelmantics.models;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

import io.audioshinigami.travelmantics.utility.Utility;

public class Deal {

    private String title;
    private String description;
    private String price;
    private String imageUrl;
    private String imageName;
    private String absPath;

    public Deal(){

    }

    public Deal(String title, String description, String price, String imageUrl, String imageName) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.absPath = "";
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
    public Map<String, String> toMap(){
        HashMap<String, String> addMe =  new HashMap<>();
        addMe.put(Utility.title_key, getTitle());
        addMe.put(Utility.description_key, getDescription());
        addMe.put(Utility.price_key, getPrice());
        addMe.put(Utility.image_url_key,getImageUrl());
        addMe.put(Utility.image_name_key, getImageName());
        addMe.put(Utility.absolute_path_key, getAbsPath());

        return addMe;
    } /*end to Map*/

    public static Deal mapToDeal(Map<String, Object> dealMap){
        String title = (String) dealMap.get(Utility.title_key);
        String description = (String) dealMap.get(Utility.description_key);
        String price = (String) dealMap.get(Utility.price_key);
        String imageUrl = (String) dealMap.get(Utility.image_url_key);
        String imageName = (String) dealMap.get(Utility.image_name_key);
        String absPath = (String) dealMap.get(Utility.absolute_path_key);

        Deal deal = new Deal(title, description, price, imageUrl, imageName);
        deal.setAbsPath(absPath);

        return deal;

    } /*end mapToDeal*/
}
