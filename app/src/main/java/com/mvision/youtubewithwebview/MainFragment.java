package com.mvision.youtubewithwebview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by iceoton on 6/10/2016 AD.
 */
public class MainFragment extends Fragment {
    WebView videoView;
    YoutubeVideoController youtubeVideoController;
    TextView txtViedeoTime;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        videoView = (WebView) rootView.findViewById(R.id.video);
        txtViedeoTime = (TextView) rootView.findViewById(R.id.videoTime);


        videoView.setVisibility(View.VISIBLE);
        youtubeVideoController = new YoutubeVideoController(getActivity(), videoView);
        youtubeVideoController.load("rzPj9Yurcq0");



        youtubeVideoController.setYoutubeVideoListener(new YoutubeVideoListener() {
            @Override
            public void onUpdateTime(double time) {
                txtViedeoTime.setText(String.valueOf(time));
            }
        });

        return rootView;
    }
}
