package com.example.ximena.lextec;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Ximena on 3/6/2018.
 */

public class CustomList extends BaseAdapter {
    private final Context context;
    private final ArrayList<Visita> web;
    private DatabaseReference mDatabase;

    public CustomList(Context context, ArrayList<Visita> web) {
        this.context = context;
        this.web = web;
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private class VisitaViewHolder {

        TextView nombreExperimento;
        TextView nombreUsuario;
        TextView dia;
        TextView telefono;
        TextView email;

        Button delete;
        Button modify;
    }

    @Override
    public int getCount() {
        return web.size();
    }

    @Override
    public Object getItem(int position) {

        return web.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View view, ViewGroup parent) {
        System.out.println("Entro");
        VisitaViewHolder visitaHolder;
        final int pos=position;
        View rowView=view;
        if (rowView == null) {
            System.out.println("Entro");
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_single, parent, false);
        }else{
            rowView=view;

        }

        visitaHolder = new VisitaViewHolder();

        visitaHolder.nombreExperimento = rowView.findViewById(R.id.experiment);
        visitaHolder.nombreUsuario = rowView.findViewById(R.id.name);
        visitaHolder.telefono = rowView.findViewById(R.id.telefono);
        visitaHolder.email = rowView.findViewById(R.id.email);
        visitaHolder.dia = rowView.findViewById(R.id.date);
        visitaHolder.delete = rowView.findViewById(R.id.eliminar);
        visitaHolder.modify = rowView.findViewById(R.id.modify);

        rowView.setTag(visitaHolder);
        final Visita visita = web.get(position);

        if (visita != null) {
            visitaHolder.email.setText("correo:"+visita.getEmail());
            visitaHolder.telefono.setText("telefono:"+visita.getTelefono());
            visitaHolder.nombreUsuario.setText("Nombre:"+visita.getNombreUsuario());
            visitaHolder.nombreExperimento.setText("Experimento:"+visita.getNombreExperimento());


            String date="Fecha:"+visita.getDia();
            visitaHolder.dia.setText(date);



            visitaHolder.delete.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    mDatabase.child("users").child(visita.getIdVisita()).child("visitas").child(visita.getIdVisita()).removeValue();
                    web.remove(web.get(pos));
                    notifyDataSetChanged();
                }
            });
        }

        return rowView;
    }



}
