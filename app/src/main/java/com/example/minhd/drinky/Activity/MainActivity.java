package com.example.minhd.drinky.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.minhd.drinky.CircleRefreshLayout.CircleRefreshLayout;
import com.example.minhd.drinky.Fragment.FavoriteFragment;
import com.example.minhd.drinky.Fragment.MapFragment;
import com.example.minhd.drinky.Fragment.ProfileFragment;
import com.example.minhd.drinky.Pager;
import com.example.minhd.drinky.R;
import com.example.minhd.drinky.TabMessage;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayoutNgon, mainLayoutKM, mainLayoutLa;

    private int[] images = {R.drawable.toco, R.drawable.dingtea, R.drawable.toco, R.drawable.dingtea,
            R.drawable.toco, R.drawable.dingtea, R.drawable.toco, R.drawable.dingtea};

    private View cell;

    private CircleRefreshLayout mRefreshLayout;


    private ViewPager viewPagerNgon, viewPagerKM, viewPagerLa;

    @Override
    public void onBackPressed() {

        if (viewPagerNgon != null && viewPagerNgon.isShown()) {

            viewPagerNgon.setVisibility(View.GONE);
        } else if (viewPagerKM != null && viewPagerKM.isShown()) {

            viewPagerKM.setVisibility(View.GONE);
        } else if (viewPagerLa != null && viewPagerLa.isShown()) {

            viewPagerLa.setVisibility(View.GONE);
        } else {

            super.onBackPressed();
        }
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setStatusBarTranslucent(true);
        setContentView(R.layout.activity_main);


        viewPagerNgon = findViewById(R.id._viewPagerNgon);

        mainLayoutNgon = findViewById(R.id._linearLayoutNgon);

        viewPagerKM = findViewById(R.id._viewPagerKM);

        mainLayoutKM = findViewById(R.id._linearLayoutKM);

        viewPagerLa = findViewById(R.id._viewPagerLa);

        mainLayoutLa = findViewById(R.id._linearLayoutLa);

        for (int i = 0; i < images.length; i++) {

            cell = getLayoutInflater().inflate(R.layout.cell, null);

            final ImageView imageView = cell.findViewById(R.id._image);
            imageView.setOnClickListener(v -> {

                viewPagerNgon.setVisibility(View.VISIBLE);
                viewPagerNgon.setAdapter
                        (new Pager(MainActivity.this, images));
                viewPagerNgon.setCurrentItem(v.getId());
            });

            imageView.setId(i);


            imageView.setImageResource(images[i]);

            mainLayoutNgon.addView(cell);
        }

        for (int i = 0; i < images.length; i++) {

            cell = getLayoutInflater().inflate(R.layout.cell, null);

            final ImageView imageView = cell.findViewById(R.id._image);
            imageView.setOnClickListener(v -> {

                viewPagerKM.setVisibility(View.VISIBLE);
                viewPagerKM.setAdapter
                        (new Pager(MainActivity.this, images));
                viewPagerKM.setCurrentItem(v.getId());
            });

            imageView.setId(i);


            imageView.setImageResource(images[i]);

            mainLayoutKM.addView(cell);
        }

        for (int i = 0; i < images.length; i++) {

            cell = getLayoutInflater().inflate(R.layout.cell, null);

            final ImageView imageView = cell.findViewById(R.id._image);

            imageView.setOnClickListener(v -> {

                viewPagerLa.setVisibility(View.VISIBLE);
                viewPagerLa.setAdapter
                        (new Pager(MainActivity.this, images));
                viewPagerLa.setCurrentItem(v.getId());
            });

            imageView.setId(i);


            imageView.setImageResource(images[i]);

            mainLayoutLa.addView(cell);
        }

        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        refeshItem();

                    }

                    @Override
                    public void completeRefresh() {

                    }
                });


        BottomBar bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
//                messageView.setText(TabMessage.get(tabId, false) +"");
                if (TabMessage.getPos(tabId, false) == 2) {

                    MapFragment mapFragment = new MapFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.relayout, mapFragment);
                    transaction.addToBackStack("ahihi");
                    transaction.commit();

                }
                if (TabMessage.getPos(tabId, false) == 1) {

                }
                if (TabMessage.getPos(tabId, false) == 3) {
                    FavoriteFragment fragment = new FavoriteFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.relayout, fragment);
                    transaction.addToBackStack("ahihi");
                    transaction.commit();
                    finish();

                }
                if (TabMessage.getPos(tabId, false) == 4) {
                    ProfileFragment fragment = new ProfileFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.relayout, fragment);
                    transaction.addToBackStack("ahihi");
                    transaction.commit();
                    finish();

                }

            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void refeshItem() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mRefreshLayout.finishRefreshing();

            }
        }, 3000);

//        mRefreshLayout.finishRefreshing();

    }

}

