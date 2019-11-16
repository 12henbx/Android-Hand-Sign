package com.example.handsignserver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    String fromclient;
    TextView show_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Client client = new Client("10.42.0.1",41039);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
//        Client client = new Client("10.42.0.1",50001);
        Log.i("test","welll1");
        //show_text = findViewById(R.id.show_text);

    }


}
