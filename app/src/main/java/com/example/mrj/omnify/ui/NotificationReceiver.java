package com.example.mrj.omnify.ui;


import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import com.example.mrj.omnify.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

/**
 * Created by MR.J on 10/3/2016.
 */
public class NotificationReceiver extends BroadcastReceiver{

    NotificationManager nm;
    public static int  notificationId = 0;
    String data, campaignList,campaignName, contentPushText;
    JSONObject jsObj;

    Random random = new Random();
    int m = random.nextInt(9999 - 1000) + 1000;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

//    data = getIntent().getStringExtra("data");
//
//    try {
//        JSONObject jsObj = new JSONObject(data);
//        JSONObject campaignList = jsObj.getJSONObject("comapaignList").getJSONObject("1");
//
//        campaignId = campaignList.getString("cmp_id");
//        campaignName = campaignList.getString("compaign_name");
//        campaignSpotId = campaignList.getString("CampaignSpotId");

    @Override
    public void onReceive(Context context, Intent intent)
    {
//        campaignList = intent.getExtras().getString("campaignList");

        data = intent.getExtras().getString("data");

        try
        {
            jsObj = new JSONObject(data);
            JSONObject campaignList = jsObj.getJSONObject("comapaignList").getJSONObject("1");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> : " +campaignList);
            campaignName = campaignList.getString("compaign_name");

            JSONObject contentList = jsObj.getJSONObject("contentList").getJSONObject("1");
            contentPushText = contentList.getString("ContentPushText");

            System.out.println(">> NotificationReceiver.java >>>>> NotificationReceiver >>>>> onReceive >>>>> "+contentPushText);
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }


//        try {
//            jsObj = new JSONObject(campaignList);
//            campaignName = jsObj.getString("compaign_name");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intnt = null;

        Resources res = context.getResources();

        intnt = new Intent(context, ShowOfferScreen.class);
        System.out.println(">> NotificationReceiver.java >>>>> NotificationReceiver >>>>> onReceive >>>>> "+data);
        intnt.putExtra("data", data);
//        intnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, notificationId, intnt, notificationId);
        Notification.Builder builder1 = new Notification.Builder(context)
                .setSmallIcon(R.drawable.omnifyicon)
                .setContentTitle(res.getString(R.string.app_name))
                .setAutoCancel(true)
                .setContentText(contentPushText)
                .setContentIntent(pendingIntent);
        Notification notification = builder1.build();
        System.out.println("New Notification >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        manager.notify(notificationId, notification);

//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> : onReceiver111111111111111111111");
//            intent = new Intent(context, ShowOfferScreen.class);
//            intent.putExtra("msg", context.getResources().getString(R.string.daily_noti));
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//            String title = context.getResources().getString(R.string.app_name);
//            builder.setSmallIcon(R.drawable.omnifyicon)
//                    .setContentTitle(title)
//                    .setContentText("okokokok")
//                    .setContentIntent(pendingIntent)
//                    .setAutoCancel(false)
//                    .setTicker("this is ticker text")
//                    .setContentTitle("WhatsApp Notification")
//                    .setContentText("You have a new message")
//                    .setContentIntent(pendingIntent)
//                    .setOngoing(true)
//                    .setNumber(100);
//            builder.build();
//
//            notification = builder.getNotification();
//            manager.notify(0, notification);
//
//
////            notification.defaults |= Notification.DEFAULT_SOUND;
////            notification.defaults |= Notification.DEFAULT_VIBRATE;
////
////            manager.notify(0, notification);
//        else
//        {
//            intnt = new Intent(context, ShowOfferScreen.class);
//            intnt.putExtra("campaign",campaign);
////            startActivity(intnt);
//        }
    }
}
