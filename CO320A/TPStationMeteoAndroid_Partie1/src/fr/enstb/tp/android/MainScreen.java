//-------------------------------------------------------------------------------------------------
//
//  Module Name: MainScreen.java
//
//  General Description: main screen displaying weather forecast information 
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

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import data.CurrentWeatherData;
import data.ForecastWeathertData;

public class MainScreen extends Activity {
	// constant values:
	static final String TEMP_DEGREE = "°C";
	static final String TEMP_FAHRENEIT = "F";
	static final int D_DAY_PLUS_ONE = 1;
	static final int D_DAY_PLUS_TWO = 2;
	// Menu parameters:
	static final int MENU_ID_SETTINGS = Menu.FIRST;
	static final int DIALOG_ID_SETTINGS_TEMP_UNIT = 0;
	static final String MENU_NAME_SETTINGS = "Parameters";
	static final CharSequence[] CHOIX_UNITE = { "Celsius (°C)",
			"Fahrenheit (F)" };
	// internal processing variables:
	private String mCountryName = "";
	private String mCityName = "";
	static final String FORECAST_NUM_OF_DAYS_STR = "3";
	private boolean mIsCelsiusUnit = false; // to choose between C� and F
	private ProgressDialog mUpdateForecastProgressDialog;
	// UI widgets for current forecast:
	private TextView mCurrentWeatherTitleTxt;
	private TextView mDescriptionTxt;
	private TextView mTemperatureTxt;
	private TextView mCloudCoverTxt;
	private ImageView mWeather0ImgView;
	// UI widgets for forecast Day+1:
	private TextView mWeather1TitleTxt;
	private TextView mDescriptionDayAfterTxt;
	private TextView mTempMaxTxt;
	private TextView mTempMinTxt;
	private TextView mRainPrecipitTxt;
	private TextView mWindDirectionTxt;
	private TextView mWindSpeedTxt;
	private ImageView mWeather1ImgView;
	// asynchronous return values:
	private CurrentWeatherData mCurrentWeather;
	private ForecastWeathertData[] mForecastWeatherTab;

	// multi-threading parameters:
	private Runnable mGetWeatherDataRunnable = new Runnable() {
		@Override
		public void run() {
			// Auto-generated method stub
			getWeatherDataRunProc();
			Message msg = mProgressHandler.obtainMessage();
			msg.arg1 = GET_WEATHER_DONE;
			mProgressHandler.sendMessage(msg);
		}
	};
	private static int GET_WEATHER_DONE = 1;
	private Runnable mUpdateUiRunnable = new Runnable() {
		@Override
		public void run() {
			// Auto-generated method stub
			updateUiRunProc();
		}
	};
	private Handler mProgressHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.arg1 == GET_WEATHER_DONE) {
				stopProgressDialog();
			}
		}
	};
	boolean mAsynchProcStatus = false;

	// =========================================================================================
	// Ecran principal: �tape4 � lancement du thread & requ�te serveur
	// ** Multi-threading **
	// - getWeatherDataRunProc()
	// - updateUiRunProc()
	// - startProgressDialog()
	// =========================================================================================
	/**
	 * Performs the HTTP request on the forecast weather server. - Launch the
	 * JSON parser on server response - Launch the UI update:
	 * runOnUiThread(mUpdateUiRunnable); This method is expected to be run on a
	 * separate thread
	 * 
	 * WARNING: - This is a blocking method - No UI access can be made from this
	 * method!!
	 * 
	 * @see #mGetWeatherDataRunnable
	 * @see #mUpdateUiRunnable
	 * @see #parseCurrentWeather
	 * @see #parseForecastWeather
	 */
	private void getWeatherDataRunProc() {
		try {
			mAsynchProcStatus = false;
			
			// building the URL
			String urlString = Tools.buildUrlRequest(mCityName, mCountryName,
					FORECAST_NUM_OF_DAYS_STR);
			Log.d("MainScreen:mGetWeatherDataRunnable", "** URL=" + urlString);
			// launch the server request
			HttpClient myHttpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(urlString);
			HttpResponse response = myHttpClient.execute(httpGet);

			// test the HTTP status response
			int httpStatus = response.getStatusLine().getStatusCode();
			if (httpStatus == HttpStatus.SC_OK) {
				String httpRespStr = EntityUtils.toString(response.getEntity());
				Log.d("MainScreen:getWeatherDataRunProc", "** Status="
						+ httpStatus + " JsonResp=" + httpRespStr);

				//
				// *******************************************************************
				// // Ecran principal: �tape5 � parsing JSON & affichage de la
				// m�t�o
				// courante Code:2
				// //
				// *******************************************************************
				// parse the current weather data
				if ((mCurrentWeather = JsonParser
						.parseCurrentWeather(httpRespStr)) != null) {
					// Download the weather icon from its URL
					String iconUrl = mCurrentWeather.getIconUrl();
					if (iconUrl != null) {
						Bitmap iconBitmap = Tools.downloadImageFromUrl(iconUrl);
						mCurrentWeather.setIconBitmap(iconBitmap);
					}
				}

				// parse the forecast weather data:
				if ((mForecastWeatherTab = JsonParser
						.parseForecastWeather(httpRespStr)) != null) {
					// Download the weather icon from its URL for each forecast
					// in mForecastWeatherTab
					for (int i = 0; i < mForecastWeatherTab.length; i++) {
						String iconUrl = mForecastWeatherTab[i].getIconUrl();
						if (iconUrl != null) {
							Bitmap iconBitmap = Tools
									.downloadImageFromUrl(iconUrl);
							mForecastWeatherTab[i].setIconBitmap(iconBitmap);
						}
					}
				}

				// set the final status: set to true only if parsing & HTTP are
				// both OK
				if ((mCurrentWeather != null) && (mForecastWeatherTab != null)) {
					mAsynchProcStatus = true; // what could it be used
														// for?..
				}

				runOnUiThread(mUpdateUiRunnable);
			} else {
				// bad response server
				Log.d("MainScreen:mGetWeatherDataRunnable",
						"** HTTP error - status=" + httpStatus);
			}
		} catch (ClientProtocolException e) {
			Log.e("EXCEPT",
					"** mGetWeatherDataRunnable() ClientProtocolException: "
							+ e.getMessage());
		} catch (IOException e) {
			Log.e("EXCEPT",
					"** mGetWeatherDataRunnable() IOException: "
							+ e.getMessage());
		} catch (Exception e) {
			Log.e("EXCEPT",
					"** mGetWeatherDataRunnable() Exception: " + e.getMessage());
		}
	}

	/**
	 * Display the current weather data on the GUI - test the asynch status
	 * before displaying weather information - stops the progress dialog
	 * 
	 * @see #getWeatherDataRunProc
	 */
	private void updateUiRunProc() {
		if (!mAsynchProcStatus){
			return;
		}
		
		// display observed weather for today (D day)
		mCurrentWeatherTitleTxt.setText("Observation " + mCityName + " - "
				+ mCurrentWeather.getObservationTime());
		mDescriptionTxt.setText(mCurrentWeather.getWeatherDescription());

		// display temperature C� vs F
		mTemperatureTxt.setText(mIsCelsiusUnit ? mCurrentWeather
				.getTempCelsius() + TEMP_DEGREE : mCurrentWeather
				.getTempFarenheit() + TEMP_FAHRENEIT);
		mCloudCoverTxt.setText(mCurrentWeather.getCloudCover());
		mWeather0ImgView.setImageBitmap(mCurrentWeather.getIconBitmap());

		// display weather forecast (D day + 1)
		displayForecastByDay(D_DAY_PLUS_ONE);
	}

	/**
	 * Display the forecast weather details according to the forecast day number
	 * given in aDayNumber
	 * 
	 * @param aDayNumber
	 *            the day number for the weather forecast: D_DAY_PLUS_ONE..
	 * @see #updateUiRunProc()
	 * @see #D_DAY_PLUS_ONE
	 * @see #D_DAY_PLUS_TWO
	 */
	private void displayForecastByDay(int aDayNumber) {
		// **************************************************************************
		// Ecran principal: �tape7 � parsing JSON & affichage de la m�t�o � J+1
		// Code:8
		// **************************************************************************
		ForecastWeathertData weatherDataDay = mForecastWeatherTab[aDayNumber];
		mWeather1TitleTxt.setText("Forecast " + mCityName + " - "
				+ weatherDataDay.getForecastDate());
		mDescriptionDayAfterTxt.setText(weatherDataDay.getWeatherDescription());
		// display temperature C� vs F
		if (mIsCelsiusUnit == true) {
			mTempMaxTxt.setText(weatherDataDay.getTempMaxCelsius()
					+ TEMP_DEGREE);
			mTempMinTxt.setText(weatherDataDay.getTempMinCelsius()
					+ TEMP_DEGREE);
		} else {
			mTempMaxTxt.setText(weatherDataDay.getTempMaxFarenheit()
					+ TEMP_FAHRENEIT);
			mTempMinTxt.setText(weatherDataDay.getTempMinFarenheit()
					+ TEMP_FAHRENEIT);
		}
		mRainPrecipitTxt.setText(weatherDataDay.getPrecipitationMm() + "mm");
		mWindDirectionTxt.setText(weatherDataDay.getWindDirection());
		mWindSpeedTxt.setText(weatherDataDay.getWindSpeedKmph() + "Km/h");
		mWeather1ImgView.setImageBitmap(weatherDataDay.getIconBitmap());
	}

	/**
	 * Create a simple progress dialog
	 */
	private void startProgressDialog() {
		// ***********************************************************
		// Ecran principal: �tape6 � insertion d'une progress dialog (optionnel)
		// Code:1-3
		// ***********************************************************
		mUpdateForecastProgressDialog = new ProgressDialog(this);
		mUpdateForecastProgressDialog.setTitle("Forecast Update");
		mUpdateForecastProgressDialog.setMessage("Loading - please wait...");
		mUpdateForecastProgressDialog.setIndeterminate(true);
		mUpdateForecastProgressDialog.setCancelable(false);
		mUpdateForecastProgressDialog.show();
	}

	private void stopProgressDialog() {
		mUpdateForecastProgressDialog.dismiss();
	}

	// =========================================================================================
	// Ecran principal: �tape3 � cr�ation du menu & boite de dialogue
	// ** MENU & DIALOGS **
	// - onCreateOptionsMenu()
	// - onMenuItemSelected()
	// - onCreateDialog()
	// =========================================================================================
	/**
	 * Creates the option menu for the activity - only one menu is created:
	 * MENU_NAME_SETTINGS
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Auto-generated method stub

		super.onCreateOptionsMenu(menu);
		// Toast.makeText(this, "onCreateOptionsMenu not yet emplemented!",
		// Toast.LENGTH_LONG).show();
		// ***********************************************************
		// Ecran principal: �tape3 � cr�ation du menu & boite de dialogue Code:5
		// ***********************************************************
		// "Parameters" menu creation:
		MenuItem theMenu = menu.add(Menu.NONE, MENU_ID_SETTINGS, Menu.NONE,
				MENU_NAME_SETTINGS);
		theMenu.setIcon(android.R.drawable.ic_menu_manage);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// Auto-generated method stub
		// ***********************************************************
		// Ecran principal: �tape3 � cr�ation du menu & boite de dialogue
		// Code:6-7
		// ***********************************************************
		switch (item.getItemId()) {
		case MENU_ID_SETTINGS:
			// display the dialog box to choose the temperature unit (C or F)
			showDialog(DIALOG_ID_SETTINGS_TEMP_UNIT);
			break;
		default:
			// error case
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * Display a single choice dialog box to choose the temperature unit (C� or
	 * F)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		// Auto-generated method stub
		// ***********************************************************
		// Ecran principal: �tape3 � cr�ation du menu & boite de dialogue
		// Code:1-4
		// ***********************************************************
		switch (id) {
		case DIALOG_ID_SETTINGS_TEMP_UNIT:
			return new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_info)
					// 0 for no icon
					.setTitle("Temperature Unit")
					.setSingleChoiceItems(CHOIX_UNITE, mIsCelsiusUnit ? 0 : 1,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int choiceItemId) {
									String valueToWrite;

									switch (choiceItemId) {
									case 0:
										mIsCelsiusUnit = true;
										valueToWrite = Tools.CELCIUS_UNIT_STR;
										break;
									case 1:
										mIsCelsiusUnit = false;
										valueToWrite = Tools.FAHRENHEIT_UNIT_STR;
										break;
									default:
										mIsCelsiusUnit = true;
										valueToWrite = Tools.CELCIUS_UNIT_STR;
									}

									Tools.setPreferences(MainScreen.this,
											Tools.PREF_TEMPERATURE_UNIT_ID,
											valueToWrite);
								}
							})
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// Update the display according to the unit
									// user choice
									runOnUiThread(mUpdateUiRunnable);
								}
							})
					.setNegativeButton("CANCEL",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// nothing to do... just leave
								}
							}).create();
		default:
			break;
		}
		return super.onCreateDialog(id);
	}

	// =========================================================================================
	// ** LIFE CYCLE **
	// =========================================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		Log.d("MainScreen:onCreate()", "** Hello!");

		// ***********************************************************
		// Ecran principal: �tape 1 � r�cup�ration du login dans MainScreen
		// Code:1-3
		// ***********************************************************
		// Is there any intent parameters sent by the calling activity?
		Bundle extras;
		String login = "";
		if (null != (extras = getIntent().getExtras())) {
			// extract the user login from the login screen
			login = extras.getString(Login.INTENT_PARAM_LOGIN);
		} else {
			Toast.makeText(this, "MainScreen Error: login is missing..",
					Toast.LENGTH_LONG).show();
		}
		// display the user login in the title
		this.setTitle(this.getTitle().toString() + " by " + login);

		// ***********************************************************
		// Ecran principal: �tape2 � partie saisie & m�t�o observ�e Code:8-9
		// ***********************************************************
		mWeather0ImgView = (ImageView) findViewById(R.id.Desc0ImageView);
		mCurrentWeatherTitleTxt = (TextView) findViewById(R.id.currentWeatherTitle);
		mDescriptionTxt = (TextView) findViewById(R.id.DescriptionText);
		mTemperatureTxt = (TextView) findViewById(R.id.TemperatureText);
		mCloudCoverTxt = (TextView) findViewById(R.id.CloudCoverTxtView);

		mRainPrecipitTxt = (TextView) findViewById(R.id.RainTextD1);
		mWindSpeedTxt = (TextView) findViewById(R.id.WindSpeedTextD1);
		mWindDirectionTxt = (TextView) findViewById(R.id.WindDirTextD1);
		mTempMaxTxt = (TextView) findViewById(R.id.TempMaxTextD1);
		mTempMinTxt = (TextView) findViewById(R.id.TempMinTextD1);
		mWeather1TitleTxt = (TextView) findViewById(R.id.forecastD1WeatherTitle);
		mWeather1ImgView = (ImageView) findViewById(R.id.Desc1ImageView);
		mDescriptionDayAfterTxt = (TextView) findViewById(R.id.DescTextD1);

		// read the preferences settings: Celsius or Fahrenheit??
		String tempUnit = Tools.getPreferences(this,
				Tools.PREF_TEMPERATURE_UNIT_ID);
		Log.d("MainScreen:onCreate()", "** Temperature unit=" + tempUnit);

		// ***********************************************************
		// Ecran principal: �tape2 � partie saisie & m�t�o observ�e Code:10-14
		// ***********************************************************
		// set the forecast OK button handler
		Button okButton = (Button) findViewById(R.id.searchButton);
		okButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// get user inputs from GUI
				mCountryName = ((EditText) findViewById(R.id.countryTxtEdit))
						.getText().toString();
				mCityName = ((EditText) findViewById(R.id.cityTxtEdit))
						.getText().toString();
				Log.d("MainScreen:onClick()", "** Country=" + mCountryName
						+ " City=" + mCityName);
				Log.d("MainScreen:onClick()", "** Start HTTP request!");

				if (mCountryName.equals("") || mCityName.equals("")) {
					Log.d("MainScreen:onClick()",
							"** Bad input: country or city is missing!!");
					return;
				}

				// ***********************************************************
				// Ecran principal: �tape4 � lancement du thread & requ�te
				// serveur Code:1
				// ***********************************************************
				// start the thread in charge of performing the server request
				Thread serverThread = new Thread(mGetWeatherDataRunnable);
				startProgressDialog();
				serverThread.start();
			}
		});
	}

	@Override
	protected void onDestroy() {
		// Auto-generated method stub
		super.onDestroy();
		Log.d("MainScreen:onDestroy()", "** Hello!");
	}

	@Override
	protected void onPause() {
		// Auto-generated method stub
		super.onPause();
		Log.d("MainScreen:onPause()", "** Hello!");
	}

	@Override
	protected void onRestart() {
		// Auto-generated method stub
		super.onRestart();
		Log.d("MainScreen:onRestart()", "** Hello!");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.d("MainScreen:onRestoreInstanceState()", "** Hello!");
	}

	@Override
	protected void onResume() {
		// Auto-generated method stub
		super.onResume();
		Log.d("MainScreen:onResume()", "** Hello!");
	}

	/**
	 * DESC: onSaveInstanceState() is called by Android if the Activity is being
	 * stopped and may be killed before it is resumed. It is the counterpart to
	 * the onCreate() method. The savedInstanceState Bundle passed in to
	 * onCreate() is the same Bundle that we construct as outState in this
	 * onSaveInstanceState() method.
	 * 
	 * @see onCreate()
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.d("MainScreen:onSaveInstanceState()", "** Hello!");
	}

	@Override
	protected void onStart() {
		// Auto-generated method stub
		super.onStart();
		Log.d("MainScreen:onStart()", "** Hello!");
	}

	@Override
	protected void onStop() {
		// Auto-generated method stub
		super.onStop();
		Log.d("MainScreen:onStop()", "** Hello!");
	}

} // end class
