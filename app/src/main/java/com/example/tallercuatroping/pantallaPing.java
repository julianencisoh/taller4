package com.example.tallercuatroping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class pantallaPing extends AppCompatActivity {

    private TextView texto;
    private Button botonregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_dos);

        texto = findViewById(R.id.texto);
        botonregresar = findViewById(R.id.botonregresar);

        String ipIntroducido = getIntent().getExtras().getString("ipIntroducido");


        botonregresar.setOnClickListener(
                v -> {
                 Intent i = new Intent(this,MainActivity.class);
                 startActivity(i);
                 finish(); }
        );


        new Thread(
                () -> {
                    try {
                        InetAddress inet = InetAddress.getByName(ipIntroducido);
                        for(int i=0; i<5;i++){
                            boolean conectado = inet.isReachable(3000);

                            runOnUiThread(
                                    ()->{
                                        if (conectado){
                                            texto.append("Recibido\n");
                                        }else {
                                            texto.append("Perdido\n");
                                        } }
                                        ); }

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        ).start();

    }
}