package com.mvision.youtubewithwebview;

import android.content.Context;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by iceoton on 6/10/2016 AD.
 */
public class CWebVideoView {
    private String url;
    private Context context;
    private WebView webview;
    private static final String HTML_TEMPLATE = "webvideo.html";

    public CWebVideoView(Context context, WebView webview) {
        this.webview = webview;
        this.context = context;
        //webview.setBackgroundColor(0);
        webview.getSettings().setJavaScriptEnabled(true);
    }

    public void load(String url) {
        this.url = url;
        String data = readFromfile(HTML_TEMPLATE, context);
        data = data.replace("[%video]", url);
        webview.loadData(data, "text/html", "UTF-8");
    }

    public String readFromfile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets().open(fileName, Context.MODE_PRIVATE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    public void reload() {
        if (url != null) {
            load(url);
        }
    }

}
