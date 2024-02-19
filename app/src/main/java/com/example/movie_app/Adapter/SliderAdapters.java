package com.example.movie_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.movie_app.Domain.SliderItems;
import com.example.movie_app.R;

import java.util.List;

public class SliderAdapters extends RecyclerView.Adapter<SliderAdapters.SliderViewHolder> {
    private List<SliderItems> SliderItems;
    private ViewPager2 ViewPager2;
    private Context Context;

    public SliderAdapters(List<com.example.movie_app.Domain.SliderItems> sliderItems, androidx.viewpager2.widget.ViewPager2 viewPager2) {
        this.SliderItems = sliderItems;
        this.ViewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapters.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context=parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_items_container,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapters.SliderViewHolder holder, int position) {
        holder.setImageview(SliderItems.get(position));
        if(position==SliderItems.size()-2){
            ViewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return SliderItems.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder{
    private ImageView imageview;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview =itemView.findViewById(R.id.imageslide);
        }
        void setImageview(SliderItems sliderItems){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions=requestOptions.transforms(new CenterCrop(), new RoundedCorners(60));

            Glide.with(Context)
                    .load(sliderItems.getImage())
                    .apply(requestOptions)
                    .into(imageview);
        }
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            SliderItems.addAll(SliderItems);
            notifyDataSetChanged();
        }
    };
}
