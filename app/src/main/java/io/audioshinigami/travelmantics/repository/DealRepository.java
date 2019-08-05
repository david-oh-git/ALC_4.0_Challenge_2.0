package io.audioshinigami.travelmantics.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import io.audioshinigami.travelmantics.models.Deal;
import io.audioshinigami.travelmantics.utility.Utility;

public class DealRepository {

    private static DealRepository instance;

    private DealRepository(){

    }

    public void addDeal(Deal deal, final AppCompatActivity context){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(Utility.deal_location).document()
                .set(deal.toMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Deal added", Toast.LENGTH_LONG)
                                .show();
                        context.finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Oops !!! something went wrong ", Toast.LENGTH_LONG)
                        .show();
            }
        });
    } /*end addDeal*/

    public void uploadImage(String absFilePath) {
        String filename = absFilePath.substring(absFilePath.lastIndexOf('/') + 1);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference imageRef = storage.getReference().child(Utility.image_location)
                .child(filename);

        try {
            InputStream stream = new FileInputStream(new File(absFilePath));
            UploadTask imagetask = imageRef.putStream(stream);

            imagetask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


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

