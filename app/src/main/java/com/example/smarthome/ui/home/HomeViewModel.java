package com.example.smarthome.ui.home;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smarthome.R;
import com.example.smarthome.RequestHttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class HomeViewModel extends ViewModel {

    private URL Url;
    private String strUrl,strCookie,result;
    private MutableLiveData<String> mText;

    Handler handler = new Handler();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        strUrl = "http://192.168.0.3:90/";
        NetworkTask networkTask = new NetworkTask(strUrl, null);
        networkTask.execute();
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mText.setValue(s);
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}