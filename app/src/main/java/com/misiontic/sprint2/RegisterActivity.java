package com.misiontic.sprint2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.misiontic.sprint2.db.DataBaseUser;
import com.misiontic.sprint2.helpers.Validator;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,pass_1,pass_2;
    private DataBaseUser dataBaseUser;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.firebaseAuth = FirebaseAuth.getInstance();

        this.dataBaseUser = new DataBaseUser(this);

        this.email   = findViewById(R.id.editTextTextEmailAddressRegister);
        this.pass_1  = findViewById(R.id.editTextTextPasswordRegister);
        this.pass_2  = findViewById(R.id.editTextTextPasswordRegisterConfirm);
        Button registerBtn = findViewById(R.id.buttonRegister);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validator validator = new Validator();

                String emailStr  = email.getText().toString();
                String pass_1Srt = pass_1.getText().toString();
                String pass_2Srt = pass_2.getText().toString();

                if (validator.confirmInputsRegister(emailStr,pass_1Srt,pass_2Srt)){

                        if(validator.confirmInputsRegisterPass(pass_1Srt,pass_2Srt)){

                            //Crear usuarios locales
                           /* if(dataBaseUser.insertUser(emailStr,pass_1Srt)){

                                Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                                Intent goToLogin = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(goToLogin);
                                finish();


                            }else {

                                Toast.makeText(getApplicationContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();

                            }*/

                            //Registro con FireBase
                            crearNewUsuario(emailStr,pass_1Srt);

                        }else{

                            Toast.makeText(getApplicationContext(), "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();

                        }

                }else {
                    Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void crearNewUsuario(String email, String password){

        this.firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "Registro completado con exito!!", Toast.LENGTH_SHORT).show();
                    Intent goToLogin = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(goToLogin);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "No se ha podido registrar", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



}