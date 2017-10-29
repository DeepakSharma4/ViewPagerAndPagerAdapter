package com.example.hp.viewpagerandpageradapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity implements MyListener {
    ImageView next_btn;
    ImageView prev_btn;
    /*@Bindv(R.id.next_btn)
    ImageView next_btn;
    @Bind(R.id.prev_btn)
    ImageView prev_btn;*/
    // Declare Variables
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] rank;
    String[] country;
    String[] population;
    int[] flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next_btn = findViewById(R.id.next_btn);
        //    prev_btn = findViewById(R.id.prev_btn);
        // Generate sample data
        rank = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        country = new String[]{"China", "India", "United States",
                "Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
                "Russia", "Japan"};

        population = new String[]{"1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886", "182,912,000",
                "170,901,000", "152,518,015", "143,369,806", "127,360,000"};

       /* flag = new int[] { R.drawable.china, R.drawable.india,
                R.drawable.unitedstates, R.drawable.indonesia,
                R.drawable.brazil, R.drawable.pakistan, R.drawable.nigeria,
                R.drawable.bangladesh, R.drawable.russia, R.drawable.japan };*/

        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(MainActivity.this, rank, country);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(getItem(+1), true);
                //  viewPager.setCurrentItem();
            }
        });
   /*Registering receiver*/
        registerReceiver(saveForLater, new IntentFilter("removeFromCart"));

        viewPager.setCurrentItem(getItem(-1), true); //getItem(-1) for previous
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private BroadcastReceiver saveForLater = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            viewPager.setCurrentItem(getItem(+1), true); //getItem(-1) for previous

           /* // Extract data included fr the Intent
            String prod_id = intent.getStringExtra("prod_id");
            String item_id = intent.getStringExtra("item_id");

            if (mAppUtils.IsInternetOn(getActivity())) {
            *//*
            method to get listing of all the products fr my cart
             *//*
                saveForLater(prod_id, item_id);

            } else {
                mAppUtils.showSnackBar(mParent, getActivity().getResources().getString(R.string.internet_error));
            }*/

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
         /*unregistering receivers*/
        if (saveForLater != null)
            unregisterReceiver(saveForLater);
    }


    @Override
    public void callback(int pos) {
        viewPager.setCurrentItem(pos - 1, true); //getItem(-1) for previous
    }
}
