package com.example.omar.shopfree.login;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.omar.shopfree.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Nayle on 3/20/2015.
 */


public class LoginActivity extends Activity {


    //Set Error Status
    static boolean errored = false;
    Button b;
    TextView statusTV;
    EditText userNameET , passWordET;
    ProgressBar webservicePG;
    String editTextUsername;
    boolean loginStatus;
    String editTextPassword;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Name Text control
       userNameET = (EditText) findViewById(R.id.username);
       passWordET = (EditText) findViewById(R.id.password);

        //Button to trigger web service invocation
        b = (Button) findViewById(R.id.LOGIN);

        //Button Click Listener
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Check if text controls are not empty
                if (userNameET.getText().length() != 0 && userNameET.getText().toString() != "") {
                    if(passWordET.getText().length() != 0 && passWordET.getText().toString() != ""){
                        editTextUsername = userNameET.getText().toString();
                        editTextPassword = passWordET.getText().toString();
                        statusTV.setText("");

                        //Create instance for AsyncCallWS
                        AsyncCallWS task = new AsyncCallWS();
                        //Call execute
                        task.execute();
                    }
                    //If Password text control is empty
                    else{
                        statusTV.setText("Please enter Password");
                    }
                    //If Username text control is empty
                } else {
                    statusTV.setText("Please enter Username");
                }
            }
        });
    }


    private class AsyncCallWS extends AsyncTask<String, Void, LoginReply> {
        @Override
        protected LoginReply doInBackground(String... params) {

            InputStream is = null;

            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();

                connection.connect();

                is = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);

                Gson gson = new Gson();

                LoginReply loginReply = gson.fromJson(reader, LoginReply.class);

                return loginReply;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }


        @Override
        //Once WebService returns response
        protected void onPostExecute(LoginReply result) {
//            Intent intent = new Intent(LoginActivity.this, );
//            startActivity(intent);

        }
    }
}
