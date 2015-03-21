package com.example.omar.shopfree.signup;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omar.shopfree.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class SignUpActivity extends ActionBarActivity {
    private EditText ettName,etPhone,etAddress, etPassword;
    private Button  Start;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        progressDialog = new ProgressDialog(this);

    }
    public  void  onSignUpClick(View view){
        SignUpTask task= new SignUpTask();
        task.execute("https://dl.dropboxusercontent.com/u/84824665/shopfree/signup");
        progressDialog.show();

    }
    public class SignUpTask extends AsyncTask<String,Void,SignUpReply>{

        @Override
        protected SignUpReply doInBackground(String... params) {try {
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();

            connection.connect();

            InputStream is = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            Gson gson = new Gson();

            return gson.fromJson(reader,SignUpReply.class);
        } catch (MalformedURLException e) {
            Log.d("doInBackground", e.getMessage());
        } catch (IOException e) {
            Log.d("doInBackground", e.getMessage());
        }


            return null;
        }

        @Override
        protected void onPostExecute(SignUpReply signUpReply) {
            progressDialog.dismiss();
            Toast.makeText(SignUpActivity.this,"ResponseId"+signUpReply.getResponseId(),Toast.LENGTH_LONG).show();
      ;

        }
    }



}
