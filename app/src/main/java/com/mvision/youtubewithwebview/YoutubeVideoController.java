package com.mvision.youtubewithwebview;

import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by iceoton on 6/13/2016 AD.
 */
public class YoutubeVideoController {
    Context mContext;
    private String videoId;
    private WebView webview;
    YoutubeVideoListener youtubeVideoListener;

    public YoutubeVideoController(Context context, WebView webview) {
        this.mContext = context;
        this.webview = webview;
        webview.setBackgroundColor(0);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

            }
        });
        webview.setWebChromeClient(new WebChromeClient());
        webview.addJavascriptInterface(this, "AndroidFunction");
    }

    public void load(String videoId) {
        this.videoId = videoId;
        webview.loadUrl("file:///android_asset/ads.html?id=" + videoId);
    }

    public void reload() {
        if (videoId != null) {
            load(videoId);
        }
    }

    public void setYoutubeVideoListener(YoutubeVideoListener listener) {
        this.youtubeVideoListener = listener;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void updateVideoTime(double time) {
        if (youtubeVideoListener != null) {
            youtubeVideoListener.onUpdateTime(time);
        }
    }

    @JavascriptInterface
    public void onPlayerError(String message) {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(mContext);
        myDialog.setTitle("Video Error!");
        myDialog.setMessage(message);
        myDialog.setPositiveButton("OK", null);
        myDialog.show();
    }

}
