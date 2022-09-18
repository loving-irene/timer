package com.example.timer;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.*;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    String msg="Android:";
    private Button startBut;
    private Button stopBut;
    //倒计时时间 s
    private EditText loopTime;
    private Integer loopTime2;
    private Long loopTime3;
    private Integer tmpLoop;
    //间隔时间 s
    private EditText external;
    private Integer external2;
    private Long external3;
    private Integer tmpExternal;
    //显示框
    private TextView showTime;

    private Timer timer=new Timer();
    private TimerTask timerTask;
    private MediaPlayer go;
    private MediaPlayer rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startBut=(Button) findViewById(R.id.button);
        stopBut=(Button) findViewById(R.id.button2);

//        go=MediaPlayer.create(this,R.raw.go);
//        rest=MediaPlayer.create(this,R.raw.rest);
//        Log.i(msg,go.toString());
//        Log.i(msg,rest.toString());

        startBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loopTime=(EditText) findViewById(R.id.loop);
                loopTime2=Integer.parseInt(loopTime.getText().toString());
                tmpLoop=loopTime2;
                external=(EditText) findViewById(R.id.external);
                external2=Integer.parseInt(external.getText().toString());
                tmpExternal=external2;
                showTime=(TextView)findViewById(R.id.time);
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (loopTime2 >= 0) {
//                            try{
//                                mediaPlayer.prepare();
//                                mediaPlayer.start();
//                            }catch (IOException e){
//                                Log.i(msg,"media player error:"+e.getMessage());
//                            }
                            String str = "<font color='#39d916'>" + loopTime2 + "</font>";
                            showTime.setText(Html.fromHtml(str));
                            loopTime2 -= 1;
                        } else {
                            String str = "<font color='#d63b1c'>" + external2 + "</font>";
                            if (external2 > 0) {
                                Log.i(msg, "444 loopTime2:" + loopTime2 + " external2:" + external2);
                                showTime.setText(Html.fromHtml(str));
                                external2 -= 1;
                            } else {
                                Log.i(msg, "555 loopTime2:" + loopTime2 + " external2:" + external2);
                                showTime.setText(Html.fromHtml(str));
                                loopTime2 = tmpLoop;
                                external2 = tmpExternal;

                            }
                        }
                    }
                };
                startBut.setClickable(false);
                startBut.setVisibility(View.INVISIBLE);
                stopBut.setVisibility(View.VISIBLE);
                timer.schedule(timerTask,0,1000);
            }
        });

        stopBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(msg,"stop button");
                startBut.setClickable(true);
                timerTask.cancel();
                startBut.setVisibility(View.VISIBLE);
                stopBut.setVisibility(View.INVISIBLE);
            }
        });

        Log.i(msg,"onCreate");
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