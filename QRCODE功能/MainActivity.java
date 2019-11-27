package com.syneart.app.lemontree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
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



                    Intent i = new Intent(MainActivity.this, MAP.class);
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

    }
