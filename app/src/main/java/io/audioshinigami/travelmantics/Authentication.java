package io.audioshinigami.travelmantics;



public class Authentication {
    public boolean userExists(){
        dbinstance = FireBaseUtility.getFireBaseDBInstance();
        userRef = dbinstance.getReference().child("users");
    } /*end User*/
}
