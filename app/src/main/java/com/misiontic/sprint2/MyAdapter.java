package com.misiontic.sprint2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.misiontic.sprint2.models.Producto;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Producto> productos;

    public MyAdapter(Context context, ArrayList<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.image.setImageResource(productos.get(position).getImage());
        holder.title.setText(productos.get(position).getTitulo());
        holder.desc.setText(productos.get(position).getDesc());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, productos.get(position).getTitulo(), Toast.LENGTH_SHORT).show();
                Intent goToMap = new Intent(context,MapActivity.class);

                goToMap.putExtra("ltd",productos.get(position).getLat());
                goToMap.putExtra("lng",productos.get(position).getLng());

                context.startActivity(goToMap);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView title,desc;
        public LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.image = itemView.findViewById(R.id.imageProducto);
            this.title = itemView.findViewById(R.id.tituloProducto);
            this.desc  = itemView.findViewById(R.id.descProducto);
            this.layout = itemView.findViewById(R.id.productLayout);


        }
    }


}
