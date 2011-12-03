//-------------------------------------------------------------------------------------------------
//
//  Module Name: ForecastWeatherData.java
//
//  General Description: current weather data format 
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
package data;

import android.graphics.Bitmap;

/**
 * Class containing the forecast weather data
 */
public class ForecastWeathertData {
  private String mForecastDate;      // Forecast date weather
  private String mPrecipitationMm;   // Precipitation amount in millimetre
  private String mWeatherDescription;
  private String mIconUrl;
  private Bitmap mIconBitmap;        // weather icon image to display
  private String mTempMaxCelsius;
  private String mTempMinCelsius;
  private String mTempMaxFarenheit;
  private String mTempMinFarenheit;
  private String mWindSpeedKmph;     // Wind Speed in kmph (Kilometer per hour)
  private String mWindDirection;     // Wind direction in 16-point compass(N, NNE, NE, ENE..)
  
  // ****************************************
  // Auto-generated setter/getters methods:
  // ****************************************  
  public String getForecastDate(){
    return mForecastDate;
  }

  public void setForecastDate(String mForecastDate){
    this.mForecastDate = mForecastDate;
  }

  public String getPrecipitationMm(){
    return mPrecipitationMm;
  }

  public void setPrecipitationMm(String mPrecipitationMm){
    this.mPrecipitationMm = mPrecipitationMm;
  }

  public String getWeatherDescription(){
    return mWeatherDescription;
  }

  public void setWeatherDescription(String mWeatherDescription){
    this.mWeatherDescription = mWeatherDescription;
  }

  public String getIconUrl(){
    return mIconUrl;
  }

  public void setIconUrl(String mWeatherIconUrl){
    this.mIconUrl = mWeatherIconUrl;
  }

  public Bitmap getIconBitmap(){
    return mIconBitmap;
  }

  public void setIconBitmap(Bitmap mIconBitmap){
    this.mIconBitmap = mIconBitmap;
  }

  public String getTempMaxCelsius(){
    return mTempMaxCelsius;
  }

  public void setTempMaxCelsius(String mTempMaxCelsius){
    this.mTempMaxCelsius = mTempMaxCelsius;
  }

  public String getTempMinCelsius(){
    return mTempMinCelsius;
  }

  public void setTempMinCelsius(String mTempMinCelsius){
    this.mTempMinCelsius = mTempMinCelsius;
  }

  public String getTempMaxFarenheit(){
    return mTempMaxFarenheit;
  }

  public void setTempMaxFarenheit(String mTempMaxFarenheit){
    this.mTempMaxFarenheit = mTempMaxFarenheit;
  }

  public String getTempMinFarenheit(){
    return mTempMinFarenheit;
  }

  public void setTempMinFarenheit(String mTempMinFarenheit){
    this.mTempMinFarenheit = mTempMinFarenheit;
  }

  public String getWindSpeedKmph(){
    return mWindSpeedKmph;
  }

  public void setWindSpeedKmph(String mWindSpeedKmph){
    this.mWindSpeedKmph = mWindSpeedKmph;
  }

  public String getWindDirection(){
    return mWindDirection;
  }

  public void setWindDirection(String mWindDirection){
    this.mWindDirection = mWindDirection;
  }

}
