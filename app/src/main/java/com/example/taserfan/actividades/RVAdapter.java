package com.example.taserfan.actividades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taserfan.R;

public class RVAdapter  extends RecyclerView.Adapter<RVAdapter  .ViewHolder> {

    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;

    public RVAdapter(Context context, LayoutInflater inflater) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public RVAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_view,parent,false);
        view.setOnClickListener(onClickListener);
        return new RVAdapter.ViewHolder(view);    }

    @Override //TODO posar que pillem
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(R.drawable.ic_baseline_pedal_bike_24);
        holder.matricula.setText("matriiicula");
        holder.marca.setText("marcaaaa");
        holder.estado.setText("estaadooo");
        holder.color.setText("rojo");
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
