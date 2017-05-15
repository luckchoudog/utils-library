package com.luckchoudog.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 更加理性的Handle
 *
 * @see { http://blog.csdn.net/luckchoudog/article/details/52851072 }
 */

public class StaticHandlerUtils {
    public static class StaticHandler extends Handler {
        private final WeakReference<Activity> mActivity;


        public StaticHandler(Activity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
