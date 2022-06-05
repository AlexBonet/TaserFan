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
import com.example.taserfan.actividades.vehiculos.Vehiculo;

import java.util.List;

public class RVAdapter  extends RecyclerView.Adapter<RVAdapter  .ViewHolder> {

    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;
    private List<Vehiculo> list;

    public  RVAdapter(Context context, List<Vehiculo> list){
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_view,parent,false);
        view.setOnClickListener(onClickListener);
        return new RVAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
        Vehiculo v = list.get(position);

        switch (v.getTipoVehiculo()){
            case MOTOS:
                holder.img.setImageResource(R.drawable.ic_baseline_bike_scooter_24);
                break;
            case COCHE:
                holder.img.setImageResource(R.drawable.ic_baseline_directions_car_24);
                break;
            case PATIN:
                holder.img.setImageResource(R.drawable.ic_baseline_electric_scooter_24);
                break;
            case BICIS:
                holder.img.setImageResource(R.drawable.ic_baseline_pedal_bike_24);
                break;
        }

        holder.matricula.setText(v.getMatricula());
        holder.marca.setText(v.getMarca());
        holder.estado.setText(v.getEstado());
        holder.color.setText(v.getColor());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
