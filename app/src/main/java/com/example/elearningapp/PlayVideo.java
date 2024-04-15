package com.example.elearningapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayVideo extends AppCompatActivity {

    VideoView videoView;
    TextView titleTv;
    ProgressBar progressBar;

    String title = "", videoUrl = "";

    @Override
    protected void onStart() {
        super.onStart();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        videoView = findViewById(R.id.videoView);
        titleTv = findViewById(R.id.titleTv);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        videoUrl = intent.getStringExtra("videoUrl");

        titleTv.setText(title);

        MediaController mediaController = new MediaController(PlayVideo.this);
        mediaController.setAnchorView(videoView);
        Uri videoUri = Uri.parse(videoUrl);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();

        videoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.start());

        videoView.setOnInfoListener((mp, what, extra) -> {

            switch (what) {
                case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                case MediaPlayer.MEDIA_INFO_BUFFERING_START: {
                    progressBar.setVisibility(View.VISIBLE);
                    return true;
                }
                case MediaPlayer.MEDIA_INFO_BUFFERING_END: {
                    progressBar.setVisibility(View.GONE);
                    return true;
                }
            }

            return false;
        });

        videoView.setOnCompletionListener(mp -> mp.start());

    }
}