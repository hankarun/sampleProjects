package com.example.hankarun.testprojectsamp;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    Button activateAnimation;
    TextView hourText;
    TextView h1;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        activateAnimation = (Button) findViewById(R.id.button3);
        activateAnimation.setOnClickListener(this);
        hourText = (TextView) findViewById(R.id.textView3);
        progressBar = (ProgressBar) findViewById(R.id.ProgressBar);
        h1 = (TextView) findViewById(R.id.textView4);
    }

    @Override
    public void onClick(View view) {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, 99);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int count = (int)animation.getAnimatedValue();
                hourText.setText(String.valueOf( count % 60));
                h1.setText(String.valueOf((int)(count / 60)));
                progressBar.setProgress((int)animation.getAnimatedValue());
            }
        });
        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round(startValue + (endValue - startValue) * fraction);
            }
        });
        animator.setDuration(1000);
        animator.start();
    }
}
