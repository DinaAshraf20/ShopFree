package com.example.omar.shopfree.ordernow;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.omar.shopfree.R;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class OrderConfirmActivity extends ActionBarActivity {
    private EditText address, phoneNumber;
    private Button order;
    private ProgressDialog mProgress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        address = (EditText) findViewById(R.id.text_address);
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Hello world!");
        phoneNumber = (EditText) findViewById(R.id.text_phone_number);
        order = (Button) findViewById(R.id.order_now);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderNowTask task = new OrderNowTask();
                mProgress.show();
                task.execute("https://dl.dropboxusercontent.com/u/84824665/shopfree/orderconfirmed");
            }
        });

    }


    class OrderNowTask extends AsyncTask<String, Void, OrderNowReply> {
        @Override
        protected OrderNowReply doInBackground(String... params) {
            Log.d("doInBackground", "doInBackground");

            try {
                String param = params[0];
                URL url = new URL(param);
                URLConnection connection = url.openConnection();

                connection.connect();

                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                Gson gson = new Gson();

                return gson.fromJson(reader, OrderNowReply.class);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(OrderNowReply orderNowReply) {
            mProgress.dismiss();


//         Toast.makeText(OrderConfirmActivity.this, "Do in background", Toast.LENGTH_LONG).show();
        }
    }
}
