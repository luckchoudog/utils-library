package com.luckchoudog.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

/**
 * 获取屏幕宽度和高度的信息类
 * 
 * @author luckchoudog
 */
public class ScreenUtils {
	private static String TAG = ScreenUtils.class.getSimpleName();
	/**
	 * 屏幕的宽度高度
	 */
	private static int screenW, screenH;
	/**
	 * 状态栏高度
	 */
	private static int statusBarHeight;

	/**
	 * 获取屏幕的宽度（单位 px）
	 * 
	 * @param mActivity
	 * @return int类型的屏幕宽度
	 */
	public static int getScreenWidth(Activity mActivity) {
		DisplayMetrics metric = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenW = metric.widthPixels; // 屏幕宽度（像素）
		return screenW;
	}

	/**
	 * 获取屏幕的高度（单位 px）
	 * 
	 * @param mActivity
	 * @return int类型的屏幕高度
	 */
	public static int getScreenHeight(Activity mActivity) {
		DisplayMetrics metric = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenH = metric.heightPixels; // 屏幕高度（像素）
		return screenH;
	}

	/**
	 * 获取屏幕的状态栏高度（单位 px）
	 * 
	 * @param mActivity
	 * @return int类型的状态栏高度
	 */
	public static int getStatusBarHeight(Activity mActivity) {
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object o = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = (Integer) field.get(o);
			statusBarHeight = mActivity.getResources().getDimensionPixelSize(x);
			return statusBarHeight;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
