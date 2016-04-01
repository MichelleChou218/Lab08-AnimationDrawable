package com.example.student.lab08_animationdrawable;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView m_img_duke;
    private AnimationDrawable m_frame_animation;
    private TextView m_tv_message;

    private View m_view_logo;
    private TextView m_logo_name;
    private TextView m_view_message;

    private TypedArray mNbaLogos;
    private int mNbaLogosCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFrameAnimation();
        intiNbaLogos();
    }

    private void intiNbaLogos() {
        mNbaLogos = getNbaLogos();
        mNbaLogosCount = getNbaLogos().length();
        m_view_logo.setBackground(mNbaLogos.getDrawable(0));
    }

    public TypedArray getNbaLogos() {
        TypedArray logos = getResources().obtainTypedArray(R.array.nba_logos);
        return logos;
    }

    private void initView() {
        m_img_duke = (ImageView)findViewById(R.id.img_duke);
        m_tv_message = (TextView)findViewById(R.id.view_message);

        m_view_logo = findViewById(R.id.view_logo);
        m_logo_name = (TextView)findViewById(R.id.tv_logo_name);
        m_view_message = (TextView)findViewById(R.id.view_message);
    }

    private void initFrameAnimation() {
        m_img_duke.setBackgroundResource(R.drawable.frame_animation);
        m_img_duke.setAlpha(0.9f); //設定透明度
        m_frame_animation = (AnimationDrawable)m_img_duke.getBackground();
    }

    public void click(View view) {
        switch(view.getId()) {
            case R.id.btn_start:
                m_frame_animation.start();
                break;
            case R.id.btn_stop:
                m_frame_animation.stop();
                break;
            case R.id.btn_5_secs:
                animation5secs();
                break;
        }
    }

    private Handler m_Handler = new Handler();

    private void animation5secs() {
       int delayMillis = 5 * 1000;
        Runnable task = new Task();

        m_frame_animation.start();
        boolean result = m_Handler.postDelayed(task, delayMillis);
        m_tv_message.setText(result ? "交付成功" : "交付失敗");
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            m_frame_animation.stop();
            m_tv_message.setText("時間到");
        }
    }

}
