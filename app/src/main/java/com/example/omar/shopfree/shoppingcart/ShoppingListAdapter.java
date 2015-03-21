package com.example.omar.shopfree.shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omar.shopfree.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by MONIEM TANTAWI on 3/20/2015.
 */
public class ShoppingListAdapter extends ArrayAdapter<Product> {

    List<Product> mProducts=null;
    Context mContext=null;
    public ShoppingListAdapter(Context context, List<Product> shoppingCart){
        super(context,R.layout.activity_shoppingcart);
         mContext=context;
        mProducts=shoppingCart;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            LayoutInflater inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.shopping_item, null , false);

        }
        ImageView imageView =(ImageView) convertView.findViewById(R.id.image_item);
        TextView textView =(TextView) convertView.findViewById(R.id.text_item);
        Button button =(Button) convertView.findViewById(R.id.button_item);
        Product product =  mProducts.get(position);

        Picasso.with(mContext).load(product.getImage()).into(imageView);

        textView.setText(product.getName());


        return convertView;

    }

    @Override
    public int getCount() {
        return mProducts.size();
    }
}
