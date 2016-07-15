package com.mvision.youtubewithwebview;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by iceoton on 6/10/2016 AD.
 */
public class MainFragment extends Fragment {
    WebView videoView;
    YoutubeVideoController youtubeVideoController;
    TextView txtViedeoTime;
    Button btnSeekTo;
    RelativeLayout layoutVideo;
    int defaultWidth;
    int defaultHeight;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        videoView = (WebView) rootView.findViewById(R.id.video);
        txtViedeoTime = (TextView) rootView.findViewById(R.id.videoTime);
        layoutVideo = (RelativeLayout) rootView.findViewById(R.id.layoutVideo);
        ViewGroup.LayoutParams lp = layoutVideo.getLayoutParams();
        defaultWidth = lp.width;
        defaultHeight = lp.height;
        Log.d("TON", "screen size (begin)=(" + defaultWidth + "," + defaultHeight + ")");

        videoView.setVisibility(View.VISIBLE);
        youtubeVideoController = new YoutubeVideoController(getActivity(), videoView);
        youtubeVideoController.load("rzPj9Yurcq0");


        youtubeVideoController.setYoutubeVideoListener(new YoutubeVideoListener() {
            @Override
            public void onUpdateTime(double time) {
                txtViedeoTime.setText(String.valueOf(time));
            }
        });

        btnSeekTo = (Button) rootView.findViewById(R.id.btnSeekTo);
        btnSeekTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtubeVideoController.seekTo(20);
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        videoView.destroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.d("TON", "Orientation change");
        Toast.makeText(getActivity(), "Orientation change", Toast.LENGTH_SHORT).show();
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(getActivity(), "landscape", Toast.LENGTH_SHORT).show();
            expandToFullscreen();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(getActivity(), "portrait", Toast.LENGTH_SHORT).show();
            setToDefaultSize();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("defaultWidth", defaultWidth);
        outState.putInt("defaultHeight", defaultHeight);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            defaultWidth = savedInstanceState.getInt("defaultWidth");
            defaultHeight = savedInstanceState.getInt("defaultHeight");
        }
    }

    private void expandToFullscreen(){
        //layoutVideo.bringToFront();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

        layoutVideo.setTranslationX(0);
        layoutVideo.setTranslationY(0);

        ViewGroup.LayoutParams lp = layoutVideo.getLayoutParams();
        Log.d("TON", "screen size=(" + displaymetrics.widthPixels + "," + displaymetrics.heightPixels + ")");
        lp.width = screenWidth;
        lp.height = screenHeight;
        layoutVideo.setLayoutParams(lp);
    }

    private void setToDefaultSize(){
        ViewGroup.LayoutParams lp = layoutVideo.getLayoutParams();
        Log.d("TON", "screen size=(" + defaultWidth + "," + defaultHeight + ")");
        lp.width = defaultWidth;
        lp.height = defaultHeight;
        layoutVideo.setLayoutParams(lp);
    }

    @Override
    public void onDestroy() {
        super.onStop();
        youtubeVideoController.release();
    }

}
