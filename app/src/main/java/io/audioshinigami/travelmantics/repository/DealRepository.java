package io.audioshinigami.travelmantics.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageMetadata;

import java.io.ByteArrayOutputStream;

import io.audioshinigami.travelmantics.models.Deal;
import io.audioshinigami.travelmantics.utility.Utility;

public class DealRepository {

    private static DealRepository instance;

    private DealRepository(){

    }

    public void addDeal(Deal deal, final Context context){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(Utility.deal_location).document(deal.getTitle())
                .set(deal.toMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Deal added", Toast.LENGTH_LONG)
                                .show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Oops !!! something went wrong ", Toast.LENGTH_LONG)
                        .show();
            }
        });
    } /*end addDeal*/


    public static DealRepository getInstance(){
        if( instance == null ){
            synchronized ( DealRepository.class ){
                if( instance == null ){
                    instance = new DealRepository();
                } /*end IF*/
            }
        } /*end IF*/
        return instance;
    } /*end getInstance*/
}

