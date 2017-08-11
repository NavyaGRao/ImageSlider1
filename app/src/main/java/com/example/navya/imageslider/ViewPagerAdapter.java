package com.example.navya.imageslider;

import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Navya on 01-08-2017.
 */

class ViewPagerAdapter extends PagerAdapter {

    Context context;
   // String[] rank;
    String[] country;
    String[] population;
   // int[] flag;
   private LayoutInflater inflater;
    private String path;
   private int l;
    String [] flag;
  //  ArrayList<String> imageList = new ArrayList<String>();


    public ViewPagerAdapter(Context context , String files , int l ,String [] flag) {

       // this.flag = flag;
        this.flag= flag;
        this.context = context;
        this.path = files;
        this.l = l;


    }
   /* void add(String path)
    {
        imageList.add(path);
    }*/

    @Override
    public int getCount() {
        return l;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       // TextView txtrank;
       // TextView txtcountry;
       // TextView txtpopulation;
        ImageView imgflag;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);
        imgflag = (ImageView) itemView.findViewById(R.id.flag) ;
/*
        // Locate the TextViews in viewpager_item.xml
        txtrank = (TextView) itemView.findViewById(R.id.rank);
        txtcountry = (TextView) itemView.findViewById(R.id.country);
        txtpopulation = (TextView) itemView.findViewById(R.id.population);

        // Capture position and set to the TextViews
        txtrank.setText(rank[position]);
        txtcountry.setText(country[position]);
        txtpopulation.setText(population[position]);
        */

        // Locate the ImageView in viewpager_item.xml
        // Capture position and set to the ImageView
        imgflag.setImageResource(Integer.parseInt(flag[position]));

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
