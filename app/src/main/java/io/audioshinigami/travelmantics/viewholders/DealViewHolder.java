package io.audioshinigami.travelmantics.viewholders;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.audioshinigami.travelmantics.GlideApp;
import io.audioshinigami.travelmantics.R;
import io.audioshinigami.travelmantics.activities.DealActivity;
import io.audioshinigami.travelmantics.models.Deal;
import io.audioshinigami.travelmantics.utility.Utility;

public class DealViewHolder extends RecyclerView.ViewHolder {

    private TextView dealTitle;
    private TextView dealDescription;
    private TextView dealPrice;
    private ImageView imageView;
    private Deal deal;

    public DealViewHolder(View view){
        super(view);
        dealTitle = view.findViewById(R.id.id_deal_title);
        dealDescription = view.findViewById(R.id.id_deal_description);
        dealPrice = view.findViewById(R.id.id_deal_price);
        imageView = view.findViewById(R.id.id_deal_image);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(DealActivity.class, (AppCompatActivity) view.getContext(), createBundle(deal));
            }
        });
    }

    private Bundle createBundle(Deal deal){
        Bundle dealBundle = new Bundle();
        dealBundle.putString(Utility.title_key, deal.getTitle());
        dealBundle.putString(Utility.price_key, deal.getPrice());
        dealBundle.putString(Utility.description_key, deal.getDescription());
        dealBundle.putString(Utility.image_name_key, deal.getImageName());
        dealBundle.putString(Utility.image_url_key, deal.getImageUrl());
        dealBundle.putString(Utility.absolute_path_key, deal.getAbsPath());

        return dealBundle;
    } /*end createBundle*/

    public void bind(Deal deal){
        this.deal = deal;
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
