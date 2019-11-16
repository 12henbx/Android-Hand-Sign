package com.example.handsignserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AppCompatActivity {
    private Socket socket            = null;
    private DataInputStream input   = null;
    private DataOutputStream out     = null;
    private BufferedReader in   = null;
    TextView show_text;
    ImageView img_view;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        show_text = findViewById(R.id.show_text);
        img_view = findViewById(R.id.img_view);
        Log.i("test","welll2");
        // establish a connection
        try
        {
            socket = new Socket(address, port);

            System.out.println("Connected\n");

            // takes input from terminal
            //input  = new DataInputStream(socket.getInputStream());//System.in);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //PrintWriter out = new PrintWriter(client.getOutputStream(),true);

            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "y";

        // keep reading until "Over" is input
        while (!line.equals("Over"))
        {
                try
                {
                    Log.i("test","hmhm");

                    line = in.readLine();
                    line = line;
                    out.writeUTF(line);
                    Log.i("test",line);
                    show_text.setText(line);
                    if(line == "stop"){
                        img_view.setImageResource(R.drawable.stop_sign);
                    }else if(line == "thumbs"){
                        img_view.setImageResource(R.drawable.thumbsup);
                    }
                    else if (line == "peace") {
                        img_view.setImageResource(R.drawable.peace_hand_sign);
                    }else if (line == "punch"){
                        img_view.setImageResource(R.drawable.punch_fist);
                    }
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }

        }

        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("10.42.0.1",41029);
    }
}
