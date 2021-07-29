package com.example.broadcastreciver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mbtnsend;
    private TextView tvview;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        mbtnsend=findViewById(R.id.btnnext);
        tvview=findViewById(R.id.tvtext);
        registerLocalreceiver();
        mbtnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.name.praveen");
                intent.putExtra("Key","Hello Masai School");
                localBroadcastManager.sendBroadcast(intent);

            }
        });

    }
    private void registerLocalreceiver(){
        localReceiver=new LocalReceiver();
        IntentFilter intentFilter=new IntentFilter("com.name.praveen");
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
    private class LocalReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent !=null){
                String data=intent.getStringExtra("Key");
                tvview.setText(data);
            }

        }
    }
}