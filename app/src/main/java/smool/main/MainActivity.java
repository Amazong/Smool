package smool.main;

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
        import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public User eu = new User();
    public ArrayList <Ponto> pontos = new ArrayList();
    public boolean stop=false;
    public Rota novarota = new Rota();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuinicial);

        adicionarPontos();

    }

    protected void adicionarPontos() {
        String[][] addp = {{"A", "0", "8", "1", "2"}, {"B", "1", "5", "0", "3", "6"}, {"C", "2", "18", "0", "4", "5"}, {"D", "3", "0", "1", "10"},
                {"E", "5", "13", "2", "5", "6"}, {"F", "6", "19","2","4","7","6","11"}, {"G", "7", "9", "1","4","10","5","8"}, {"H", "8", "22","5","11"},
                {"I", "10", "9", "9","12","6"}, {"J", "10", "15", "11", "8"}, {"K", "11", "1", "12", "13", "3", "6"}, {"L", "11", "19", "7", "5","16","9"},
                {"M", "13", "5", "8", "10", "14", "15"}, {"N", "15", "1", "14", "10", "20"}, {"O", "15", "3", "13", "12", "17"},
                {"P", "19", "11", "18", "19", "12"}, {"Q", "20", "20", "11", "19"}, {"R", "21", "5", "14", "18", "20"},
                {"S", "21", "10", "15", "17", "21"}, {"T", "21", "15", "15", "16"}, {"U", "22", "3", "17", "21", "13"}, {"V", "25", "10", "18", "20"}};
        for (int i = 0; i < addp.length; i++) {
            Ponto p = new Ponto(addp[i][0], Integer.parseInt(addp[i][1]), Integer.parseInt(addp[i][2]));
            pontos.add(p);
        }

        for (int i = 0; i < addp.length; i++){
            ArrayList <Ponto> aux = new ArrayList<>();
            for (int j = 3; j < addp[i].length; j++){
                aux.add(pontos.get(Integer.parseInt((addp[i][j]))));
            }
            pontos.get(i).addAmigo(aux);
        }
    }

    public void startRoute(View view) throws InterruptedException {
        Button b= (Button) findViewById(R.id.startRoute);
        b.setEnabled(false);
        Button a= (Button) findViewById(R.id.stopRoute);
        a.setEnabled(true);

        stop=false;

        new Thread(new Runnable() {
            Date data=new Date();
            double latitude=0,longitude=0;

            public void run() {
                Looper.prepare();
                while (!stop) {
                    GPSTracker gps = new GPSTracker(MainActivity.this);
                    if (gps.canGetLocation()) {
                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                    } else {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GPSTracker gps = new GPSTracker(MainActivity.this);
                                gps.showSettingAlert();
                                Button b = (Button) findViewById(R.id.startRoute);
                                b.setEnabled(true);

                                Button a= (Button) findViewById(R.id.stopRoute);
                                a.setEnabled(false);
                                stop=true;
                            }
                        });
                        return;
                    }
                    if (latitude == 0 && longitude == 0) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Please consider to wait a few seconds before GPS is working. Try in a few seconds!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    if (coord_ponto(latitude, longitude) != null) {
                        Local novolocal = new Local(longitude, latitude, data.getTime());
                        novarota.adicionalocal(novolocal);
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getApplicationContext(), "Rota Concluida!", Toast.LENGTH_SHORT).show();
            }
        }).start();

        /*1-Rotas
          2-eliminar rota  send = rota; flag = 1
          3-request rota
          4-criar conta
          5-login
          6-alterar dados utilizador
         */
    }


    public Object senddata(Object send, int flag){


        NetworkTask networktask = new NetworkTask(send, flag); //Create initial instance so SendDataToNetwork doesn't throw an error.
        networktask.execute();

        while (networktask.getReceived() == 0){
            try{
                Thread.interrupted();
                Thread.sleep(100);
            }catch(Exception e){

            }
        }

        return networktask.getData();

    }

    public void login(View view){
        /*1-Rotas
          2-eliminar rota  send = rota; flag = 1
          3-request rota
          4-criar conta
          5-login
          6-alterar dados utilizador
         */
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        if(!username.getText().equals("") && !username.getText().equals("")) {
            eu.setUsername("" + username.getText());
            eu.setPassword("" + password.getText());

            int result = (int)senddata(eu, 5);

            if (result == -1){
                Toast.makeText(getApplicationContext(),"Autentificação falhou!", Toast.LENGTH_LONG).show();
            } else{
                eu.setID(result);
            //Toast.makeText(getApplicationContext(),"Autentificação falhou!", Toast.LENGTH_LONG).show();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                        setContentView(R.layout.activity_main);
                    }
                });
            }

        }
        else{
            Toast.makeText(getApplicationContext(),"Por favor , preencha os campos", Toast.LENGTH_SHORT).show();
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

        String user, pass;

        user = "" + username.getText();
        pass = "" + password.getText();

        if (!user.equals("") && !pass.equals("")) {
            eu.setUsername("" + username.getText());
            eu.setPassword("" + password.getText());

            Thread t =new Thread(new Runnable() {
                @Override
                public void run() {
                    final int result = (int) senddata(eu, 4);

                    if (result == -1) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Username not available", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        eu.setID(result);
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
            t.start();
        }
        else{
                Toast.makeText(getApplicationContext(), "Por favor , preencha os campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void para_rota(View view) throws InterruptedException {
        stop = true;
        Toast.makeText(getApplicationContext(),"Rota Concluida", Toast.LENGTH_SHORT).show();



        Button b = (Button) findViewById(R.id.startRoute);
        b.setEnabled(true);

        Button a= (Button) findViewById(R.id.stopRoute);
        a.setEnabled(false);

    }

    public Ponto coord_ponto(double lat, double longi){
        //lat = 40.200712 - x*0.00067388
        //long = -8.421352 + y*0.00097244

        double x = 40.200712;
        double y = -8.421352;
        int CoordX = 0;
        int CoordY = 0;
        while (x < lat){
            x -= 0.00067388;
            CoordX++;
            if (CoordX > 25) return null;
        }
        while (y < longi){
            y += 0.00067388;
            CoordY++;
            if (CoordY > 25) return null;
        }

        int lim = pontos.size();
        for (int i = 0; i < lim; i++){
            if (CoordX == pontos.get(i).getX()){
                if (CoordY == pontos.get(i).getY()){
                    return pontos.get(i);
                }
            }
        }

        return null;
    }
}
