package io.audioshinigami.travelmantics.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.audioshinigami.travelmantics.GlideApp;
import io.audioshinigami.travelmantics.R;
import io.audioshinigami.travelmantics.models.Deal;

public class DealViewHolder extends RecyclerView.ViewHolder {

    private TextView dealTitle;
    private TextView dealDescription;
    private TextView dealPrice;
    private ImageView imageView;

    public DealViewHolder(View view){
        super(view);
        dealTitle = view.findViewById(R.id.id_deal_title);
        dealDescription = view.findViewById(R.id.id_deal_description);
        dealPrice = view.findViewById(R.id.id_deal_price);
        imageView = view.findViewById(R.id.id_deal_image);
    }

    public void bind(Deal deal){

        dealTitle.setText(deal.getTitle());
        dealDescription.setText(deal.getDescription());
        dealPrice.setText(String.valueOf(deal.getPrice()));

        if(deal.getImageUrl() != null){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference().child(deal.getImageUrl());

            GlideApp.with(imageView.getContext())
                    .load(storageRef)
                    .into(imageView);

        } /*end IF*/
    } /*end bind*/

}
