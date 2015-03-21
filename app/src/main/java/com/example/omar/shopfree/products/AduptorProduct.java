package com.example.omar.shopfree.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omar.shopfree.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Mohamed on 3/18/2015.
 */
public class AduptorProduct extends ArrayAdapter<ProductJSON> {
    java.util.List<ProductJSON> mProducts=null;
    Context mContext=null;

    public AduptorProduct(Context context, java.util.List<ProductJSON> products) {
        super(context, R.layout.activity_products);
        mContext=context;
        mProducts=products;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_products, null, false);
        }

        TextView productName = (TextView) convertView.findViewById(R.id.product_name);
        TextView productPrice = (TextView) convertView.findViewById(R.id.product_price);
        ImageView productImage=(ImageView) convertView.findViewById(R.id.product_image);
        ProductJSON currentProduct = mProducts.get(position);
        productName.setText(currentProduct.getName());
        productPrice.setText("20 $");
        Picasso.with(mContext).load(currentProduct.getImage()).into(productImage);

        return convertView;
    }
}
