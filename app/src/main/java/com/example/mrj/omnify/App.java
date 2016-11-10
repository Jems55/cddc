package com.example.mrj.omnify;

import android.app.Application;
import android.content.SharedPreferences;

import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.log.LogLevel;
import com.kontakt.sdk.android.common.log.Logger;

import java.util.ArrayList;

public class App extends Application {

  static SharedPreferences preferences;
  static SharedPreferences.Editor prefEditor;

  @Override
  public void onCreate() {
    super.onCreate();

    initializeDependencies();

    preferences = getSharedPreferences("myGamePreferences", MODE_PRIVATE);
    prefEditor = preferences.edit();
  }

  private void initializeDependencies() {
    KontaktSDK.initialize(this);

// .setDebugLoggingEnabled(BuildConfig.DEBUG).setLogLevelEnabled(LogLevel.DEBUG, true).setCrashlyticsLoggingEnabled(true);
// Logger.setDebugLoggingEnabled(false);
  }

  public static void setEmail(String email) {
    prefEditor.putString("EMAIL", email).commit();
  }

  public static String getEmail()
  {
    return preferences.getString("EMAIL", "NoSignUp");
  }

  public static void setName(String name)
  {
    prefEditor.putString("NAME", name).commit();
  }

  public static String getName()
  {
    return preferences.getString("NAME", "NoName");
  }

  public static void setUserId(Integer userId)
  {
    prefEditor.putInt("USERID", userId).commit();
  }

  public static int getUserId()
  {
    return preferences.getInt("USERID", 0);
  }

  public static void setGender(String gender) {
    prefEditor.putString("GENDER", gender).commit();
  }

  public static String getGender()
  {
    return preferences.getString("GENDER", "F");
  }

  public static void setBirthYear(String birthyear)
  {
    prefEditor.putString("BIRTHYEAR", birthyear).commit();
  }

  public static String getBirthYear()
  {
    return preferences.getString("BIRTHYEAR", "1950");
  }

  public static void seCategory(String value)
  {
    prefEditor.putString("CATEGORY", value).commit();}

  public static String getCategory()
  {
    return preferences.getString("CATEGORY","No Category");
  }
}

