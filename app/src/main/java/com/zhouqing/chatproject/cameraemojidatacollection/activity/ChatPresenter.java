package com.zhouqing.chatproject.cameraemojidatacollection.activity;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.zhouqing.chatproject.cameraemojidatacollection.db.SmsOpenHelper;
import com.zhouqing.chatproject.cameraemojidatacollection.provider.SmsProvider;
import com.zhouqing.chatproject.cameraemojidatacollection.util.ThreadUtil;

public class ChatPresenter implements ChatContract.Presenter {
    private Context mContext;
    private ChatContract.View mView;



    public ChatPresenter(Context context, ChatContract.View view) {
        this.mContext = context;
        this.mView = view;
    }

    @Override
    public void getDialogueMessage(final String fromAccount,final int type) {
        ThreadUtil.runOnThread(new Runnable() {
            @Override
            public void run() {
                final Cursor c = mContext.getContentResolver().query(
                        SmsProvider.URI_SMS, null,
                        "from_account = ? and type = ?",
                        new String[]{fromAccount,type+""}, null);

                //显示聊天消息
                ThreadUtil.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.showDialogueMessage(c);
                    }
                });
            }
        });
    }

    @Override
    public void sendMessage(final String clickAccount,final int type) {
        final String body = mView.getMessage();
        if (!TextUtils.isEmpty(body)) {
            ThreadUtil.runOnThread(new Runnable() {
                @Override
                public void run() {
                    ContentValues values = new ContentValues();

                    values.put(SmsOpenHelper.SmsTable.FROM_ACCOUNT, clickAccount);
                    values.put(SmsOpenHelper.SmsTable.BODY, body);
                    values.put(SmsOpenHelper.SmsTable.TYPE, type);
                    values.put(SmsOpenHelper.SmsTable.TIME, System.currentTimeMillis());
                    mContext.getContentResolver().insert(SmsProvider.URI_SMS, values);
                    ThreadUtil.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.clearMessage();
                        }
                    });
                }
            });
        }

    }
}
