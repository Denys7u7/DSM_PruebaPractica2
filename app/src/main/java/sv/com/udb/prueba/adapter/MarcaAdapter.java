package sv.com.udb.prueba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import sv.com.udb.prueba.R;
import sv.com.udb.prueba.interfaces.OnItemClickListener;
import sv.com.udb.prueba.interfaces.OnLongItemClickListener;
import sv.com.udb.prueba.model.Marca;

public class MarcaAdapter extends AbstractAdapter<Marca, MarcaAdapter.MarcaViewHolder> {

    public MarcaAdapter(List<Marca> payload, OnItemClickListener<Marca> onItemClickListener) {
        super(payload, onItemClickListener);
    }

    public MarcaAdapter(List<Marca> payload) {
        super(payload);
    }

    @NonNull
    @Override
    public MarcaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_marca,parent,false);
        return new MarcaViewHolder(view);
    }

    public class MarcaViewHolder extends AbstractViewHolder<Marca> {

        private final TextView textView;

        public MarcaViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.marca_text);
        }

        @Override
        public void bind(Marca payload, int postion, OnItemClickListener<Marca> onItemClickListener,
                         OnLongItemClickListener<Marca> onLongItemClickListener) {
            textView.setText(payload.getNombre());
            if(null != onItemClickListener){
                itemView.setOnClickListener((View v) -> onItemClickListener.onClick(payload));
            }
            if(null != onLongItemClickListener){
                itemView.setOnLongClickListener((View v) -> {
                    onLongItemClickListener.onLongClick(payload);
                    return false;
                });
            }

        }
    }
}
