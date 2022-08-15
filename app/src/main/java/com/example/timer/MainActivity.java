package com.example.timer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    String msg="Android:";
    private Button button1;
    private EditText loop;
    private EditText external;
    private TextView time1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loop=(EditText) findViewById(R.id.loop);
        external=(EditText) findViewById(R.id.external);
        time1=(TextView)findViewById(R.id.time);

        final String test=external.getText().toString();
        button1=(Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer second=Integer.parseInt(loop.getText().toString());
                Long mill=(long)(second*1000);


                for (int i=0;i<5;i++){
                    new CountDownTimer(mill,1000){
                        public void onTick(long mill){
                            button1.setClickable(false);
                            time1.setText(mill/1000+"s");
                        }

                        public void onFinish(){
                            button1.setClickable(true);
                        }
                    }.start();
                }
            }
        });

        Log.d(msg,"onCreate");
        Log.d(msg,loop.getText().toString());
        Log.d(msg,external.getText().toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(msg,"onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(msg,"onResume");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}