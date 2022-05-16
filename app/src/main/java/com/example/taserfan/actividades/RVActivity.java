package com.example.taserfan.actividades;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taserfan.R;


public class RVActivity extends RecyclerView.Adapter<RVActivity.ViewHolder> {

    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    public RVActivity(Context context, LayoutInflater inflater) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public RVActivity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_view,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);    }

    @Override //TODO posar que pillem
    public void onBindViewHolder(@NonNull RVActivity.ViewHolder holder, int position) {
        holder.img.setImageResource();
        holder.matricula.setText();
        holder.marca.setText();
        holder.estado.setText();
        holder.color.setText();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView matricula, marca, estado, color;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            matricula = itemView.findViewById(R.id.matricula);
            marca = itemView.findViewById(R.id.marca);
            estado = itemView.findViewById(R.id.estado);
            color = itemView.findViewById(R.id.color);
        }
    }
}