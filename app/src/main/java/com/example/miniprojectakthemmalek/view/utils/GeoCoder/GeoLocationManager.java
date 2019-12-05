package com.example.miniprojectakthemmalek.view.utils.GeoCoder;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GeoLocationManager extends AppCompatActivity {


    private String fournisseur;


    LocationManager mLocationManager;
 //   Location myLocation = getLastKnownLocation();


    public void initLocalisation(Context context,LocationManager locationManager)
    {

            Criteria criteres = new Criteria();

            // la précision  : (ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision)
            criteres.setAccuracy(Criteria.ACCURACY_FINE);

            // l'altitude
            criteres.setAltitudeRequired(true);

            // la direction
            criteres.setBearingRequired(true);

            // la vitesse
            criteres.setSpeedRequired(true);

            // la consommation d'énergie demandée
            criteres.setCostAllowed(true);
            criteres.setPowerRequirement(Criteria.POWER_HIGH);

            fournisseur = locationManager.getBestProvider(criteres, true);

            System.out.println("------------>"+fournisseur);
            Log.d("GPS", "fournisseur : " + fournisseur);


        if(fournisseur != null)
        {
            // dernière position connue



                Location localisation = locationManager.getLastKnownLocation(fournisseur);


                Log.d("GPS", "localisation : " + localisation.toString());
                String coordonnees = String.format("Latitude : %f - Longitude : %f\n", localisation.getLatitude(), localisation.getLongitude());
                Log.d("GPS", "coordonnees : " + coordonnees);
                String autres = String.format("Vitesse : %f - Altitude : %f - Cap : %f\n", localisation.getSpeed(), localisation.getAltitude(), localisation.getBearing());
                Log.d("GPS", autres);
                //String timestamp = String.format("Timestamp : %d\n", localisation.getTime());
                //Log.d("GPS", "timestamp : " + timestamp);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date(localisation.getTime());
                Log.d("GPS", sdf.format(date));
                System.out.println("Latitude ----------------> "+localisation.getLatitude());
                System.out.println("Longitude ----------------> "+localisation.getLongitude());

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(localisation.getLatitude(), localisation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                /*String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                */
                System.out.println(addresses);

            } catch (IOException e) {
                e.printStackTrace();
            }





        }






    }


}
