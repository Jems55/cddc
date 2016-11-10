package com.example.mrj.omnify.ui;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrj.omnify.App;
import com.example.mrj.omnify.R;
import com.example.mrj.omnify.util.Constant;
import com.example.mrj.omnify.service.ServiceManager;
import com.example.mrj.omnify.util.Utils;
import com.kontakt.sdk.android.ble.configuration.ForceScanConfiguration;
import com.kontakt.sdk.android.ble.configuration.ScanPeriod;
import com.kontakt.sdk.android.ble.configuration.scan.ScanMode;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.exception.ScanError;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerContract;
import com.kontakt.sdk.android.ble.manager.listeners.EddystoneListener;
import com.kontakt.sdk.android.ble.manager.listeners.ScanStatusListener;
import com.kontakt.sdk.android.ble.rssi.RssiCalculators;
import com.kontakt.sdk.android.common.profile.IEddystoneDevice;
import com.kontakt.sdk.android.common.profile.IEddystoneNamespace;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements View.OnClickListener{

    Timer timer;
    int dWidth,dHeight;
    ImageView _btnMenu,_failedOfferImg, _progressBar;
//    TextView _option1, _option2, _option3, _option4, _option5;
    FrameLayout _fmLayOut;
    LinearLayout _linearlay, _linearlay2;
    String email;
    boolean isInFront = true;

    NotificationManager manager;
    Notification myNotication;

    int isLogin = 0;
    int defaultValue = 0;

    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerList;
    private TextView _tSearchMsg;
    TextView[] textViewArray = new TextView[6];
    static Handler handler;

    private static final int PERMISSION_REQUEST_CODE_LOCATION = 1;
    private final RxProximityListener rxProximityListener = new RxProximityListener();
    private ProximityManagerContract proximityManager;


    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        getDeviceDimen();

        if (!Utils.getBluetoothState()) {
            Utils.setBluetooth(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (FrameLayout) findViewById(R.id.drawer);

        _btnMenu = (ImageView) findViewById(R.id.btnMenu);
        _btnMenu.setOnClickListener(this);

        _tSearchMsg = (TextView) findViewById(R.id.searchMsg);

        textViewArray[0] = (TextView) findViewById(R.id.textOption1);
        textViewArray[0].setOnClickListener(this);

        textViewArray[1] = (TextView) findViewById(R.id.textOption2);
        textViewArray[1].setOnClickListener(this);

        textViewArray[2] = (TextView) findViewById(R.id.textOption3);
        textViewArray[2].setOnClickListener(this);

        textViewArray[3] = (TextView) findViewById(R.id.textOption4);
        textViewArray[3].setOnClickListener(this);

        textViewArray[4] = (TextView) findViewById(R.id.textOption5);
        textViewArray[4].setOnClickListener(this);

        textViewArray[5] = (TextView) findViewById(R.id.textOption6);
        textViewArray[5].setOnClickListener(this);

        _failedOfferImg = (ImageView) findViewById(R.id.failedOfferImg);
        _failedOfferImg.setVisibility(View.INVISIBLE);
        _progressBar = (ImageView) findViewById(R.id.progressBar);

        // Design Settings
        _fmLayOut = (FrameLayout) findViewById(R.id.contectFrame);
        _linearlay = (LinearLayout) findViewById(R.id.linearlay);
        _linearlay2 = (LinearLayout) findViewById(R.id.linearlay2);


        setHightWidthForComponant();
        startImageRotation();
        readPreferences();

        initHandler();

        if (email.equals("NoSignUp") ) {
            loginFB();
        } else {
            System.out.println("ON Scan");
            scanEddyStone();
        }

    }

    private void showMaps()
    {
        Intent in = new Intent(getApplicationContext(), Maps.class);
        startActivity(in);
    }

    protected void setHightWidthForComponant()
    {
        // logo size
        int wD = ((dWidth * 655) / 1080);
        int hD = wD;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wD,hD);
        _fmLayOut.setLayoutParams(lp);

        // logo padding
        int tP = ((dHeight * 400) / 1920);
        _linearlay.setPadding(0,tP,0,0);

        // set drawer options padding
        tP = ((dHeight * 160) / (1920/2));
        _linearlay2.setPadding(0,tP,0,0);

        //set Text size
        int tS = ((dWidth * 55) / (1080));
        int tB = ((dHeight * 35)/1920);
        tP = ((dHeight * 35)/1920);
        int tl = ((dWidth * 55) / (1080/2));

        for(int i = 0; i < 6; i++) {
            textViewArray[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
            textViewArray[i].setPadding(tl,tP,0,tB);
        }

        tS = ((dWidth * 50) / (1080));
        _tSearchMsg.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        _tSearchMsg.setPadding(0,tP,0,0);

        //Menu icon
        wD = ((dWidth * 150) / 1080);
        hD = wD;
        FrameLayout.LayoutParams dp = new FrameLayout.LayoutParams(wD,hD);
        _btnMenu.setLayoutParams(dp);

    }

    protected void getDeviceDimen()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
    }

    protected void clearSelection()
    {
        for(int i = 0; i < 6; i++) {
            textViewArray[i].setBackgroundColor(0);
        }
    }

    @Override
    public void onClick(View view)
    {
        mDrawerLayout.closeDrawer(mDrawerList);
        switch (view.getId())
        {
            case R.id.btnMenu:
                mDrawerLayout.openDrawer(mDrawerList);
                break;
            case R.id.textOption1:
                scanEddyStone();
                clearSelection();
                textViewArray[0].setBackgroundColor(Color.parseColor("#CC0041"));
                break;
            case R.id.textOption2:
                clearSelection();
                textViewArray[1].setBackgroundColor(Color.parseColor("#CC0041"));
                break;
            case R.id.textOption3:
                clearSelection();
                textViewArray[2].setBackgroundColor(Color.parseColor("#CC0041"));
                break;
            case R.id.textOption4:
                clearSelection();
                textViewArray[3].setBackgroundColor(Color.parseColor("#CC0041"));
                break;
            case R.id.textOption5:
                clearSelection();
                showMaps();
                textViewArray[4].setBackgroundColor(Color.parseColor("#CC0041"));
                break;
            case R.id.textOption6:
                clearSelection();
                openSettings();
                textViewArray[5].setBackgroundColor(Color.parseColor("#CC0041"));
                break;
        }
    }

    protected void openSettings()
    {
        Intent in = new Intent(getApplicationContext(), Settings.class);
        startActivity(in);
    }

    protected void startImageRotation()
    {
//        RotateAnimation rotate = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotate.setDuration(3000);
//        rotate.setRepeatMode(Animation.RESTART);
//        rotate.setRepeatCount(Animation.INFINITE);
//        rotate.setInterpolator(new LinearInterpolator());
//        _progressBar.startAnimation(rotate);

        if(_progressBar.getVisibility() == View.INVISIBLE)
            _progressBar.setVisibility(View.VISIBLE);

        _tSearchMsg.setText("Searching for offer...");
        _failedOfferImg.setVisibility(View.INVISIBLE);

        _progressBar.startAnimation(
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_indefinitely));

    }

    protected void stopImageRotation()
    {
        _tSearchMsg.setText("No Offers found nearby \n Don't worry, Omnify will notify you \n Once you are close to an interesting offer.");
        _failedOfferImg.setVisibility(View.INVISIBLE);
        _progressBar.setVisibility(View.INVISIBLE);
        _progressBar.clearAnimation();
    }

    protected void loginFB()
    {
        Intent in = new Intent(getApplicationContext(), FBlogin.class);
        startActivity(in);
        finish();
    }

    protected void readPreferences()
    {
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        email = sharedPref.getString("Email", "");

        email = App.getEmail();
        System.out.println("isLoginvalue1 : " + email);
    }

    protected void savePreferences()
    {
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(getString(R.string.Is_login), isLogin);
//        editor.commit();
//
//        System.out.println("isLoginvalue:" + isLogin);
    }

    protected void takePermission()
    {
        int checkSelfPermissionResult = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (PackageManager.PERMISSION_GRANTED == checkSelfPermissionResult)
        {
            //already granted
            System.out.println(">> MainActivity.java >>>>> MainActivity >>>>> takePermission >>>>> ");
            eddyStoneStartScane();
        }
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                //we should show some explanation for user here
                Toast.makeText(getApplicationContext(), "Location permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            }else {
                //request permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            }
        }

//        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION,getApplicationContext(),MainActivity.this)) {
//            eddyStoneStartScane();
//        }
//        else
//        {
//            requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,PERMISSION_REQUEST_CODE_LOCATION,getApplicationContext(),MainActivity.this);
//        }
    }

    public void requestPermission(String strPermission, int perCode, Context _c, Activity _a){

        if (ActivityCompat.shouldShowRequestPermissionRationale(_a,strPermission)){
            Toast.makeText(getApplicationContext(),"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.",Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(_a,new String[]{strPermission},perCode);
        }
    }

    public static boolean checkPermission(String strPermission,Context _c,Activity _a)
    {
        int result = ContextCompat.checkSelfPermission(_c, strPermission);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }


    protected void scanEddyStone()
    {
        takePermission();
        setTimerForScan();
        startImageRotation();
    }

    protected void setTimerForScan()
    {
        System.out.println("Start Timer");
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timer.cancel();
                        stopImageRotation();
                    }
                });

            }
        }, 50000, 30000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        isInFront = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;
    }

    protected void OnStartBackGroundScan()
    {

    }

    private void initHandler()
    {
        System.out.println(">> MainActivity.java >>>>> MainActivity >>>>> initHandler >>>>> ");

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message)
            {
                if (message.what == 002) {
                    System.out.println("Start Scan");
                    scanEddyStone();
                }

                else if(message.what == Constant.SERVICE_COMPAINGNS_RESPONSE)
                {
                    System.out.println(">> MainActivity.java >>>>> MainActivity >>>>> initHandler >>>>> 1");
                    String data = message.obj.toString();
                    try {
                        JSONObject jsObj = new JSONObject(data);

                        Integer success = jsObj.getInt("success");
                        String msg = jsObj.getString("message");

                        if(success == 1)
                        {
                            JSONObject campaignList = jsObj.getJSONObject("comapaignList").getJSONObject("1");

                            String campaignId = campaignList.getString("cmp_id");
                            String campaignName = campaignList.getString("compaign_name");
                            String campaignSpotId = campaignList.getString("CampaignSpotId");
                            String campaignActionType = campaignList.getString("CampaignActionType");
                            String loyaltyId = campaignList.getString("LoyaltyId");
                            String campaignStartDate = campaignList.getString("CampaignStartDate");
                            String campaignEndDate = campaignList.getString("CampaignEndDate");
                            String activates = campaignList.getString("activates");

                            JSONObject content = jsObj.getJSONObject("contentList").getJSONObject("1");

                            if(jsObj.has("comapaignList") && jsObj.has("contentList") && content.has("content_id"))
                            {
                                if (isAppIsInBackground(getApplicationContext())) {
                                    setRepeatingAlarm(campaignList, data);
                                } else {
                                    if (isInFront) {
                                        Intent intnt = new Intent(getApplicationContext(), ShowOfferScreen.class);
                                        intnt.putExtra("data", data);
                                        startActivity(intnt);
                                    }
                                }
                            }
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                return false;
            }
        });
    }

    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }


    protected void eddyStoneStartScane()
    {
//        System.out.println("set Proximity");
        proximityManager = new ProximityManager(getApplicationContext());
        proximityManager.configuration()
                .scanMode(ScanMode.BALANCED)
                .scanPeriod(ScanPeriod.create(TimeUnit.SECONDS.toMillis(15), TimeUnit.SECONDS.toMillis(5)))
                .forceScanConfiguration(ForceScanConfiguration.MINIMAL)
                .rssiCalculator(RssiCalculators.newLimitedMeanRssiCalculator(5));

        proximityManager.setEddystoneListener(rxProximityListener);
        proximityManager.connect(new OnServiceReadyListener()
        {
            @Override
            public void onServiceReady()
            {
//                System.out.println("Start Scan111");
                proximityManager.startScanning();
            }
        });
    }

//    private EddystoneListener createEddystoneListener() {
//        return new SimpleEddystoneListener() {
//            @Override
//            public void onEddystoneDiscovered(IEddystoneDevice eddystone, IEddystoneNamespace namespace) {
//                Log.i("Sample11111112222", "Eddystone discovered: " + eddystone.getUniqueId());
//            }
//        };}



    private void showToast(final String message)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onScan()
    {
//        System.out.println("Start Scan222222222");
        rxProximityListener.onScanStart();
    }

    AlarmManager am;

    public void setRepeatingAlarm(JSONObject value,String data)
    {
        NotificationReceiver.notificationId++;
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);
        intent.putExtra("data", data);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, NotificationReceiver.notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.set(AlarmManager.RTC, Calendar.getInstance().getTimeInMillis() + 100, pendingIntent);
    }

    private class RxProximityListener implements ScanStatusListener, EddystoneListener
    {

        @Override
        public void onScanStart()
        {
            showToast("Scanning started");
//            System.out.println("Start Scan333333333333");
            proximityManager.startScanning();
        }

        @Override
        public void onScanStop()
        {
            showToast("Scanning stopped");
            proximityManager.stopScanning();
        }

        @Override
        public void onScanError(ScanError scanError)
        {
            proximityManager.disconnect();
        }

        @Override
        public void onMonitoringCycleStart() {

        }

        @Override
        public void onMonitoringCycleStop() {

        }

        @Override
        public void onEddystoneDiscovered(IEddystoneDevice eddystone, IEddystoneNamespace namespace)
        {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> : " + eddystone.getUniqueId() + " : " + eddystone.getInstanceId() + " : "+ eddystone.getAddress());

            ServiceManager.getCampaign(handler,eddystone.getUniqueId());




//            EddayStoneList.FindOffer(eddystone.getUniqueId());
//            if(!EddayStoneList.active)
//            {
//                IEddystoneDevice stone = eddystone;
//                Bundle eddyStone = new Bundle();
//                eddyStone.putParcelable("key", stone);
//
//                Intent in = new Intent(getApplicationContext(), EddayStoneList.class);
//                in.putExtras(eddyStone);
//                startActivity(in);
//            }
//            else
//            {
//                Message msg = new Message();
//                msg.what = 001;
//                msg.obj = eddystone;
//
//                if (EddayStoneList.handler != null) {
//                    EddayStoneList.handler.sendMessage(msg);
//                }
//            }
        }

        @Override
        public void onEddystonesUpdated(List<IEddystoneDevice> eddystones, IEddystoneNamespace namespace) {

        }

        @Override
        public void onEddystoneLost(IEddystoneDevice eddystone, IEddystoneNamespace namespace)
        {

        }
    }
}


