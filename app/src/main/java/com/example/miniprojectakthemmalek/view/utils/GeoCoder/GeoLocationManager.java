package com.example.miniprojectakthemmalek.view.utils.GeoCoder;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GeoLocationManager   {



    Double latitude,longitude;
    List<Address> address;
    Context context;

    public GeoLocationManager(Context context)
    {
        this.context = context;

    }

    public GeoLocationManager(Context context,Double latitude,Double longitude)
    {
        this.latitude=latitude;
        this.longitude=longitude;
        this.context = context;
    }



    public String getAddress(Context context, double lat, double lng) {


        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);

            String add = obj.getAddressLine(0);
            add = add + "," + obj.getAdminArea();
            add = add + "," + obj.getCountryName();

            return add;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }





    public Address getLatLonFromAdress(String adress)
    {
        try {
            Geocoder coder = new Geocoder(context, Locale.getDefault());

            address = coder.getFromLocationName(adress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            location.getLatitude();
            location.getLongitude();

            return location;

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;

    }


}
