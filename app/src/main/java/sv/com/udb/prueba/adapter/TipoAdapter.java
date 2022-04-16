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
import sv.com.udb.prueba.model.TipoAutomovil;

public class TipoAdapter extends AbstractAdapter<TipoAutomovil, TipoAdapter.TipoViewHolder> {

    public TipoAdapter(List<TipoAutomovil> payload, OnItemClickListener<TipoAutomovil> onItemClickListener) {
        super(payload, onItemClickListener);
    }

    public TipoAdapter(List<TipoAutomovil> payload) {
        super(payload);
    }

    @NonNull
    @Override
    public TipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_marca,parent,false);
        return new TipoViewHolder(view);
    }

    public class TipoViewHolder extends AbstractViewHolder<TipoAutomovil> {
        private final TextView textView;

        public TipoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.marca_text);
        }

        @Override
        public void bind(TipoAutomovil payload, int postion, OnItemClickListener<TipoAutomovil> onItemClickListener,
                         OnLongItemClickListener<TipoAutomovil> onLongItemClickListener) {
            textView.setText(payload.getDescripcion());
            if(null != onItemClickListener){
                itemView.setOnClickListener((View v) -> onItemClickListener.onClick(payload));
            }
            if(null != onLongItemClickListener){
                itemView.setOnLongClickListener((View v) -> {
                    onLongItemClickListener.onLongClick(payload);
                    return true;
                });
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
