package com.zhouqing.chatproject.cameraemojidatacollection.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class SmsOpenHelper extends SQLiteOpenHelper {
    public static final String T_SMS = "t_sms";

    public class SmsTable implements BaseColumns {
        /**
         * from_account;//发送者
         * body//消息内容
         * type//消息类型
         * time//发送时间
         */
        public static final String FROM_ACCOUNT = "from_account";
        public static final String BODY = "body";
        public static final String TYPE = "type";
        public static final String TIME = "time";

    }

    public SmsOpenHelper(Context context) {
        super(context, "sms.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE t_sms (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "from_account TEXT," +
                "body TEXT," +
                "type integer," +
                "time TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
