package com.example.minhd.drinky;

import android.app.DialogFragment;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.minhd.drinky.Fragment.MapFragment.geocoder;
import static com.example.minhd.drinky.Fragment.MapFragment.mGoogleMap;


/**
 * Created by minhd on 17/08/04.
 */

public class AddDialog extends DialogFragment implements View.OnClickListener {

    private View view;
    private EditText edtAdd ;
    public static String adresss;
    private double longitude;
    private double latitude;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_dialog_layout, container, false) ;

        edtAdd = view.findViewById(R.id.edt_add_location);

        view.findViewById(R.id.btn_add).setOnClickListener(this);

        return view ;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(1200, 700);
    }

    @Override
    public void onClick(View view) {
        makerAdress();
        dismiss();
    }

    private void makerAdress() {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        String [] arr = {edtAdd.getText().toString()};

        MarkerOptions options = new MarkerOptions();
        Marker marker ;

        try {
            for (int i = 0; i < arr.length; i++) {
                List<Address> addresses = geocoder.getFromLocationName(arr[i], 1);
                Address address = addresses.get(0);
                double longitude = address.getLongitude();
                double latitude = address.getLatitude();
                Log.d("Adres" , arr.length+"");
                Log.d("Adres", latitude + "\n" + longitude);

                LatLng latLng = new LatLng(latitude, longitude);
                options.
                        icon(BitmapDescriptorFactory.
                                defaultMarker(BitmapDescriptorFactory.HUE_RED));
                options.position(latLng);
                options.title("Add Location");
                options.snippet(getLocation(latLng));
                marker = mGoogleMap.addMarker(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getLocation(LatLng latLng) {
        try {
            List<Address> addresses =
                    geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses == null || addresses.size() == 0) {
                return null;
            }
            String result = "";
            int maxLine = addresses.get(0).getMaxAddressLineIndex();
            result = addresses.get(0).getAddressLine(0);
            for (int i = 1; i < maxLine; i++) {
                result += ", " + addresses.get(0).getAddressLine(i);
            }
            result += ", " + addresses.get(0).getCountryName();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
