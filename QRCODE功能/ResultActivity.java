package com.syneart.app.lemontree;

import android.app.Activity;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.syneart.app.lemontree.R;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ResultActivity extends Activity {

    private ImageView mImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent mIntent = getIntent();
        String textResult = mIntent.getStringExtra("TEXT_RESULT");

        mImageView = (ImageView)findViewById(R.id.ivImage);
        mTextView = (TextView)findViewById(R.id.tvResult);

        // http://dummy.restapiexample.com
        GetDataTask mGetDataTask = new GetDataTask();
        mGetDataTask.execute(textResult);
    }

    private class GetDataTask extends AsyncTask<String, Integer, String> {
        private String id;
        protected String doInBackground(String... ids) {
            id = ids[0];
//            更新UI 畫面
            publishProgress(0);
//            網路連線效率高
            final OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://140.131.114.154:80/connection/test3.php?number=" + id)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                JSONObject jsonObject = new JSONObject(response.body().string());
                String employee_salary = jsonObject.getString("cname") + "\n" + jsonObject.getString("sname") + "\n" +
                        jsonObject.getString("place") + "\n" + jsonObject.getString("life") + "\n" + jsonObject.getString("branch") + "\n" +
                        jsonObject.getString("leaf") + "\n" + jsonObject.getString("spore") + "\n" + jsonObject.getString("fert") + "\n" + jsonObject.getString("describe");

                return employee_salary;
            } catch (Exception e) {
                Log.e("TAT", e.getMessage());
            }

            return "?Aloha";
        }

        protected void onProgressUpdate(Integer... progress) {
            mTextView.setText("讀取中 ...");
        }

        protected void onPostExecute(String result) {
            Glide.with(ResultActivity.this).load("http://140.131.114.154:80/connection/img/" + id + ".jpg").into(mImageView);
            mTextView.setText("植物中文名 : " + result);
        }
    }
}