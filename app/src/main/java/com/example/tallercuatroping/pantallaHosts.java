package com.example.tallercuatroping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class pantallaHosts extends AppCompatActivity {

    private TextView textoo;
    private Button botonregresarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_hosts);

        textoo = findViewById(R.id.textoo);
        botonregresarr = findViewById(R.id.botonregresarr);

        botonregresarr.setOnClickListener(
                (v)->{
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
        );
        new Thread(

                () -> {

                    String ip = "192.168.0.";

                    for (int i = 1; i < 255; i++) {
                        String ippantallaHost = ip + i;
                        InetAddress inet;

                        try {
                            inet = InetAddress.getByName(ippantallaHost);
                            boolean conectado = inet.isReachable(1000);

                            if (conectado) {
                                runOnUiThread(
                                        () -> {
                                            textoo.append(ippantallaHost + "\n");
                                        }
                                        );
                            }
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();






    }
}