package com.example.hankarun.testprojectsamp;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bruce.pickerview.LoopScrollListener;
import com.bruce.pickerview.LoopView;
import com.wunderlist.slidinglayer.SlidingLayer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RelativeLayout view;
    boolean isWhite = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final SlidingLayer layer = (SlidingLayer) findViewById(R.id.slidingPlateDetails);

        layer.setSlidingEnabled(false);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layer.openLayer(true);
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        LoopView loopView = (LoopView) findViewById(R.id.loopView);
        loopView.setInitPosition(0);
        loopView.setCanLoop(false);
        loopView.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {

            }
        });
        loopView.setTextSize(25);//must be called before setDateList
        loopView.setDataList(getList(0,12));

        LoopView loopView1 = (LoopView) findViewById(R.id.loopView1);
        loopView1.setInitPosition(0);
        loopView1.setCanLoop(false);
        loopView1.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {

            }
        });
        loopView1.setTextSize(25);//must be called before setDateList
        loopView1.setDataList(getList(0,59));


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button)view).setText("Activate");
                animateBackground();
            }
        });

        view = (RelativeLayout) findViewById(R.id.content_main);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layer.closeLayer(true);
            }
        });

    }

    private void animateBackground()
    {
        final float[] from = new float[3],
                to =   new float[3];

        if(isWhite)
        {
            Color.colorToHSV(Color.parseColor("#FFFFFFFF"), from);   // from white
            Color.colorToHSV(Color.parseColor("#FFFF0000"), to);     // to red
            isWhite = false;
        }else
        {
            Color.colorToHSV(Color.parseColor("#FFFF0000"), from);   // from white
            Color.colorToHSV(Color.parseColor("#FFFFFFFF"), to);     // to red
            isWhite = true;
        }


        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);   // animate from 0 to 1
        anim.setDuration(600);                              // for 300 ms

        final float[] hsv  = new float[3];                  // transition color
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override public void onAnimationUpdate(ValueAnimator animation) {
                // Transition along each axis of HSV (hue, saturation, value)
                hsv[0] = from[0] + (to[0] - from[0])*animation.getAnimatedFraction();
                hsv[1] = from[1] + (to[1] - from[1])*animation.getAnimatedFraction();
                hsv[2] = from[2] + (to[2] - from[2])*animation.getAnimatedFraction();

                view.setBackgroundColor(Color.HSVToColor(hsv));
            }
        });

        anim.start();
    }

    public ArrayList<String> getList(int start, int end){
        ArrayList<String> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add("" + i);
        }
        return list;
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
