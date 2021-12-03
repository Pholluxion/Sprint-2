package com.misiontic.sprint2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.misiontic.sprint2.models.Producto;

import java.util.ArrayList;


public class ListFragment extends Fragment {


    ArrayList<Producto> productos;

    public ListFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productos = new ArrayList<>();

        Producto producto = new Producto();

        producto.setImage(R.drawable.add_files);
        producto.setTitulo("Some product");
        producto.setDesc("Some desc");
        producto.setLatLng(new LatLng(20,20));

        productos.add(producto);
        productos.add(producto);
        productos.add(producto);
        productos.add(producto);
        productos.add(producto);
        productos.add(producto);
        productos.add(producto);
        productos.add(producto);
        productos.add(producto);
        productos.add(producto);

        if(productos.size() == 0){
            Toast.makeText(getContext(), "No hay productos agregados", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new MyAdapter(this.getContext(),productos));


        return view;
    }
}