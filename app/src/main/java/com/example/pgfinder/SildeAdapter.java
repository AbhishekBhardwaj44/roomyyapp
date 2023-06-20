package com.example.pgfinder;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SildeAdapter extends RecyclerView.Adapter<SildeAdapter.SlideViewHolder> {

    private List<SlideIten> slideItems;
    private ViewPager2 viewPager2;
    SildeAdapter(List<SlideIten> slideItems, ViewPager2 viewPager2){
        this.slideItems=slideItems;
        this.viewPager2=viewPager2;
    }

    public SildeAdapter(homeefrag homeefrag) {
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {

        holder.setImage(slideItems.get(position));
        if (position == slideItems.size()-2) {

            viewPager2.post(runnable);

        }

    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder{

        private RoundedImageView imageView;

        SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageSlider);
        }

        void setImage(SlideIten slideItem){

//          if you want to display image from internet you can put code here usinf glide or piccaso

            imageView.setImageResource(slideItem.getImage());
        }

    }

    private Runnable runnable= new Runnable() {
        @Override
        public void run() {
            slideItems.addAll(slideItems);
            notifyDataSetChanged();
        }
    };
}

