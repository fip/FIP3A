//-------------------------------------------------------------------------------------------------
//
//  Module Name: Tools.java
//
//  General Description: various helper tools
//
//
//  Project: Weather Forecast - TP Android Introduction
//
//
//-------------------------------------------------------------------------------------------------
//
//                        TELECOM BRETAGNE
//
//
// Revision History:
//                             Modification
// Author (core ID)                Date                 Description of Changes
// -------------------------    ------------    ---------------------------------------------------
// pcontrei                     02/10/2010     Creation
//  
//-------------------------------------------------------------------------------------------------
package fr.enstb.tp.android;

import java.io.InputStream;
import java.net.URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Tools {
  public static final int     PREF_TEMPERATURE_UNIT_ID = 0;
  public static final String  CELCIUS_UNIT_STR         = "celcius";
  public static final String  FAHRENHEIT_UNIT_STR      = "fahrenheit";
  private static final String WEATHER_REQUEST_URL      = "http://free.worldweatheronline.com/feed/weather.ashx";
  private static final String KEY                  	   = "ed9a0b27bf025314100910";

  
  /**
   * Builds the weather forecast URL request
   * 
   * @param aCity city name
   * @param aCountry country name
   * @param aNumOfDays forecast number of days
   * @return the weather forecast URL
   * @see #getWeatherDataRunProc()
   */
  public static String buildUrlRequest(String aCity, String aCountry, String aNumOfDays){
    String urlString = WEATHER_REQUEST_URL;
    urlString += "?q=" + aCity + "," + aCountry + "&format=json&num_of_days=" + aNumOfDays + "&key=" + KEY;
    return urlString;
  }

  
  /**
   * Retrieves an image from its internet location
   * 
   * @param aUrlImage URL image
   * @return the image in a bitmap format, null otherwise
   */
  public static Bitmap downloadImageFromUrl(String aUrlImage){
    InputStream inputStream;
    Bitmap bitmapImage = null;
    try {
      // download the image & decode the image
      inputStream = new URL(aUrlImage).openStream();
      bitmapImage = BitmapFactory.decodeStream(inputStream);
    }
    catch(Exception e) {
      Log.e("EXCEPT", "** downloadImageFromUrl(): " + e.getMessage());
    }
    
    return bitmapImage;
  }

  
  /**
   * Writes a string preference
   * 
   * @param aTheContext context from the calling activity
   * @param aPreferenceId preference ID
   * @param aStrValueToWrite string value to write 
   * @return true if operation succeed, false otherwise
   */
  public static boolean setPreferences(Context aTheContext, int aPreferenceId, String aStrValueToWrite){
    boolean status = true;
    SharedPreferences settings = aTheContext.getSharedPreferences("WeatherForecastPrefFile", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = settings.edit();

    switch(aPreferenceId) {
      case PREF_TEMPERATURE_UNIT_ID:
        Log.d("Tools:setPreferences()", "** PREF_TEMPERATURE_UNIT_ID: aStrValueToWrite=" + aStrValueToWrite);
        editor.putString("TemperatureUnit"/* preference name */, aStrValueToWrite);
        break;

      default:
        status = false;
        break;
    }

    // Commit the new values
    editor.commit();
    return status;
  }
    
  
  /**
   * Reads the preference referenced by aPreferenceId 
   * 
   * @param aTheContext context from the calling activity
   * @param aPreferenceId preference ID
   * @return a String value if operation succeed, null otherwise
   */
  public static String getPreferences(Context aTheContext, int aPreferenceId){
    String retValue = null;
    SharedPreferences settings = aTheContext.getSharedPreferences("WeatherForecastPrefFile", Context.MODE_PRIVATE);

    switch(aPreferenceId) {
      case PREF_TEMPERATURE_UNIT_ID:
        retValue = settings.getString("TemperatureUnit"/* preference name */, CELCIUS_UNIT_STR/*default value, if not exist*/);
        Log.d("Tools:getPreferences()", "** PREF_TEMPERATURE_UNIT_ID=" + retValue);
        break;

      default:
        // error, return a null Object
        break;
    }

    return retValue;
  }
} // end of class
