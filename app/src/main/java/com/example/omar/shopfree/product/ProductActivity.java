package com.example.omar.shopfree.product;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omar.shopfree.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ProductActivity extends ActionBarActivity {
    private TextView tvProductName;
    private ImageView imgView;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        tvProductName = (TextView) findViewById(R.id.product_name);
        imgView = (ImageView) findViewById(R.id.img_view);
        tvDescription = (TextView) findViewById(R.id.Text_decription);

        ProductTask task = new ProductTask();

        task.execute("https://dl.dropboxusercontent.com/u/84824665/shopfree/product");

    }

    class ProductTask extends AsyncTask<String, Void, Product> {


        @Override
        protected Product doInBackground(String... params) {
            InputStream is = null;

            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();

                connection.connect();

                is = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);

                Gson gson = new Gson();

                Product product = gson.fromJson(reader, Product.class);

                return product;

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
        protected void onPostExecute(Product product) {
            if (product == null) {
                Toast.makeText(ProductActivity.this, R.string.err_product_failed, Toast.LENGTH_LONG).show();

                return;
            }
            Picasso.with(ProductActivity.this).load(product.getImage()).into(imgView);

            tvProductName.setText(product.getName());
            tvDescription.setText(product.getDescription());
        }
    }
}