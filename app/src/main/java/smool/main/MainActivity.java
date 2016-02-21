package smool.main;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;
import java.util.ArrayList;

import smool.shared.*;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public User eu = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuinicial);
    }


    public Object senddata(Object send, int flag) {


        NetworkTask networktask = new NetworkTask(send, flag); //Create initial instance so SendDataToNetwork doesn't throw an error.
        networktask.execute();

        while (networktask.getReceived() == 0) {
            try {
                Thread.interrupted();
                Thread.sleep(100);
            } catch (Exception e) {

            }
        }

        return networktask.getData();

    }

    public void login(View view) {
        /*1-Rotas
          2-eliminar rota  send = rota; flag = 1
          3-request rota
          4-criar conta
          5-login
          6-alterar dados utilizador
         */
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        if (!username.getText().equals("") && !username.getText().equals("")) {
            eu.setUsername("" + username.getText());
            eu.setPassword("" + password.getText());

            int result = (int) senddata(eu, 5);

            if (result == -1) {
                Toast.makeText(getApplicationContext(), "Autentificação falhou!", Toast.LENGTH_LONG).show();
            } else {
                eu.setID(result);
                //Toast.makeText(getApplicationContext(),"Autentificação falhou!", Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(MainActivity.this, MenuP.class);
                myIntent.putExtra("user", eu); //Optional parameters
                MainActivity.this.startActivity(myIntent);


                //setContentView(R.layout.activity_menu_p);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Por favor , preencha os campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void registar(View view) {
       /*1-Rotas
          2-eliminar rota  send = rota; flag = 1
          3-request rota
          4-criar conta
          5-login
          6-alterar dados utilizador
         */
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        if (!username.getText().equals("") && !username.getText().equals("")) {
            eu.setUsername("" + username.getText());
            eu.setPassword("" + password.getText());

            int result = (int) senddata(eu, 4);

            if (result == -1) {
                Toast.makeText(getApplicationContext(), "Criar conta falhou!", Toast.LENGTH_LONG).show();
            } else {
                eu.setID(result);
                Toast.makeText(MainActivity.this, "fffuick", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"Autentificação falhou!", Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(MainActivity.this, MenuP.class);
                myIntent.putExtra("user", eu); //Optional parameters
                MainActivity.this.startActivity(myIntent);

            }

        } else {
            Toast.makeText(getApplicationContext(), "Por favor , preencha os campos", Toast.LENGTH_SHORT).show();
        }
    }


}
