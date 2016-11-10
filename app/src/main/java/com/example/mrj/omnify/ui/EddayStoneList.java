package com.example.mrj.omnify.ui;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mrj.omnify.R;
import com.example.mrj.omnify.service.ServiceManager;
import com.kontakt.sdk.android.common.profile.IEddystoneDevice;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by MR.J on 8/30/2016.
 */

public class EddayStoneList extends Activity
{
    static boolean active = false;
    static Handler handler;

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        allEddyStone = new ArrayList<>();
        System.out.println("Finish");
        this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        active = false;
    }

    ListView lv;
    ArrayList<IEddystoneDevice> allEddyStone = new ArrayList<>();
    IEddystoneDevice getStone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_eddystone);

//        Bundle eddayStone = getIntent().getExtras();
//        getStone =  eddayStone.getParcelable("key");
//        allEddyStone.add(getStone);
//
//        lv=(ListView) findViewById(R.id.listView);
//        lv.setAdapter(new CustomAdapter(this, allEddyStone));

        initHandler();
    }



    public class CustomAdapter extends BaseAdapter
    {
        ArrayList<IEddystoneDevice> result;
        Context context;
        int [] imageId;
        public LayoutInflater inflater=null;

        public CustomAdapter(EddayStoneList eddayStoneList, ArrayList stoneObj)
        {
            result = stoneObj;
            context = eddayStoneList;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return allEddyStone.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder=new Holder();
            View rowView;

            rowView = inflater.inflate(R.layout.eddaystone_rend, null);

            holder.tv=(TextView) rowView.findViewById(R.id.txtName);
            holder.tv.setText("Name:- " + result.get(position).getName());

            holder.tv=(TextView) rowView.findViewById(R.id.txtUniqId);
            holder.tv.setText("UniqId:- " + result.get(position).getUniqueId());

            holder.tv=(TextView) rowView.findViewById(R.id.txtNamespace);
            holder.tv.setText("Namespace:- " + result.get(position).getNamespaceId());

            holder.tv=(TextView) rowView.findViewById(R.id.txtInstance);
            holder.tv.setText("Instance:- " + result.get(position).getInstanceId());

            holder.tv=(TextView) rowView.findViewById(R.id.txtUrl);
            holder.tv.setText("Url:- " + result.get(position).getUrl());

            holder.tv=(TextView) rowView.findViewById(R.id.txtPower);
            holder.tv.setText("Power:- " + result.get(position).getBatteryPower());

            holder.tv=(TextView) rowView.findViewById(R.id.txtTemprature);
            holder.tv.setText("Temprature:- " + result.get(position).getTemperature());

            holder.tv=(TextView) rowView.findViewById(R.id.txtBattery);
            holder.tv.setText("Battery voltage:- " + result.get(position).getBatteryVoltage());

            holder.tv=(TextView) rowView.findViewById(R.id.txtCount);
            holder.tv.setText("PDU Count:- " + result.get(position).getPduCount());

            holder.tv=(TextView) rowView.findViewById(R.id.txtTimePowerUp);
            holder.tv.setText("Time since power up:- " + result.get(position).getTimeSincePowerUp());

            holder.tv=(TextView) rowView.findViewById(R.id.txtVersion);
            holder.tv.setText("Telemetry version:- " + result.get(position).getTelemetryVersion());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
//                    Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
                }
            });
            return rowView;
        }
    }

    public class Holder
    {
        TextView tv;
    }

    static Boolean _isAlreadyExist = false;

    private void initHandler()
    {
        handler = new Handler(new Handler.Callback()
        {
            @Override
            public boolean handleMessage(Message message)
            {
                if(message.what == 001)
                {
                    IEddystoneDevice newStone = (IEddystoneDevice) message.obj;

                    for (int i = 0; i < allEddyStone.size(); i++)
                    {
                        IEddystoneDevice stone = allEddyStone.get(i);
                       if(stone.getUniqueId().equals(newStone.getUniqueId()))
                       {
                           System.out.println("already Exist");
                           System.out.println("ID: "+newStone.getUniqueId() + " : " + stone.getUniqueId());
                           _isAlreadyExist = true;
                           break;
                       }
                        else
                       {
                           System.out.println("Not Exist");
                           System.out.println("ID: "+newStone.getUniqueId() + " : " + stone.getUniqueId());
                           _isAlreadyExist = false;
                       }
                    }

                    if(!_isAlreadyExist)
                    {
//                        allEddyStone.add((IEddystoneDevice) message.obj);
//                        lv.setAdapter(new CustomAdapter(EddayStoneList.this, allEddyStone));
                        FindOffer(newStone.getUniqueId());
                    }
                }

//                if(message.what == Constant.SERVICE_COMPAINGNS_RESPONSE)
//                {
//                    System.out.println("-------------------------------------------------------------------");
//                    String data = message.obj.toString();
//                    try {
//                        JSONObject jsObj = new JSONObject(data);
//
//                        Integer success = jsObj.getInt("success");
//                        String msg = jsObj.getString("message");
//
//                        if(success == 1)
//                        {
//                            JSONObject campaignList = jsObj.getJSONObject("comapaignList").getJSONObject("1");
//
//                            String campaignId = campaignList.getString("cmp_id");
//                            String campaignName = campaignList.getString("compaign_name");
//                            String campaignSpotId = campaignList.getString("CampaignSpotId");
//                            String campaignActionType = campaignList.getString("CampaignActionType");
//                            String loyaltyId = campaignList.getString("LoyaltyId");
//                            String campaignStartDate = campaignList.getString("CampaignStartDate");
//                            String campaignEndDate = campaignList.getString("CampaignEndDate");
//                            String activates = campaignList.getString("activates");
//                        }
//                    }
//                    catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
                return false;
            }
        });
    }

    AlarmManager am;

    public void setRepeatingAlarm() {

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 0);

        Intent intent = new Intent(this, NotificationReceiver.class);

        boolean alarmUp = (PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_NO_CREATE) != null);

        if (!alarmUp) {

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        }
    }

   public static void FindOffer(String uniqueId)
    {
        ServiceManager.getCampaign(handler,uniqueId);
    }
}
