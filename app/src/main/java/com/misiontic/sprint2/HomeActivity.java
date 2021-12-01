package com.misiontic.sprint2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            closeApplication();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void closeApplication() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_baseline_exit_to_app)
                .setTitle("¿Desea cerrar sesión?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goToLog = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(goToLog);
                        finish();
                    }
                }).show();



    }
}