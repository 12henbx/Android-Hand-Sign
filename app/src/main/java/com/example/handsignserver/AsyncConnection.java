package com.example.handsignserver;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class AsyncConnection extends AsyncTask<Void, Void, String> {
    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    public List<String> list = new ArrayList<>();

    AsyncConnection(String addr, int port, TextView textResponse) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;

    }

    @Override
    protected String doInBackground(Void... arg0) {
        //NameList listName = new NameList("lol");
        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];
            int bytesRead;
            InputStream inputStream = socket.getInputStream();

            /*
             * notice: inputStream.read() will block if no data return
             */
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response = byteArrayOutputStream.toString("UTF-8");
                list.add(response);
                Log.i("tes",response);
                byteArrayOutputStream.reset();
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }
}

