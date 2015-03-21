package com.example.omar.shopfree.products;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.omar.shopfree.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ProductsActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private EditText mTextSearch;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        listView = (ListView) findViewById(R.id.product_list);
        mTextSearch = (EditText) findViewById(R.id.search_src_text);
//        Product[] products = new Product[]{
//                new Product("Meet1", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet2", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet3", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet4", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet5", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet6", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet7", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet8", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet1", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet2", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet3", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet4", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet5", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet6", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet7", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet8", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet1", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet2", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet3", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet4", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet5", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet6", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet7", "20", "http://openweathermap.org/img/w/10n.png"),
//                new Product("Meet8", "20", "http://openweathermap.org/img/w/10n.png")
//        };
//        AduptorProduct aduptorProduct = new AduptorProduct(this, products);
//
//        listView.setAdapter(aduptorProduct);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ForscustTask task = new ForscustTask();
        task.execute("https://dl.dropboxusercontent.com/u/84824665/shopfree/products");


    }


    class ForscustTask extends AsyncTask<String, Void, ProductsReply> {

        @Override
        protected ProductsReply doInBackground(String... params) {
            InputStream IS = null;
            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                IS = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(IS);


                Gson gson = new Gson();

                ProductsReply example = gson.fromJson(reader, ProductsReply.class);

                return example;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (IS != null)
                        IS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onPostExecute(ProductsReply example) {
            if (example == null) {
                Toast.makeText(ProductsActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                return;
            } else {
                AduptorProduct aduptorProduct = new AduptorProduct(ProductsActivity.this, example.getProducts());
                listView.setAdapter(aduptorProduct);

            }
        }
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                Toast.makeText(this, R.string.title_Categories, Toast.LENGTH_SHORT).show();
//                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                Toast.makeText(this, R.string.title_Shopping, Toast.LENGTH_SHORT).show();
//                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                Toast.makeText(this, R.string.title_Profile, Toast.LENGTH_SHORT).show();
//                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                Toast.makeText(this, R.string.title_About, Toast.LENGTH_SHORT).show();
//                mTitle = getString(R.string.title_section3);
                break;

        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.products, menu);
            restoreActionBar();
            return true;
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        if (item.getItemId() == R.id.action_search) {
//            if (mTextSearch.getSystemUiVisibility() == 0) {
//                mTextSearch.setSystemUiVisibility(1);
//
//            } else {
//                mTextSearch.setSystemUiVisibility(0);
//            }

//            return true;
//        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((ProductsActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
