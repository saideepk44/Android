package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int sec = 60;
    TextView time;
    ImageView shiba1;
    ImageView shiba2;
    ImageView shiba3;
    ImageView shiba4;
    ImageView shiba5;
    ImageView shiba6;
    ImageView shiba7;
    ImageView shiba8;
    ImageView shiba9;
    Button start;
    int points;
    TextView score;
    boolean moleNotOut;
    ScaleAnimation comingIn, goingOut;
    int current;
    boolean notStarted;
    LinearLayout linearLayout;
    int t;
    int keptSeconds;
    boolean animatingRN;
    int interval;
    ArrayList<ImageView> imageViews = new ArrayList<>();
    ConstraintLayout constraintLayout;
    public Handler uiUpdates = new Handler();
    static final String LAUNCH_EXTRA = "ABC123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shiba1 = findViewById(R.id.id_imageView);
        shiba2 = findViewById(R.id.id_imageView2);
        shiba3 = findViewById(R.id.id_imageView3);
        shiba4 = findViewById(R.id.id_imageView4);
        shiba5 = findViewById(R.id.id_imageView5);
        shiba6 = findViewById(R.id.id_imageView6);
        shiba7 = findViewById(R.id.id_imageView7);
        shiba8 = findViewById(R.id.id_imageView8);
        shiba9 = findViewById(R.id.id_imageView9);

        linearLayout = findViewById(R.id.id_linear);

        moleNotOut = true;

        animatingRN = false;

        notStarted = true;

        keptSeconds = 0;

        imageViews.add(shiba1);
        imageViews.add(shiba2);
        imageViews.add(shiba3);
        imageViews.add(shiba4);
        imageViews.add(shiba5);
        imageViews.add(shiba6);
        imageViews.add(shiba7);
        imageViews.add(shiba8);
        imageViews.add(shiba9);

        time = findViewById(R.id.timeLeft);
        start = findViewById(R.id.startButton);
        score = findViewById(R.id.score);
        points = 0;
        comingIn = new ScaleAnimation(.0f,1.0f,.0f,1.0f, Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
        comingIn.setDuration(1);
        comingIn.setFillAfter(true);
        goingOut = new ScaleAnimation(1.0f,.0f,1.0f,.0f, Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
        goingOut.setDuration(1);
        goingOut.setFillAfter(true);
        comingIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animatingRN = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animatingRN = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        goingOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animatingRN = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animatingRN = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        for(int i = 0; i < 9; i++)
        {
            imageViews.get(i).setVisibility(View.INVISIBLE);
            imageViews.get(i).setAnimation(goingOut);
            imageViews.get(i).setClickable(false);
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setClickable(false);

                final Timer timer = new Timer();
                final TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if(sec>1)
                        {
                            if(sec%1==0)//change 1 to 6 later
                            {
                                interval = (int)(Math.random()*2);
                                t = (int)(Math.random()*2);
                                if(moleNotOut&&!animatingRN)
                                {
                                    uiUpdates.post(comeIn);
                                    moleNotOut = false;
                                    keptSeconds = 0;
                                }
                                else if(interval==1&&keptSeconds>0)
                                {
                                    uiUpdates.post(getOut);
                                    moleNotOut = true;
                                    keptSeconds = 0;
                                }
                            }
                            sec--;
                            keptSeconds++;
                            Log.d("TAG","TAG2");
                            uiUpdates.post(randomTimedTask);

                        }
                        else
                        {
                            sec--;
                            Intent launchIntent = new Intent(MainActivity.this, MainActivity2.class);
                            launchIntent.putExtra(LAUNCH_EXTRA, score.getText().toString());
                            startActivity(launchIntent);
                            timer.cancel();
                        }
                    }
                };
                timer.scheduleAtFixedRate(timerTask,20,1000);
            }
        });

        shiba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    sec-=5;
                    Toast.makeText(MainActivity.this,"TIME TAKEN",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    points++;
                    score.setText(points+"");
                    addImageViews();
                }
                shiba1.setClickable(false);
                uiUpdates.post(getOut);
                keptSeconds = 0;
            }
        });
        shiba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    sec-=5;
                    Toast.makeText(MainActivity.this,"TIME TAKEN",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    points++;
                    score.setText(points+"");
                    addImageViews();
                }
                shiba2.setClickable(false);
                uiUpdates.post(getOut);
                keptSeconds = 0;
            }
        });
        shiba3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    sec-=5;
                    Toast.makeText(MainActivity.this,"TIME TAKEN",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    points++;
                    score.setText(points+"");
                    addImageViews();
                }
                shiba3.setClickable(false);
                uiUpdates.post(getOut);
                keptSeconds = 0;
            }
        });
        shiba4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    sec-=5;
                    Toast.makeText(MainActivity.this,"TIME TAKEN",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    points++;
                    score.setText(points+"");
                    shiba4.setClickable(false);
                    addImageViews();
                }
                shiba4.setClickable(false);
                uiUpdates.post(getOut);
                keptSeconds = 0;
            }
        });
        shiba5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    sec-=5;
                    Toast.makeText(MainActivity.this,"TIME TAKEN",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    points++;
                    score.setText(points+"");
                    addImageViews();
                }
                shiba5.setClickable(false);
                uiUpdates.post(getOut);
                keptSeconds = 0;
            }
        });
        shiba6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    sec-=5;
                    Toast.makeText(MainActivity.this,"TIME TAKEN",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    points++;
                    score.setText(points+"");
                    addImageViews();
                }
                shiba6.setClickable(false);
                uiUpdates.post(getOut);
                keptSeconds = 0;
            }
        });
        shiba7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    sec-=5;
                    Toast.makeText(MainActivity.this,"TIME TAKEN",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    points++;
                    score.setText(points+"");
                    addImageViews();
                }
                shiba7.setClickable(false);
                uiUpdates.post(getOut);
                keptSeconds = 0;
            }
        });
        shiba8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    sec-=5;
                    Toast.makeText(MainActivity.this,"TIME TAKEN",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    points++;
                    score.setText(points+"");
                    addImageViews();
                }
                shiba8.setClickable(false);
                uiUpdates.post(getOut);
                keptSeconds = 0;
            }
        });
        shiba9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t==0)
                {
                    sec-=5;
                    Toast.makeText(MainActivity.this,"TIME TAKEN",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    points++;
                    score.setText(points+"");
                    addImageViews();
                }
                shiba9.setClickable(false);
                uiUpdates.post(getOut);
                keptSeconds = 0;
            }
        });


    }
    private Runnable randomTimedTask = new Runnable(){
        @Override
        public void run() {
            time.setText(sec+"");
            //uiUpdates.post(this);
        }
    };
    private Runnable comeIn = new Runnable() {
        @Override
        public void run() {
            imageViews.get(current).setImageResource(R.drawable.shiba);
            current = (int)(Math.random()*9);
            if(t==0)
            {
                imageViews.get(current).setImageResource(R.drawable.bomb);
            }
            if(notStarted)
            {
                shiba1.setClickable(false);
                shiba2.setClickable(false);
                shiba3.setClickable(false);
                shiba4.setClickable(false);
                shiba5.setClickable(false);
                shiba6.setClickable(false);
                shiba7.setClickable(false);
                shiba8.setClickable(false);
                shiba9.setClickable(false);
            }
            imageViews.get(current).setAnimation(comingIn);
            imageViews.get(current).setClickable(true);
            //imageViews.get(current).setVisibility(View.VISIBLE);
            //uiUpdates.post(this);
        }
    };
    private Runnable getOut = new Runnable() {
        @Override
        public void run() {
            //imageViews.get(current).setVisibility(View.INVISIBLE);
            imageViews.get(current).setClickable(false);
            imageViews.get(current).setAnimation(goingOut);
            //uiUpdates.post(this);

        }
    };
    public void addImageViews()
    {
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setImageResource(R.drawable.emoji);

        addPicProgramatically(imageView,40,40);
    }
    public void addPicProgramatically(ImageView imageView, int width, int height)
    {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.setMargins(0,10,0,10);
        imageView.setLayoutParams(layoutParams);
        linearLayout.addView(imageView);
    }
}