package com.example.chris.beerme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Matt on 4/10/2018.
 */

public class BeerAdapter extends BaseAdapter {

    // adapter takes the app itself and a list of data to display
    private Context mContext;
    private ArrayList<Beer> mBeerList;
    private LayoutInflater mInflater;

    // constructor
    public BeerAdapter(Context mContext, ArrayList<Beer> mBeerList){

        // initialize instances variables
        this.mContext = mContext;
        this.mBeerList = mBeerList;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // methods
    // a list of methods we need to override

    // gives you the number of beers in the data source
    @Override
    public int getCount() {
        return mBeerList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mBeerList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        //check if the view already exists
        //if yes, you don't need to inflate and findviewbyid again
        if(convertView == null) {
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_beer, parent, false);
            // add the views to the holder
            holder = new ViewHolder();
            // views
            holder.nameTextView = convertView.findViewById(R.id.beer_list_name);
            holder.abvTextView = convertView.findViewById(R.id.beer_list_abv);
            holder.thumbnailImageView = convertView.findViewById(R.id.beer_list_thumbnail);
            holder.styleTextView = convertView.findViewById(R.id.beer_list_style);
            holder.categoryTextView = convertView.findViewById(R.id.beer_list_category);

            //add holder to the view for future use
            convertView.setTag(holder);
        }
        else{
            // get the view holder from converview
            holder = (ViewHolder)convertView.getTag();
        }

        // get corresonpinding recipe for each row
        final Beer beer = (Beer) getItem(position);

        // get relavate subview of the row view
        TextView nameTextView = holder.nameTextView;
        TextView abvTextView = holder.abvTextView;
        TextView styleTextView = holder.styleTextView;
        TextView categoryTextView = holder.categoryTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        TextView numberOfRecipesTextView = holder.numberOfRecipesTextView;

        // update the row view's textviews and imageview to display the information
        nameTextView.setText(beer.name);
        nameTextView.setTextSize(18);

        abvTextView.setText(beer.abv + "%");
        abvTextView.setTextSize(12);

        styleTextView.setText(beer.style);
        styleTextView.setTextSize(12);

        categoryTextView.setText(beer.category);
        categoryTextView.setTextSize(12);

        // imageView
        // use Picasso library to load image from the image url
        Picasso.with(mContext).load("http://www.iemoji.com/view/emoji/429/food-drink/clinking-beer-mugs").into(thumbnailImageView);

        return convertView;
    }

    private static class ViewHolder {
        public TextView nameTextView;
        public TextView abvTextView;
        public TextView styleTextView;
        public TextView categoryTextView;
        public ImageView thumbnailImageView;
        public TextView numberOfRecipesTextView;
    }
}

