package com.example.photoskip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    }
    private int seconds = 5;
    private boolean skipped = false;
   @Override
    protected  void onResume(){
       super.onResume();
       Thread thread =new Thread(new Runnable() {
           @Override
           public void run() {
               while (seconds >0){
                   if(skipped)return;
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Button btnSkip =findViewById(R.id.btnSkip);
                           btnSkip.setText("跳过 |"+seconds+"秒");
                       }
                   });
                   seconds--;
                   try {
                       Thread.sleep(1000);
                   }catch (InterruptedException e){
                   }
               }
               skip(null);
           }
       });
       thread.start();
   }
   public void skip (View view){
       skipped =true;
       Intent intent=new Intent();
       intent.setClass(MainActivity1.this, MainActivity2.class);
       startActivity(intent);
       finish();
   }
}