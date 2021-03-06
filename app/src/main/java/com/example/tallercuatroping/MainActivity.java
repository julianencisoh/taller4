package com.example.tallercuatroping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private EditText casillaUno;
    private EditText casillaDos;
    private EditText casillaTres;
    private EditText casillaCuatro;
    private Button botonPing;
    private Button botonBuscarHosts;
    private TextView numeroIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        casillaUno = findViewById(R.id.casillaUno);
        casillaDos = findViewById(R.id.casillaDos);
        casillaTres = findViewById(R.id.casillaTres);
        casillaCuatro = findViewById(R.id.casillaCuatro);
        numeroIp = findViewById(R.id.numeroIp);
        botonPing = findViewById(R.id.botonPing);
        botonBuscarHosts = findViewById(R.id.botonBuscarHosts);

        botonPing.setOnClickListener(

                v -> {
                    String ip1 = casillaUno.getText().toString();
                    String ip2 = casillaDos.getText().toString();
                    String ip3 = casillaTres.getText().toString();
                    String ip4 = casillaCuatro.getText().toString();
                    String ipIntroducido = ip1+"."+ip2+"."+ip3+"."+ip4;

                    Intent i = new Intent(this, pantallaPing.class);
                    i.putExtra("ipIntroducido",ipIntroducido);
                    startActivity(i);

                }
                );

       // public static String formatIpAddres(int ip){
       //     return String.format(Locale.US,"%d.%d.%d.%d",(ip & 0xff), ip >> 8 & 0xff),(ip >> 16 & 0xff), (ip >> 24 & 0xff));
       // }

        botonBuscarHosts.setOnClickListener(
                v -> {

                    Intent a = new Intent(this, pantallaHosts.class);
                    startActivity(a);

                }
        );








        new Thread(
                () -> {

                    String base = "192.168.0.";


                    for(int i=1; i<255; i++){

                        String ip = base+i;

                        try {
                            InetAddress inet = InetAddress.getByName(ip);
                            boolean conectado = inet.isReachable(1000);

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