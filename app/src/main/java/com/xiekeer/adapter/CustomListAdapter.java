package com.xiekeer.adapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.xiekeer.R;
import com.xiekeer.myapplication.AppController;
import com.xiekeer.model.Shoe;

import java.util.List;

import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;



/**
 * Created by caozheng on 9/16/15.
 */
public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Shoe> shoeItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Shoe> shoeItems) {
        this.activity = activity;
        this.shoeItems = shoeItems;
    }

    @Override
    public int getCount() {
        return shoeItems.size();
    }

    @Override
    public Object getItem(int position) {
        return shoeItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_row, null);
        }

        if(imageLoader == null) {
            imageLoader = AppController.getInstance().getImageLoader();
        }
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView shoeTitle = (TextView) convertView.findViewById(R.id.shoe_title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        //getting shoe data for the row
        Shoe s = shoeItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(s.getThumbnailUrl(), imageLoader);

        //title
        shoeTitle.setText(s.getTitle());

        //rating
        rating.setText("Rating" + String.valueOf(s.getRating()));

        // genre
        String genreStr = "";
        for (String str : s.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);

        // release year
        year.setText(String.valueOf(s.getYear()));

        return convertView;
    }
}
