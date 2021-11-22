package com.example.hw_10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    private ProgressBar prog;
    private TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prog = (ProgressBar)findViewById(R.id.pBar);
        prog.setMax(100);
        t = (TextView) findViewById(R.id.tView);
        LocalBroadcastManager.getInstance(this).registerReceiver(brod_reciever,
                new IntentFilter("myintent"));
    }
    private BroadcastReceiver brod_reciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            handleMessage(intent);
        }
    };
    private void handleMessage(Intent msg)
    {
        Bundle data = msg.getExtras();
        prog.setProgress(data.getInt("progress"));
        int pr = data.getInt("progress");
        t.setText(pr+"/"+prog.getMax());
    }
    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}