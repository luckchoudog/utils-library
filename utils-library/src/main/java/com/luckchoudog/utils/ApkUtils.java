package com.luckchoudog.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 获取App信息的类,包括获取当前App信息和安装包的信息
 * <p/>
 * <pre>
 * 信息包括其中有：
 * 安装包名称,安装包包名称,安装包版本信息,安装包图标
 * </pre>
 *
 * @author luckchoudog
 */
public class ApkUtils {

    /**
     * 安装一个apk文件
     *
     * @param context
     * @param uriFile
     */
    public static void install(Context context, File uriFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(uriFile), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 卸载一个app
     *
     * @param context
     * @param packageName
     */
    public static void uninstall(Context context, String packageName) {
        //通过程序的包名创建URI
        Uri packageURI = Uri.parse("package:" + packageName);
        //创建Intent意图
        Intent intent = new Intent(Intent.ACTION_DELETE, packageURI);
        //执行卸载程序
        context.startActivity(intent);
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName 应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 获取任何APK安装包App信息
     *
     * @param context 上下文
     * @param appDir  安装包包的路径,比如说下载在SD卡上的一个APK安装包
     * @return 包含安装包App信息的HashMap, 其中有AppName(安装包名称), AppPackageName(安装包包名称), AppVersion
     * (安装包版本信息),AppIcon(安装包图标)
     */
    public HashMap<String, Object> getApkMsg(Context context, String appDir) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(appDir, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            String appName = pm.getApplicationLabel(appInfo).toString();
            String packageName = appInfo.packageName;
            String version = info.versionName;
            Drawable icon = pm.getApplicationIcon(appInfo);
            result.put("AppName", appName);
            result.put("AppPackageName", packageName);
            result.put("AppVersion", version);
            result.put("AppIcon", icon);
        }
        return result;
    }
}
