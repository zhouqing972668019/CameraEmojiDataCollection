package com.zhouqing.chatproject.cameraemojidatacollection.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.zhouqing.chatproject.cameraemojidatacollection.R;
import com.zhouqing.chatproject.cameraemojidatacollection.util.Constant;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NiceSpinner spEmotion;
    private int selectedEmotion = Constant.ANGRY;
    private List<String> emotionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spEmotion = findViewById(R.id.sp_emotion);
        emotionList = new LinkedList<>(Arrays.asList(Constant.EMOTION_ARRAY));
        spEmotion.attachDataSource(emotionList);
        spEmotion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toast("select:"+position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void toast(String content){
        Toast.makeText(MainActivity.this,content,Toast.LENGTH_SHORT).show();
    }
}
