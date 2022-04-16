package sv.com.udb.prueba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;

import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.interfaces.OnItemClickListener;
import sv.com.udb.prueba.interfaces.OnLongItemClickListener;
import sv.com.udb.prueba.model.Automovil;

public class AutoFavAdapter extends AbstractAdapter<Automovil, AutoFavAdapter.AutoViewHolder> {

    public AutoFavAdapter(List<Automovil> payload, OnItemClickListener<Automovil> onItemClickListener) {
        super(payload, onItemClickListener);
    }

    public AutoFavAdapter(List<Automovil> payload) {
        super(payload);
    }

    @NonNull
    @Override
    public AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_auto_fav,parent,false);
        return new AutoViewHolder(view);
    }

    public class AutoViewHolder extends AbstractViewHolder<Automovil> {

        public static final String EDIT = "Editar";
        public static final String DELETE = "Eliminar";
        private TextView txtModelo;
        private TextView txtAnio;
        private TextView txtAsientos;
        private TextView txtPrecio;
        private AppCompatImageButton btnFav;


        public AutoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtAnio = itemView.findViewById(R.id.txtAnio);
            txtAsientos = itemView.findViewById(R.id.txtAsientos);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            btnFav = itemView.findViewById(R.id.btnFav);
        }

        @Override
        public void bind(Automovil payload, int postion, OnItemClickListener<Automovil> onItemClickListener,
                         OnLongItemClickListener<Automovil> onLongItemClickListener) {
            txtModelo.setText(payload.getModelo());
            txtAnio.setText("Año: " + payload.getAnio());
            txtAsientos.setText("# Asientos: " + payload.getNumeroAsientos());
            txtPrecio.setText("$ " + payload.getPrecio());
            if(null != onItemClickListener){
                btnFav.setOnClickListener((View v) -> onItemClickListener.onClick(payload));
            }
        }
    }

}
