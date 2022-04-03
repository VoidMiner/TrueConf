package com.example.trueconf;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Animation {

    private final long TIME_DELAY = 5000;
    private final int MOVE_STEP = 10;
    private final int PERIOD_FOR_TIMER_ANIMATION = 50;

    private Context context;
    private TextView activity_main;
    private Timer timerAnimation;

    private boolean downMove = true;
    private boolean timerActivationFirst = false;


    public Animation(Context context) {
        this.context = context;
    }

    public void initHelloTextView(TextView textView) {
        activity_main = textView;
        activity_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerActivationFirst) timerAnimation.cancel();
            }
        });
    }

    public void initContainer(ConstraintLayout container) {
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (timerActivationFirst) timerAnimation.cancel();

                setColorEnglishAndRussianLocales(Color.RED, Color.BLUE);

                startAnimation(container.getHeight());

                move(motionEvent.getX(), motionEvent.getY());
                return false;
            }
        });
    }

    private void setColorEnglishAndRussianLocales(int colorEnglishLocale, int colorRussianLocale) {
        if (activity_main.getCurrentTextColor() == Color.WHITE) {

            activity_main.setTextColor(colorRussianLocale);

            String localesEnglish;
            localesEnglish = (String) activity_main.getText();

            int numberOfEnglishletters = localesEnglish.replaceAll("[А-Я\\d]", "").length();

            Spannable spannable = new SpannableString(localesEnglish);
            spannable.setSpan(new ForegroundColorSpan(colorEnglishLocale), 0, numberOfEnglishletters, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            activity_main.setText(spannable);
        }
    }

    private void startAnimation(float sectorAnimation) {
        TranslateAnimation animationPause = new TranslateAnimation(
                android.view.animation.Animation.ABSOLUTE, 0,
                android.view.animation.Animation.ABSOLUTE, 0,
                android.view.animation.Animation.ABSOLUTE, 0,
                android.view.animation.Animation.ABSOLUTE, 0);
        animationPause.setDuration(TIME_DELAY);
        animationPause.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            @Override
            public void onAnimationStart(android.view.animation.Animation animation) {


            }

            @Override
            public void onAnimationEnd(android.view.animation.Animation animation) {

                startAnimationMoveDownOrMoveUp(sectorAnimation);

            }

            @Override
            public void onAnimationRepeat(android.view.animation.Animation animation) {
            }
        });
        activity_main.startAnimation(animationPause);
    }

    private void startAnimationMoveDownOrMoveUp(float sectorAnimation) {
        timerActivationFirst = true;

        timerAnimation = new Timer(true);
        timerAnimation.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (activity_main.getY() + activity_main.getHeight() <= sectorAnimation && downMove) {
                    activity_main.setY(activity_main.getY() + MOVE_STEP);
                } else {
                    downMove = false;
                }

                if (activity_main.getY() >= 0 && downMove == false) {
                    activity_main.setY(activity_main.getY() - MOVE_STEP);
                } else {
                    downMove = true;
                }
            }
        }, 0, PERIOD_FOR_TIMER_ANIMATION);
    }

    private void move(float x, float y) {
        activity_main.setX(x - activity_main.getWidth() / 2);
        activity_main.setY(y - activity_main.getHeight() / 2);
    }

    public void setText(String text) {
        String country = Locale.getDefault().getLanguage();
        Log.e("L", country);
        if (country == "US") {
            activity_main.setText("HELLO");
        }else{
            activity_main.setText("ПРИВЕТ");
        }
        }
    }






