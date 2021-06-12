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
    private MutableLiveData<String> Temperature;
    private MutableLiveData<String> Humidity;

    Handler handler = new Handler();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        Temperature = new MutableLiveData<>();
        Humidity = new MutableLiveData<>();
        strUrl = "https://m.naver.com/";          //아두이노 없을경우
        //strUrl = "http://192.168.0.3:90/";      //내부 아이피 선언
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
            if(s!=null) {
                Temperature.setValue("온도 : " + s.substring(172, 174) + "'C");
                Humidity.setValue("습도 : " + s.substring(198, 200) + "%");
            }else{
                Temperature.setValue("Connection Error");
                Humidity.setValue("Connection Error");
            }
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getTemperature(){ return Temperature; }
    public LiveData<String> getHumidity(){ return Humidity; }
}