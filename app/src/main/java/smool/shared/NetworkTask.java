package smool.shared;

        import android.os.AsyncTask;
        import android.util.Log;

        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.net.InetSocketAddress;
        import java.net.Socket;
        import java.net.SocketAddress;


public class NetworkTask extends AsyncTask<Void, Object, Boolean> {
        Socket nsocket; //Network Socket
        ObjectInputStream ois;
        ObjectOutputStream oos;

        Object send;
        int flag;


        int received = 0;
        Object data;

    public int getReceived() {
        return received;
    }

    public Object getData() {
        return data;
    }

    public NetworkTask(Object send, int flag){
            this.send = send;
            this.flag = flag;
        }

        @Override
        protected void onPreExecute() {
            Log.i("AsyncTask", "onPreExecute");
        }

        @Override
        protected Boolean doInBackground(Void... params) { //This runs on a different thread
            boolean result = false;
            try {

                Log.i("AsyncTask", "doInBackground: Creating socket");

                SocketAddress sockaddr = new InetSocketAddress("172.20.10.3", 11111);
                nsocket = new Socket();
                Log.d("minhas", "vou conectar");
                nsocket.connect(sockaddr); //10 second connection timeout

                Log.d("minhas", "vou mandar");
                if (nsocket.isConnected()) {
                    Log.d("minhas", "ri me");
                    oos = new ObjectOutputStream(nsocket.getOutputStream());
                    ois = new ObjectInputStream(nsocket.getInputStream());



                    Log.d("minhas", "lololo");
                    oos.writeObject(flag);


                    User user = (User)send;
                    Log.d("minhas", user.getUsername());
                    oos.writeObject(user);



                    Object read = (Object) ois.readObject();
                    Log.d("minhas", "recebi alguma cena");
                    this.data = read;
                    received = 1;

                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("AsyncTask", "doInBackground: IOException");
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("AsyncTask", "doInBackground: Exception");
                result = true;
            } finally {
                try {
                    //  nis.close();
                    //nos.close();
                    nsocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("AsyncTask", "doInBackground: Finished");
            }
            return result;
        }





        protected void onProgressUpdate(Object value) {


        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                //Toast.makeText(getApplicationContext(), "A ligação Caiu", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(getApplicationContext(), "A ligação Terminou", Toast.LENGTH_LONG).show();
            }

        }

    }
