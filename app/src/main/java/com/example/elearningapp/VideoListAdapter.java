package com.example.elearningapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/** @noinspection ALL */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.holder> {

    final Context context;
    ArrayList<ModelVideo> videoArrayList;

    public VideoListAdapter(Context context, ArrayList<ModelVideo> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_videos, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        ModelVideo video = videoArrayList.get(position);
        holder.tv_title.setText(video.getTitle());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayVideo.class);
            intent.putExtra("title", video.getTitle());
            intent.putExtra("videoUrl", video.getVideoUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    static class holder extends RecyclerView.ViewHolder {

        final TextView tv_title;

        public holder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.titleTv);

        }
    }
}
