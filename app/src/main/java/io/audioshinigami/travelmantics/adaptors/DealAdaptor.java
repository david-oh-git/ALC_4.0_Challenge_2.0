package io.audioshinigami.travelmantics.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.audioshinigami.travelmantics.R;
import io.audioshinigami.travelmantics.models.Deal;
import io.audioshinigami.travelmantics.viewholders.DealViewHolder;

public class DealAdaptor extends RecyclerView.Adapter<DealViewHolder> {

    private ArrayList<Deal> data = new ArrayList<>();

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deal_item, parent, false);
        return new DealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {

        Deal currentDeal = data.get(position);
        holder.bind(currentDeal);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<Deal> data){
        this.data = data;
        notifyDataSetChanged();
    } /*end setData*/
}
