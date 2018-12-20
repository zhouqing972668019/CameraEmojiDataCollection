package com.zhouqing.chatproject.cameraemojidatacollection.activity;

import android.database.Cursor;

import com.zhouqing.chatproject.cameraemojidatacollection.common.BasePresenter;
import com.zhouqing.chatproject.cameraemojidatacollection.common.BaseView;


public class ChatContract {
    public interface Presenter extends BasePresenter {
        void getDialogueMessage(String clickAccout);

        void sendMessage(String clickAccout);

        void bindIMService();

        void unbindIMService();
    }

    public interface View extends BaseView<Presenter> {
        void showDialogueMessage(Cursor cursor);

        String getMessage();

        void clearMessage();
    }
}
