package com.example.asus.ebill;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class EBill extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        //for Firebase offline capabilities

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //For Picasso offline capability

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);


    }
}
