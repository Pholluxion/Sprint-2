package com.misiontic.sprint2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.misiontic.sprint2.db.DataBaseUser;
import com.misiontic.sprint2.helpers.Validator;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.firebaseAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DataBaseUser dataBaseUser = new DataBaseUser(this);

        TextView goToRegister = findViewById(R.id.goToReg);
        Button btnLogin = findViewById(R.id.buttonLogin);
        EditText txtEmail = findViewById(R.id.editTextTextEmailAddressLogin);
        EditText txtPass = findViewById(R.id.editTextTextPasswordLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailStr  = txtEmail.getText().toString();
                String passSrt   = txtPass.getText().toString();

                Validator validator = new Validator();


             if(validator.confirmInputsLogin(emailStr,passSrt)){


                 inicioSesion(emailStr,passSrt);

                 //Inicio de sesión LOCAL

                    /*if(dataBaseUser.isUserRegister(emailStr)){
                        if(dataBaseUser.isPassOk(emailStr,passSrt)){

                            Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();

                            Intent goToHome = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(goToHome);
                            finish();

                        }else {

                           // Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            setMessage("Contraseña incorrecta");

                        }

                    }else{

                        //Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_SHORT).show();
                        setMessage("Usuario no registrado");
                    }*/




                }else{
                    Toast.makeText(getApplicationContext(), "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                }



            }
        });
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToReg = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(goToReg);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null ){

            Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_SHORT).show();

            Intent goToHome = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(goToHome);
            finish();

        }


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
                .setTitle("¿Realmente desea cerrar la aplicación?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid()); //Su funcion es algo similar a lo que se llama cuando se presiona el botón "Forzar Detención" o "Administrar aplicaciones", lo cuál mata la aplicación
                        //finish(); Si solo quiere mandar la aplicación a segundo plano
                    }
                }).show();



    }

    private void setMessage(String message){

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_baseline_error)
                .setTitle(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null).show();
    }


    private void  inicioSesion(String email, String password){

        this.firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();

                    Intent goToHome = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(goToHome);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


}