package com.neocean.app.neoceansoftnativefunctionutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User weixn
 * Date 2019/5/22
 * 手机组件调用工具类
 */
public class NativeFunctionUtils {

    /**
     * 调用系统发短信界面
     *
     * @param activity    Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessage(Context activity, String phoneNumber, String smsContent) {
        if (smsContent == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", smsContent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /**
     * 打开相册
     *
     * @param requestcode 响应码
     * @param activity    上下文
     */
    public static void toTakePicture(int requestcode, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, requestcode);

    }

    /**
     * 跳转到拨号界面
     *
     * @param activity
     */
    public static void toDial(Activity activity, String phoneNumber) {
        //跳转到拨号界面，同时传递电话号码
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        activity.startActivity(dialIntent);
    }

    /**
     * 打开系统浏览器
     *
     * @param activity
     * @param URL
     */

    public static void toWebView(Activity activity, String URL) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(URL));//Url 就是你要打开的网址
        intent.setAction(Intent.ACTION_VIEW);
        activity.startActivity(intent); //启动浏览器
    }

    /**
     * 分享文字内容
     */
    public static void shareText(Activity activity, String title, String content) {
        Intent intent1 = new Intent(Intent.ACTION_SEND);
        intent1.putExtra(Intent.EXTRA_TEXT, content);
        intent1.setType("text/plain");
        activity.startActivity(Intent.createChooser(intent1, title));
        /*
         *  自定义分享过滤
         */
//        List<ResolveInfo> resInfo = activity.getPackageManager().queryIntentActivities(intent1, 0);
//        if (!resInfo.isEmpty()) {
//            ArrayList<Intent> targetIntents = new ArrayList<Intent>();
//            for (ResolveInfo info : resInfo) {
//                ActivityInfo activityInfo = info.activityInfo;
//                if (activityInfo.packageName.contains("com.tencent.mobileqq")) {
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.setPackage(activityInfo.packageName);
//                    intent.putExtra(Intent.EXTRA_TEXT, content);
//                    intent.setClassName(activityInfo.packageName, activityInfo.name);
//                    targetIntents.add(intent);
//                }
//            }
//            Intent chooser = Intent.createChooser(targetIntents.remove(0), title);
//            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetIntents.toArray(new Parcelable[]{}));
//            activity.startActivity(chooser);
//        }


    }

    /**
     * 分享单张图片
     */
    public static void shareImage(Activity activity, String title, String filePath) {
        Intent intent2 = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(new File(filePath));
        intent2.putExtra(Intent.EXTRA_STREAM, uri);
        intent2.setType("image/*");
        activity.startActivity(Intent.createChooser(intent2, title));
    }

    /**
     * 分享多张图片
     */
    public static void shareMultiImage(Activity activity, String title, String[] filePaths) {
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        for (String path : filePaths) {
            Uri imageUri1 = Uri.fromFile(new File(path));
            imageUris.add(imageUri1);
        }
        Intent intent3 = new Intent();
        intent3.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent3.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        intent3.setType("image/*");
        activity.startActivity(Intent.createChooser(intent3, "share"));
    }

}
