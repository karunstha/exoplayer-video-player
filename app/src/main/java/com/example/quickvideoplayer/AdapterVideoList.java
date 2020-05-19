package com.example.quickvideoplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterVideoList extends RecyclerView.Adapter<AdapterVideoList.MyViewHolder> {

    ArrayList<ModelVideo> videosList = new ArrayList<ModelVideo>();
    Context context;

    AdapterVideoList(Context context, ArrayList<ModelVideo> videosList){
        this.context = context;
        this.videosList = videosList;
    }

    @NonNull
    @Override
    public AdapterVideoList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_video, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVideoList.MyViewHolder holder, int position) {
        final ModelVideo item = videosList.get(position);
        holder.tv_title.setText(item.getTitle());
        holder.tv_duration.setText(item.getDuration());
        Glide.with(context).load(item.getData()).into(holder.imgView_thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Activity_Player.class);
                intent.putExtra("videoId", item.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView_thumbnail;
        TextView tv_title, tv_duration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            imgView_thumbnail = itemView.findViewById(R.id.imageView_thumbnail);
        }
    }
}
