package fr.enstb.tp.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;
import data.CurrentWeatherData;
import data.ForecastWeathertData;

public class JsonParser {
	// **************************************************************************
	// Ecran principal: �tape5 � parsing JSON & affichage de la m�t�o courante
	// Code:1
	// **************************************************************************
	/**
	 * Performs the JSON parsing to retrieve the current weather values - values
	 * to be parsed are located in "current_condition" part
	 * 
	 * @param aJsonString
	 *            JSON string response server
	 * @return CurrentWeatherData object if parsing succeed, null otherwise
	 */
	public static CurrentWeatherData parseCurrentWeather(String aJsonString) {
		CurrentWeatherData weatherData = null;

		try {
			// instantiate the weather data return value
			weatherData = new CurrentWeatherData();

			JSONObject jsonRootObj = new JSONObject(aJsonString);
			JSONObject dataObj = jsonRootObj.getJSONObject("data");
			JSONArray jsonArray = dataObj.getJSONArray("current_condition");

			// retrieve the current weather description
			JSONObject currWeatherObj = jsonArray.getJSONObject(0);
			weatherData.setCloudCover(currWeatherObj.getString("cloudcover"));
			weatherData.setHumidity(currWeatherObj.getString("humidity"));
			weatherData.setObservationTime(currWeatherObj
					.getString("observation_time"));
			weatherData
					.setPrecipitationMm(currWeatherObj.getString("precipMM"));
			weatherData.setPressure(currWeatherObj.getString("pressure"));
			weatherData.setTempCelsius(currWeatherObj.getString("temp_C"));
			weatherData.setTempFarenheit(currWeatherObj.getString("temp_F"));
			weatherData.setWindDirection(currWeatherObj
					.getString("winddir16Point"));
			weatherData.setWindSpeedKmph(currWeatherObj
					.getString("windspeedKmph"));

			// retrieve the current weather description
			JSONArray descriptArray = currWeatherObj
					.getJSONArray("weatherDesc");
			weatherData.setWeatherDescription(descriptArray.getJSONObject(0)
					.getString("value"));

			// retrieve the current weather description icon URL
			JSONArray iconArray = currWeatherObj.getJSONArray("weatherIconUrl");
			weatherData.setIconUrl(iconArray.getJSONObject(0)
					.getString("value"));
		} catch (JSONException e) {
			// Auto-generated catch block
			Log.e("EXCEPT",
					"** ParseCurrentWeather() JSONException: " + e.getMessage());
			weatherData = null;
		}

		return weatherData;
	}

	/**
	 * Performs the JSON parsing to retrieve the forecast weather values
	 * 
	 * @param aJsonString
	 *            JSON string response server
	 * @return ForecastWeathertData an array filled in with forecast data, null
	 *         otherwise
	 */
	public static ForecastWeathertData[] parseForecastWeather(String aJsonString) {
		ForecastWeathertData weatherDataTab[] = new ForecastWeathertData[3];

		try {
			JSONObject jsonRootObj = new JSONObject(aJsonString);
			// **************************************************************************
			// Ecran principal: �tape7 � parsing JSON & affichage de la m�t�o �
			// J+1 Code:4-5
			// **************************************************************************
			JSONObject dataObj = jsonRootObj.getJSONObject("data");
			JSONArray weatherArray = dataObj.getJSONArray("weather");

			for (int i = 0; i < weatherDataTab.length; i++) {
				ForecastWeathertData weatherData = new ForecastWeathertData();
				
				JSONObject currentObj = weatherArray.getJSONObject(i);
				weatherData.setForecastDate(currentObj.getString("date"));
				JSONArray weatherIconArray = currentObj
						.getJSONArray("weatherIconUrl");
				weatherData.setIconUrl(weatherIconArray.getJSONObject(0)
						.getString("value"));
				weatherData.setPrecipitationMm(currentObj.getString("precipMM"));
				weatherData.setTempMaxCelsius(currentObj.getString("tempMaxC"));
				weatherData.setTempMaxFarenheit(currentObj.getString("tempMaxF"));
				weatherData.setTempMinCelsius(currentObj.getString("tempMinC"));
				weatherData.setTempMinFarenheit(currentObj.getString("tempMinF"));
				JSONArray weatherDescArray = currentObj.getJSONArray("weatherDesc");
				weatherData.setWeatherDescription(weatherDescArray.getJSONObject(0).getString("value"));
				weatherData.setWindDirection(currentObj.getString("winddir16Point"));
				weatherData.setWindSpeedKmph(currentObj.getString("windspeedKmph"));
				
				weatherDataTab[i] = weatherData;
			}
		} catch (JSONException e) {
			// Auto-generated catch block
			Log.e("EXCEPT",
					"** parseForecastWeather() JSONException: "
							+ e.getMessage());
			weatherDataTab = null;
		}

		return weatherDataTab;
	}

}
