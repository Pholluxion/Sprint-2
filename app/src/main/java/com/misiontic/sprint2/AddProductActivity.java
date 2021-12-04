package com.misiontic.sprint2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.misiontic.sprint2.models.Producto;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    //private Producto producto;
    private EditText nombre,desc,lat,lon;
    private Button btnAdd;
    ArrayList<Producto> productos =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //getProducts();

        this.nombre = findViewById(R.id.txtNombre);
        this.desc   = findViewById(R.id.editTextTextDesc);
        this.lat    = findViewById(R.id.editTextTextLatitud);
        this.lon    = findViewById(R.id.editTextTextLongitud);
        this.btnAdd = findViewById(R.id.buttonAddP);

        //TODO: VERIFICAR QUE NINGUN CAMPO ESTÉ VACIO


        this.firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            Toast.makeText(getApplicationContext(), firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();

            this.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Producto producto = new Producto();

                    producto.setImage(R.drawable.add_files);
                    producto.setDesc(desc.getText().toString());
                    producto.setTitulo(nombre.getText().toString());
                    producto.setLat(Double.parseDouble(lat.getText().toString()));
                    producto.setLng(Double.parseDouble(lon.getText().toString()));

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference(Producto.class.getSimpleName()).child(firebaseUser.getUid());


                    productos.add(producto);

                    myRef.setValue(productos);

                }
            });

        }


    }

  /*  private void getProducts(){

        this.firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Producto.class.getSimpleName()).child(firebaseUser.getUid());

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                 productos = (ArrayList<Producto>) dataSnapshot.getValue();
                System.out.println(productos);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }
*/


}