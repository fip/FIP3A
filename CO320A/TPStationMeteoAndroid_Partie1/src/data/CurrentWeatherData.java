package data;

import android.graphics.Bitmap;

/**
 * Class containing the weather data for the current day
 */
public class CurrentWeatherData {
  private String mObservationTime;   // Observation time (UTC)
  private String mCloudCover;        // Cloud cover (%)
  private String mHumidity;          // Humidity (%)
  private String mPrecipitationMm;   // Precipitation amount in millimetre
  private String mPressure;          // Atmospheric pressure in milibars
  private String mTempCelcius;
  private String mTempFarenheit;
  private String mVisibility;        // Visibility (km)
  private String mWeatherDescription;
  private String mIconUrl;           // URL icon to be displayed in GUI
  private Bitmap mIconBitmap;        // weather icon image to display
  private String mWindSpeedKmph;     // Wind Speed in kmph (Kilometer per hour)
  private String mWindDirection;     // Wind direction in 16-point compass(N, NNE, NE, ENE..)

  
  // ****************************************
  // Auto-generated setter/getters methods:
  // ****************************************  
  public String getCloudCover(){
    return mCloudCover;
  }

  public Bitmap getIconBitmap(){
    return mIconBitmap;
  }

  public void setIconBitmap(Bitmap mIconBitmap){
    this.mIconBitmap = mIconBitmap;
  }

  public void setCloudCover(String mCloudCover){
    this.mCloudCover = mCloudCover;
  }

  public String getHumidity(){
    return mHumidity;
  }

  public void setHumidity(String mHumidity){
    this.mHumidity = mHumidity;
  }

  public String getObservationTime(){
    return mObservationTime;
  }

  public void setObservationTime(String mObservationTime){
    this.mObservationTime = mObservationTime;
  }

  public String getPrecipitationMm(){
    return mPrecipitationMm;
  }

  public void setPrecipitationMm(String mPrecipitationMm){
    this.mPrecipitationMm = mPrecipitationMm;
  }

  public String getPressure(){
    return mPressure;
  }

  public void setPressure(String mPressure){
    this.mPressure = mPressure;
  }

  public String getTempCelsius(){
    return mTempCelcius;
  }

  public void setTempCelsius(String mTempCelcius){
    this.mTempCelcius = mTempCelcius;
  }

  public String getTempFarenheit(){
    return mTempFarenheit;
  }

  public void setTempFarenheit(String mTempFarenheit){
    this.mTempFarenheit = mTempFarenheit;
  }

  public String getVisibility(){
    return mVisibility;
  }

  public void setVisibility(String mVisibility){
    this.mVisibility = mVisibility;
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
