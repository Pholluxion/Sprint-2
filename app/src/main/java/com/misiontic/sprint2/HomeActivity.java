package com.misiontic.sprint2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.pagina_1){

            //TODO: Implementar menu opc 1
            Toast.makeText(getApplicationContext(), "Pgina 1", Toast.LENGTH_SHORT).show();

        }else if(item.getItemId() == R.id.pagina_2){

            //TODO: Implementar menu opc 2
            Toast.makeText(getApplicationContext(), "Pagina 2", Toast.LENGTH_SHORT).show();

        }else if(item.getItemId() == R.id.cerrar_sesion){

            Toast.makeText(getApplicationContext(), "Hasta luego", Toast.LENGTH_SHORT).show();

            Intent goToLog = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(goToLog);
            finish();

        }


        return true;
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