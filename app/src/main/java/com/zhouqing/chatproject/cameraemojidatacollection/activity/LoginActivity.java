package com.zhouqing.chatproject.cameraemojidatacollection.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhouqing.chatproject.cameraemojidatacollection.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUsername;

    //记住登录用户名
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        etUsername = findViewById(R.id.et_username);
        String userName = pref.getString("username","");
        etUsername.setText(userName);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(LoginActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
                }
                else{
                    login();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    login();
                } else {
                    System.out.println("You denied the permission!");
                }
                break;
            default:
        }
    }

    public void login(){
        if(TextUtils.isEmpty(etUsername.getText())){
            toast("用户名不能为空!");
            return;
        }
        editor = pref.edit();
        editor.putString("username",etUsername.getText().toString());
        editor.apply();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void toast(String content){
        Toast.makeText(LoginActivity.this,content,Toast.LENGTH_SHORT).show();
    }
}
