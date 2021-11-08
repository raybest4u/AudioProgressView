package com.ray.leanap;

import android.os.Bundle;
import android.widget.TextView;

import com.ray.leanap.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();


    private ActivityMainBinding binding;
    TextView tv;
    AudioProgressView apv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        tv = binding.sampleText;
        apv = binding.apPro;
//        ValueAnimator mValueAnimator1 = ValueAnimator.ofFloat(100.0f, 0.0f);
//        mValueAnimator1.setRepeatMode(ValueAnimator.RESTART);
//        mValueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
//        mValueAnimator1.setInterpolator(new LinearInterpolator());
//        mValueAnimator1.setDuration((int) (100f / 80 * 1000));
//        mValueAnimator1.addUpdateListener(animation ->
//                apv.setProcess ((float) animation.getAnimatedValue()));
//        mValueAnimator1.start();
//        tv.setText(stringFromJNI());
//        initPython();
//        callPythonCode();
    }

}