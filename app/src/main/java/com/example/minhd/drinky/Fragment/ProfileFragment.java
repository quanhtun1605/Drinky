package com.example.minhd.drinky.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.minhd.drinky.Activity.MainActivity;
import com.example.minhd.drinky.R;
import com.example.minhd.drinky.TabMessage;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        BottomBar bottomBar = view.findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_star);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
//                messageView.setText(TabMessage.get(tabId, false) +"");
                if (TabMessage.getPos(tabId, false) == 2) {
                    MapFragment mapFragment = new MapFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.relayout, mapFragment);
                    transaction.addToBackStack("ahihi");
                    transaction.commit();
                }
                if (TabMessage.getPos(tabId, false) == 1) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                if (TabMessage.getPos(tabId, false) == 3) {
                    FavoriteFragment fragment = new FavoriteFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.relayout, fragment);
                    transaction.addToBackStack("ahihi");
                    transaction.commit();
                }
                if (TabMessage.getPos(tabId, false) == 4) {


                }

            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }


}
