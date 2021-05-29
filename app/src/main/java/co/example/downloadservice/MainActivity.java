package co.example.downloadservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void download(View view){

        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);
        System.out.println("DOWNLOAD TIKLANDI");
    }


    public void stop(View view){
        Intent intent = new Intent(this,DownloadService.class);
        stopService(intent);
        System.out.println("STOP TIKLANDI");



    }


}