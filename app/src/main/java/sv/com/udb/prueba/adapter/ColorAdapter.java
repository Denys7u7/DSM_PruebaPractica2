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
import sv.com.udb.prueba.model.Color;

public class ColorAdapter extends AbstractAdapter<Color, ColorAdapter.ColorViewHolder> {

    public ColorAdapter(List<Color> payload, OnItemClickListener<Color> onItemClickListener) {
        super(payload, onItemClickListener);
    }

    public ColorAdapter(List<Color> payload) {
        super(payload);
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_marca,parent,false);
        return new ColorViewHolder(view);
    }

    public class ColorViewHolder extends AbstractViewHolder<Color> {
        private final TextView textView;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.marca_text);
        }

        @Override
        public void bind(Color payload, int postion, OnItemClickListener<Color> onItemClickListener,
                         OnLongItemClickListener<Color> onLongItemClickListener) {
            textView.setText(payload.getDescripcion());
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
