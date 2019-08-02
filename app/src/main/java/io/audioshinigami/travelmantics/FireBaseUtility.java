package io.audioshinigami.travelmantics;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseUtility {

    public static FirebaseAuth firebaseAuth;
    private static FireBaseUtility instance;
    public static FirebaseFirestore fbDatabase;

    private FireBaseUtility() {
    }

    public static FireBaseUtility getInstance(){
        if( instance == null ){
            synchronized ( FireBaseUtility.class ){
                if( instance == null ){
                    instance = new FireBaseUtility();
                } /*end IF*/
            }
        } /*end IF*/

        return instance;

    } /*end getInstance*/



    public static void fbInitReference(String ref){
        fbDatabase = FirebaseDatabase.getInstance();
    }/*end fbInit*/

} /*end FirebaseUtility*/
