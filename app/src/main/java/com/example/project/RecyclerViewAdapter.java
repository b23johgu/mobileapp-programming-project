package com.example.project;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Plants> items;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    RecyclerViewAdapter(Context context, List<Plants> items, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.onClickListener = onClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.plant_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Plants plant = items.get(position);
        holder.name.setText(plant.getName());
        holder.company.setText(plant.getCompany());
        holder.location.setText("Ursprung: " + plant.getLocation());
        // Set centimeter
        holder.centimeter.setText(String.valueOf("Cirka " + plant.getCentimeter()) + " cm hög");

       /* // Load plant photo from assets folder
        try {
            InputStream inputStream = layoutInflater.getContext().getAssets().open(plant.getPlantPhoto() + ".png");
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.plantPhoto.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView company;
        TextView location;
        TextView centimeter;
        /*ImageView plantPhoto;*/


        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.plant_title);
            company = itemView.findViewById(R.id.plant_latin);
            location = itemView.findViewById(R.id.plant_location);
            centimeter = itemView.findViewById(R.id.plant_category);
            /*plantPhoto = itemView.findViewById(R.id.plant_image);*/


        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(items.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(Plants item);
    }
}