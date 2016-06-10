package com.mvision.youtubewithwebview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by iceoton on 6/10/2016 AD.
 */
public class MainFragment extends Fragment {
    WebView videoView;
    CWebVideoView cWebVideoView;

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
        videoView.setVisibility(View.VISIBLE);
        cWebVideoView = new CWebVideoView(getActivity(), videoView);
        cWebVideoView.load("cx20nTPZjes");

        return rootView;
    }
}
