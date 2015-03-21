package com.example.omar.shopfree.shoppingcart;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.omar.shopfree.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ShoppingCartActivity extends ActionBarActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);
        listView = (ListView) findViewById(R.id.list_shoppingcart);

        ShoppingTask task =new ShoppingTask();
        task.execute("https://dl.dropboxusercontent.com/u/84824665/shopfree/shoppinglist");

    }


    class ShoppingTask extends AsyncTask<String, Void, ShoppingCartReply> {


        @Override
        protected ShoppingCartReply doInBackground(String... params) {
            InputStream is = null;
            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();

                connection.connect();

                is = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);

                Gson gson = new Gson();

                ShoppingCartReply shoppingCartReply = gson.fromJson(reader, ShoppingCartReply.class);

                return shoppingCartReply;

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
        protected void onPostExecute(ShoppingCartReply shoppingCartReply) {
            if(shoppingCartReply == null){

                return;

            }
            else {
                ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(ShoppingCartActivity.this, shoppingCartReply.getProducts());
                listView.setAdapter(shoppingListAdapter);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
