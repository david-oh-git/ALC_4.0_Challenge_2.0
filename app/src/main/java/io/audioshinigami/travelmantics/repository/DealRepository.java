package io.audioshinigami.travelmantics.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import io.audioshinigami.travelmantics.models.Deal;
import io.audioshinigami.travelmantics.utility.Utility;

public class DealRepository {

    private static DealRepository instance;
    private ArrayList<Deal> deals = new ArrayList<>();

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
            UploadTask image_task = imageRef.putStream(stream);

            image_task.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void getAllDeals(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(Utility.deal_location)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if( task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("cata",document.getId() +" : "+ document.getData());

                                Map<String, Object> dealMap = document.getData();
                                Deal deal = Deal.mapToDeal(dealMap);
                                deals.add(deal);
                                Log.d("cata","data size is : " + deals.size());
                            }
                        }
                    }
                });
    }// end getAllDoc


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

