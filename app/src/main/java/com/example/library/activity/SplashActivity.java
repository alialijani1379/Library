package com.example.library.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;

public class SplashActivity extends AppCompatActivity {
    //<editor-fold desc="-Declaration--">
    private TextView textView;
    private ProgressBar progressBar;
    private Animation animation;

    //</editor-fold>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findView();

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_txt);
        textView.setAnimation(animation);
        progressBar.setVisibility(View.VISIBLE);
        SharedPreferences register = getSharedPreferences("register", MODE_PRIVATE);

        if (isNetworkConnected()) {
            if (register.getBoolean("isRegister", false)) {
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }, 3000);
            } else {
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }, 3000);
            }
        } else {
            progressBar.setVisibility(View.GONE);
            showDialog();
        }

    }

    private void showDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setMessage("لطفا به اینترنت متصل شوید");
        ad.setCancelable(false);
        ad.setPositiveButton("اتصال به اینترنت", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });
        ad.setNegativeButton("بستن", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        ad.create().show();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void findView() {
        textView = findViewById(R.id.txt_library);
        progressBar = findViewById(R.id.progressBar);
    }

}