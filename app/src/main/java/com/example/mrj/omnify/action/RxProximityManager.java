package com.example.mrj.omnify.action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mrj.omnify.ui.EddayStoneList;
import com.kontakt.sdk.android.ble.configuration.ForceScanConfiguration;
import com.kontakt.sdk.android.ble.configuration.ScanPeriod;
import com.kontakt.sdk.android.ble.configuration.scan.ScanMode;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.exception.ScanError;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerContract;
import com.kontakt.sdk.android.ble.manager.listeners.EddystoneListener;
import com.kontakt.sdk.android.ble.manager.listeners.ScanStatusListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleEddystoneListener;
import com.kontakt.sdk.android.ble.rssi.RssiCalculators;
import com.kontakt.sdk.android.common.profile.IEddystoneDevice;
import com.kontakt.sdk.android.common.profile.IEddystoneNamespace;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by MR.J on 8/27/2016.
 */

public class RxProximityManager extends Activity
{
    Context inCon;

    private final RxProximityListener rxProximityListener = new RxProximityListener();
    private ProximityManagerContract proximityManager;

    public RxProximityManager(Context context)
    {
        inCon = context;

        System.out.println("set Proximity");
        proximityManager = new ProximityManager(context);
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
                System.out.println("Start Scan111");
                proximityManager.startScanning();
            }
        });
    }

    private EddystoneListener createEddystoneListener() {
        return new SimpleEddystoneListener()
        {
            @Override
            public void onEddystoneDiscovered(IEddystoneDevice eddystone, IEddystoneNamespace namespace) {
                Log.i("Sample", "Eddystone discovered: " + eddystone.getUniqueId());
            }
        };
    }

    private void showToast(final String message)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RxProximityManager.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onScan()
    {
        System.out.println("Start Scan222222222");
        rxProximityListener.onScanStart();
    }

    private class RxProximityListener implements ScanStatusListener, EddystoneListener {

        @Override
        public void onScanStart()
        {
            showToast("Scanning started");
            System.out.println("Start Scan333333333333");
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
//            System.out.println("EddayStone Discoverd:111111111 " +eddystone.getNamespaceId() +" : "+eddystone.getUniqueId());
//            System.out.println("EddayStone Discoverd:222222222 " +namespace);

            String stone = eddystone.toString();
            Bundle eddyStone = new Bundle();
            eddyStone.putString("key", stone);

            Intent in = new Intent(inCon, EddayStoneList.class);
            in.putExtras(eddyStone);
            startActivity(in);
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
