package com.ryokodoko.ryokodoko;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchURL extends AsyncTask<String, Void, String> {
    Context mContext;
    String directionMode = "transit";

    public FetchURL(Context mContext){
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(String... strings) {
        // 웹 서비스를 위한 문자열 data
        String data = "";
        directionMode = strings[1];
        try{
            // 요청값 받기
            data = downloadUrl(strings[0]);
            Log.d("mylog", "Background task data " + data.toString());
        } catch (Exception e){
            Log.d("Background Task", e.toString());
        }
        return data;
    }




    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);
            // HttpURLConnection 만들고 url 연결
            urlConnection = (HttpURLConnection) url.openConnection();
            // URL 연결
            urlConnection.connect();
            // URL을 통해 데이터값 읽어오기
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            Log.d("mylog", "Downloaded URL: " + data.toString());
            br.close();
        } catch (Exception e) {
            Log.d("mylog", "Exception downloading URL: " + e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}
