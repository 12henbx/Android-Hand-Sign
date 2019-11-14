package com.example.handsignserver;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import static android.provider.Telephony.Carriers.PORT;

public class PacketSendReceive extends AsyncTask<String, Void, String> {
    //private Object prop;

//    @Override
//    public void run() {
//
//    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            ServerSocket serverSocket = new ServerSocket(6868);
            Socket socket = serverSocket.accept();
            InputStream input = socket.getInputStream();
//            InputStreamReader reader = new InputStreamReader(input);
//            int character = reader.read();  // reads a single character
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = reader.readLine();    // reads a line of text
            Log.i("satu",line);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
