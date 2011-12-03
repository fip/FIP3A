//-------------------------------------------------------------------------------------------------
//
//  Module Name: Login.java
//
//  General Description: Login activity 
//
//  Proxy emulator configuration: -http-proxy http://0.0.0.0:1234
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
// pcontrei     				        02/10/2010     Creation
//  
//-------------------------------------------------------------------------------------------------
package fr.enstb.tp.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {
  // intent parameter name:
  public static final String INTENT_PARAM_LOGIN = "Login";
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle aSavedInstanceState){
    super.onCreate(aSavedInstanceState);
    setContentView(R.layout.login);

    // set the ENTER handler button: Ecran de login Code:1
    Button enterButton = (Button) findViewById(R.id.EnterButton);
    enterButton.setOnClickListener(this);
  }

  /**
   * Enter button handler
   * <p> - check if the login is provided
   * <p> - launch the main activity: MainScreen
   */
  @Override
  public void onClick(View aView){
//    Log.d("Login::onClick()", "** Not emplemented yet!");
	  
    // get the login entered by the user:
    EditText loginTextEdit = (EditText) findViewById(R.id.loginText);
    String loginStr = loginTextEdit.getText().toString();

    // test if the login is not empty
    if(loginStr == null || loginStr.equals("")) {
      // login is missing => display a Toast error msg and return!
      Toast.makeText(this, "Invalid input: login is missing..", Toast.LENGTH_LONG).show();
    }
    else{
      // The login is not empty , just start the MainScreen activity
      // through an Intent.
      // - The login is provided to the called activity as a String parameter
      // - In the Intent use INTENT_PARAM_LOGIN as name for the login parameter 
      // Create and launch the intent:
      Intent mainScreenIntent = new Intent(Login.this, MainScreen.class);
      mainScreenIntent.putExtra(Login.INTENT_PARAM_LOGIN, loginStr);
      startActivity(mainScreenIntent);
    }
  }
  
} // end of class