package com.sunity.fridgeinventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sunity.fridgeinventory.entity.Aliment;

import java.net.URL;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private List<Aliment> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, List<Aliment> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.imageAliment = (ImageView) convertView.findViewById(R.id.imageAliment);
            holder.alimentBrand = (TextView) convertView.findViewById(R.id.textBrand);
            holder.alimentName = (TextView) convertView.findViewById(R.id.textName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Aliment aliment = this.listData.get(position);
        holder.alimentName.setText(aliment.getName());
        holder.alimentBrand.setText(aliment.getBrand());
        if (this.isValid(aliment.getImgURL())) {
            Picasso.get().load(aliment.getImgURL()).into(holder.imageAliment);
        }

        return convertView;
    }

    private static boolean isValid(String url) {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }

    static class ViewHolder {
        ImageView imageAliment;
        TextView alimentBrand;
        TextView alimentName;
    }


}
