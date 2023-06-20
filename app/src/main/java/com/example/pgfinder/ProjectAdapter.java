package com.example.pgfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {


    ArrayList<ProjectModel> list;
    Context context;


    public ProjectAdapter(ArrayList<ProjectModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProjectModel model=list.get(position);

        Picasso.get().load(model.getUploadImage()).placeholder(R.drawable.imageloading).into(holder.roomimage);

        holder.roomownername.setText(model.getName());

        holder.roomlocation.setText(model.getLocation());

        holder.roomprice.setText(model.getPrice());

        holder.roomavailable.setText(model.getAvailable());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,RoomDescriptionActivity.class);
                intent.putExtra("singleImage",model.getUploadImage());
                intent.putExtra("singleHeadline",model.getName());
                intent.putExtra("singlePrice",model.getPrice());
                intent.putExtra("singleLocation",model.getLocation());
                intent.putExtra("singleAvailable",model.getAvailable());
                intent.putExtra("singleAttached",model.getAttachedB());
                intent.putExtra("singleContact",model.getContnumber());
                intent.putExtra("singleSharing",model.getSharing());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView roomownername,roomlocation,roomprice,roomavailable;
        ImageView  roomimage;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           roomownername = itemView.findViewById(R.id.roomownername);
            roomlocation =itemView. findViewById(R.id.roomlocation);
            roomavailable = itemView.findViewById(R.id.roomavailable);
            roomprice = itemView.findViewById(R.id.roomprice);
            roomimage = itemView.findViewById(R.id.roomimage);
        }
    }
}
