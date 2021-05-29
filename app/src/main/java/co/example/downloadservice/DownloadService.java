package co.example.downloadservice;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class DownloadService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // eger asyntasking class yerine onstartcommand altında yani mainthread altında yapsaydık cökecekti

        AsyncTaskingClass asyncTaskingClass = new AsyncTaskingClass();
        try {
            asyncTaskingClass.execute("https://balm-e.co.uk/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    class AsyncTaskingClass extends AsyncTask<String,Void,String> {

        // bunları apı ile yapmıstık simdi
        // apı olmadan siteden http verileri inderecegiz
        // apı olmadan html kodları cekerek ayıklar veriyi indiririz

        @Override
        protected String doInBackground(String... strings) {

            String result = "";

            URL url;
            HttpURLConnection httpURLConnection = null;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                //gelen verileri almak icin inputsteram kullanırız.

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                // gelen input streamı okuyacak bir obje olusturuyorum.

                int data = inputStreamReader.read(); // data bittiğinde -1 değerini verecek

                while (data != -1) { // verileri karaktere dönüştürürüz

                    char current = (char) data;
                    result += current;

                    data = inputStreamReader.read();

                }

                return result; // burada cıkan sonucu post execute a döndürmeye calısıyorum

            } catch (Exception e) {


            return "Failed";


        }

    }
        @Override
        protected void onPostExecute(String s) {
            // aldıgımız stringi yazdırabiliyor muyuz ona bakacagız burada.

            System.out.println("Result" + s);


            super.onPostExecute(s); // sonucumuuzn s si


        }
    }
}
