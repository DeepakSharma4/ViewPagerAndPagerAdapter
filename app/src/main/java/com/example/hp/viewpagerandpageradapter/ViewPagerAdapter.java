package com.example.hp.viewpagerandpageradapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by hp on 10/29/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {
    MyListener ml;

    // Declare Variables
    Context context;
    String[] rank;
    String[] country;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, String[] rank, String[] country) {
        this.context = context;
        this.rank = rank;
        this.country = country;
        this.ml = ml;
    }

    @Override
    public int getCount() {
        return rank.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        // Declare Variables
        TextView txtrank;
        TextView txtcountry;
        final ImageView imageViewleft;
        final ImageView imageViewRight;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txtrank = (TextView) itemView.findViewById(R.id.rank);
        txtcountry = (TextView) itemView.findViewById(R.id.countrylabel);
        imageViewleft = (ImageView) itemView.findViewById(R.id.prev_btn);
      //  imageViewRight = (ImageView) itemView.findViewById(R.id.next_btn);


        // Capture position and set to the TextViews
        txtrank.setText(rank[position]);
        txtcountry.setText(country[position]);
        imageViewleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ml.callback(Integer.parseInt(rank[position]));
            }
        });






     /*   // Locate the ImageView in viewpager_item.xml
        imgflag = (ImageView) itemView.findViewById(R.id.flag);
        // Capture position and set to the ImageView
        imgflag.setImageResource(flag[position]);*/

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

  /*  public void MyLogicToIntimateOthere() {
        ml.callback(getItemPosition(country));
    }
*/
}
