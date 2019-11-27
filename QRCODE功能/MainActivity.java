package com.syneart.app.lemontree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.syneart.app.lemontree.Quyuchuangguan;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends Activity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Button btn_qrcode;
//        Button btn_map;
//        Button btn_us;
//        Button btn_quyuchuangguan;
//        btn_qrcode = (Button) findViewById(R.id.scan_btn);
//        btn_map = (Button) findViewById(R.id.map);
//        btn_us = (Button) findViewById(R.id.us);
//        btn_quyuchuangguan = (Button) findViewById(R.id.quyuchuangguan);
          //private Quyuchuangguan quyuchuangguan;
          public  String DB_NAME = "test.db";
          public  String DB_PATH = "/data/data/com.syneart.app.lemontree/databases/test.db";
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initFile();
            //quyuchuangguan =new Quyuchuangguan(this,MainActivity.this);
            Button scan_btn = (Button) findViewById(R.id.scan_btn);
            Button us=(Button)findViewById(R.id.us);
            Button map=(Button)findViewById(R.id.map);
            Button quyuchuangguan=(Button)findViewById(R.id.quyuchuangguan);
            scan_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                    integrator.setPrompt("QRCODE");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(false);
                //integrator.setOrientationLocked(false);
                    integrator.initiateScan();
            }
            });
            us.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){



                    Intent i = new Intent(MainActivity.this, US.class);
////启动
                    startActivity(i);
                }
            });
            map.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){



                    Intent i;
                    i = new Intent(MainActivity.this, MapsActivity.class);
////启动
                    startActivity(i);
                }
            });


            quyuchuangguan.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){



                    Intent i = new Intent(MainActivity.this, Quyuchuangguan.class);
////启动
                    startActivity(i);
                }
            });
        }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(MainActivity.this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent(MainActivity.this, ResultActivity.class);
                mIntent.putExtra("TEXT_RESULT", result.getContents());
                startActivity(mIntent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initFile() {
        //判断数据库是否拷贝到相应的目录下
        if (new File(DB_PATH + DB_NAME).exists() == false) {
            File dir = new File(DB_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }

            //复制文件
            try {
                InputStream is = getBaseContext().getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);

                //用来复制文件
                byte[] buffer = new byte[1024];
                //保存已经复制的长度
                int length;

                //开始复制
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                //刷新
                os.flush();
                //关闭
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
