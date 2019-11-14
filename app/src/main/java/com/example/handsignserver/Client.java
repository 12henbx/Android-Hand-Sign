package com.example.handsignserver;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket            = null;
    private DataInputStream input   = null;
    private DataOutputStream out     = null;
    private BufferedReader in   = null;
    TextView show_text;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
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
                    out.writeUTF(line+ "bla");
                    Log.i("test",line);
                    //show_text
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
