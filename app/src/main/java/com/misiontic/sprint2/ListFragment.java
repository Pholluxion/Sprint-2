package com.misiontic.sprint2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.misiontic.sprint2.models.Producto;

import java.util.ArrayList;
import java.util.HashMap;


public class ListFragment extends Fragment {


    ArrayList<Producto> productos;
    FloatingActionButton floatingActionButton;
    private FirebaseAuth firebaseAuth;

    public ListFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Producto producto = new Producto();

        productos = new ArrayList<>();

        getProducts();

        producto.setImage(R.drawable.add_files);
        producto.setTitulo("Some product");
        producto.setDesc("Some desc");
        producto.setLat(20.0);
        producto.setLng(20.0);



        if(productos.size() == 0){
            Toast.makeText(getContext(), "No hay productos agregados", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_list, container, false);




        System.out.println(productos);


        RecyclerView recyclerView = view.findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        MyAdapter myAdapter = new MyAdapter(this.getContext(),productos);
        recyclerView.setAdapter(myAdapter);


        floatingActionButton = view.findViewById(R.id.addProduct);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAdd= new Intent(getContext(),AddProductActivity.class);
                startActivity(goToAdd);
            }
        });

        return view;
    }

    private void getProducts(){

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


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }



}