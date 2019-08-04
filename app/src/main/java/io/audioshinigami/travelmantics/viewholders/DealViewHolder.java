package io.audioshinigami.travelmantics.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import io.audioshinigami.travelmantics.R;
import io.audioshinigami.travelmantics.models.Deal;

public class DealViewHolder extends RecyclerView.ViewHolder {

    private TextView dealTitle;
    private TextView dealDescription;
    private TextView dealPrice;

    public DealViewHolder(View view){
        super(view);
        dealTitle = view.findViewById(R.id.id_deal_title);
        dealDescription = view.findViewById(R.id.id_deal_description);
        dealPrice = view.findViewById(R.id.id_deal_price);
    }

    public void bind(Deal deal){
        dealTitle.setText(deal.getTitle());
        dealDescription.setText(deal.getDescription());
        dealPrice.setText(String.valueOf(deal.getPrice()));
    }

}
