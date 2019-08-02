package io.audioshinigami.travelmantics;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String email;

    public User(){

    }

    public User(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> addMe = new HashMap<>();
        addMe.put("email", getEmail());

        return addMe;
    } /*end toMap*/
}
