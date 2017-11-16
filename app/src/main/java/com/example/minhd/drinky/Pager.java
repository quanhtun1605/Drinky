package com.example.minhd.drinky;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by minhd on 17/07/13.
 */

public class Pager extends PagerAdapter {
    private Activity activity;
    private int[] drawableIDs;

    public Pager(Activity activity, int[] drawableIDs){

        this.activity = activity;
        this.drawableIDs = drawableIDs;
    }

    @Override
    public int getCount() {
        return drawableIDs.length;
    }

    @Override
    public Object instantiateItem(View collection, int position) {

        ImageView imageView = new ImageView(activity);

        imageView.setBackgroundResource(drawableIDs[position]);

        ((ViewPager) collection).addView(imageView,0);

        return imageView;
    }


    @Override
    public void destroyItem(View collection, int position, Object view) {
        ((ViewPager) collection).removeView((ImageView) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view== object;
    }


    @Override
    public void finishUpdate(View arg0) {}

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {}

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {}
}
