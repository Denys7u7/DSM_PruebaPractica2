package sv.com.udb.prueba.adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.interfaces.OnItemClickListener;
import sv.com.udb.prueba.interfaces.OnLongItemClickListener;
import sv.com.udb.prueba.model.Automovil;

public class AutoAdapter extends AbstractAdapter<Automovil, AutoAdapter.AutoViewHolder> {

    public AutoAdapter(List<Automovil> payload, OnItemClickListener<Automovil> onItemClickListener,
                       OnLongItemClickListener<Automovil> onLongItemClickListener) {
        super(payload, onItemClickListener,onLongItemClickListener);
    }

    public AutoAdapter(List<Automovil> payload) {
        super(payload);
    }

    @NonNull
    @Override
    public AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_auto,parent,false);
        return new AutoViewHolder(view);
    }

    private Automovil position;

    public Automovil getPosition() {
        return position;
    }

    public void setPosition(Automovil position) {
        this.position = position;
    }


    public class AutoViewHolder extends AbstractViewHolder<Automovil> implements  View.OnCreateContextMenuListener {

        public static final String EDIT = "Editar";
        public static final String DELETE = "Eliminar";
        private TextView txtModelo;
        private TextView txtAnio;
        private TextView txtAsientos;
        private TextView txtPrecio;



        public AutoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtAnio = itemView.findViewById(R.id.txtAnio);
            txtAsientos = itemView.findViewById(R.id.txtAsientos);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Acciones");
            menu.add(0, v.getId(), 0, EDIT);
            menu.add(0, v.getId(), 0, DELETE);
        }

        @Override
        public void bind(Automovil payload, int postion, OnItemClickListener<Automovil> onItemClickListener,
                         OnLongItemClickListener<Automovil> onLongItemClickListener) {
            txtModelo.setText(payload.getModelo());
            txtAnio.setText("Año: " + payload.getAnio());
            txtAsientos.setText("# Asientos: " + payload.getNumeroAsientos());
            txtPrecio.setText("$ " + payload.getPrecio());
            if(null != onItemClickListener){
                itemView.setOnClickListener((View v) -> onItemClickListener.onClick(payload));
            }
            itemView.setOnLongClickListener((View v) ->{
                setPosition(payload);
                if(null != onLongItemClickListener){
                    onLongItemClickListener.onLongClick(payload);
                }
                return false;
            });

        }
    }

}
