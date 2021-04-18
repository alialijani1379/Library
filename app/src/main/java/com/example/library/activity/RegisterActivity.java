package com.example.library.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //<editor-fold desc="--Declaration--">
    private TextView textView;
    private EditText edtName, edtLastName, edtEmail, edtPassword;
    private Button btnRegister;
    private ImageView imgPassword;
    private String name, lastName, email, pass;
    private Animation animation;
    //</editor-fold>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_txt);
        textView.setAnimation(animation);
        btnRegister.setOnClickListener(this);
        imgPassword.setOnClickListener(this);
        btnRegister.setBackgroundResource(R.drawable.shape_btn);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_register:
                if (isNetworkConnected()) {
                    name = edtName.getText().toString().trim();
                    lastName = edtLastName.getText().toString().trim();
                    email = edtEmail.getText().toString().trim();
                    pass = edtPassword.getText().toString().trim();
//                    if (name.isEmpty()) {
//                        edtName.setError("Please enter name");
//                    } else if (lastName.isEmpty()) {
//                        edtLastName.setError("Please enter lastName");
//                    } else if (email.isEmpty()) {
//                        edtEmail.setError("Please enter email");
//                    } else if (pass.isEmpty()) {
//                        edtPassword.setError("Please enter password");
//                    } else
                    if (!email.isEmpty()) {
                        validateEmail();
                    }
                } else {
                    showDialog();
                }
                break;
            case R.id.img_password:
                if (IsPasswordType(RegisterActivity.this, edtPassword)) {
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    imgPassword.setImageResource(R.drawable.ic_baseline_visibility_off);
                } else {
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    imgPassword.setImageResource(R.drawable.ic_baseline_visibility);
                }
                edtPassword.setSelection(edtPassword.getText().toString().length());
                break;
        }
    }

    private void retrieveSharedPreferences() {
        SharedPreferences.Editor editor = getSharedPreferences("register", MODE_PRIVATE).edit();
        editor.putString("name", name);
        editor.putString("lastName", lastName);
        editor.putString("email", email);
        editor.putString("pass", pass);
        editor.putBoolean("isRegister", true);
        editor.apply();

        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateEmail() {
        String val = edtEmail.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        if (val.isEmpty()) {
//            edtEmail.setError("Field can not be empty");
//            return false;
//        }
        if (!val.matches(checkEmail)) {
            edtEmail.setError("Please enter the correct email");
            return false;
        } else {
            edtEmail.setError(null);
            retrieveSharedPreferences();
            return true;
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
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

    public static boolean IsPasswordType(Context context, EditText editText) {
        boolean type = false;
        final int inputType = editText.getInputType();
        switch (inputType) {
            case (InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT): {
                type = true;
            }
            break;
            case (InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT): {
                type = false;
            }
            break;
        }
        return type;
    }

    private void findViews() {
        textView = findViewById(R.id.txt_register);
        edtName = findViewById(R.id.edt_name_register);
        edtLastName = findViewById(R.id.edt_last_name_register);
        edtEmail = findViewById(R.id.edt_email_register);
        edtPassword = findViewById(R.id.edt_password_register);
        imgPassword = findViewById(R.id.img_password);
        btnRegister = findViewById(R.id.btn_register);
    }

}