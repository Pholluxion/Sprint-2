package com.misiontic.sprint2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        if(getIntent().hasExtra("ltd") && getIntent().hasExtra("lng")){
            double latitude  = getIntent().getDoubleExtra("ltd",0);
            double longitude = getIntent().getDoubleExtra("lng",0);

            System.out.println(latitude + "  " + longitude);

            replaceFragment(new MapsFragment(new LatLng(latitude,longitude)));
        }


    }

    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.holderMap, fragment);

        fragmentTransaction.addToBackStack(fragment.toString());

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.commit();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent goToHome = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(goToHome);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}