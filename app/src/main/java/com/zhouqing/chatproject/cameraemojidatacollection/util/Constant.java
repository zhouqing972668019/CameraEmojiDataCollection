package com.zhouqing.chatproject.cameraemojidatacollection.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

public class Constant {
    //0=Angry, 1=Disgust, 2=Fear, 3=Happy, 4=Sad, 5=Surprise, 6=Neutral
    public static final int ANGRY = 0;
    public static final int DISGUST = 1;
    public static final int FEAR = 2;
    public static final int HAPPY = 3;
    public static final int SAD = 4;
    public static final int SURPRISE = 5;
    public static final int NEUTRAL = 6;

    public static final String[] EMOTION_ARRAY = {"Angry","Disgust","Fear","Happy","Sad","Surprise","Neutral"};

    public static final int EMOTION_TOTAL_NUM = 5; //每一组情绪采集的数据组数量

    //项目存储文件的路径
    public static String PROJECT_FILE_PATH
            = Environment.getExternalStorageDirectory() + "/CameraEmojiDataCollection/";

    public static int[] readCompletedNums(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String completedNums = sharedPreferences.getString("completedNums","");
        int[] answer = new int[7];
        if(completedNums == null || completedNums.equals("")){
            return answer;
        }
        String[] elements = completedNums.split(",");
        for(int i=0;i<elements.length;i++){
            answer[i] = Integer.parseInt(elements[i]);
        }
        return answer;
    }

    public static void saveCompletedNums(Context context, int[] completedNums){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String answer = "";
        for(int i=0;i<completedNums.length;i++){
            answer += completedNums[i] + ",";
        }
        editor.putString("completedNums",answer.substring(0,answer.length()-1));
        editor.apply();
    }
}
