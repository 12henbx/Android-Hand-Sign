package com.example.handsignserver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    String fromclient;
    ImageView imge_view;
    TextView show_text;
    Button btnListener;
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private BufferedReader in = null;

    public int length;
    private String address = "192.168.137.1";
    private int port = 50001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_text = (TextView) findViewById(R.id.show_text);
        btnListener = (Button) findViewById(R.id.btnListener);
        imge_view = (ImageView) findViewById(R.id.img_view);
        final AsyncConnection async = new AsyncConnection(address, port, show_text);
        async.execute();
        btnListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                length = async.list.size()-1;
                //show_text.setText(nameList.list.get(length));
                fromclient = async.list.get(length);
                //System.out.println(fromclient);
                if (fromclient.equals("punch\n")) {
                    show_text.setText(fromclient);
                }

                if (fromclient.equals("stop\n")) {
                    imge_view.setImageResource(R.drawable.stop_sign);
                } else if (fromclient.equals("thumbs_up\n")) {
                    imge_view.setImageResource(R.drawable.thumbsup);
                } else if (fromclient.equals("peace\n")) {
                    imge_view.setImageResource(R.drawable.peace_hand_sign);
                } else if (fromclient.equals("punch\n")) {
                    imge_view.setImageResource(R.drawable.punch_fist);
                }
                Log.i("len", String.valueOf(length));
            }
        });
        Log.i("test", "welll1");
        //show_text = findViewById(R.id.show_text);

    }


}
