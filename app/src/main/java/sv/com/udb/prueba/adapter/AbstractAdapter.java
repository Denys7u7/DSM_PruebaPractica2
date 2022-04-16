package sv.com.udb.prueba.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sv.com.udb.prueba.interfaces.OnItemClickListener;
import sv.com.udb.prueba.interfaces.OnLongItemClickListener;

abstract class AbstractAdapter<T,K extends AbstractViewHolder> extends RecyclerView.Adapter<K> {

    private List<T> payload;
    private final OnItemClickListener<T> onItemClickListener;
    private final OnLongItemClickListener<T> onLongItemClickListener;

    public AbstractAdapter(List<T> payload){
        this(payload,null,null);
    }

    public AbstractAdapter(List<T> payload, OnItemClickListener<T> onItemClickListener){
        this(payload,onItemClickListener,null);
    }

    public AbstractAdapter(List<T> payload, OnItemClickListener<T> onItemClickListener,
                           OnLongItemClickListener<T> onLongItemClickListener){
        this.payload = payload;
        this.onItemClickListener = onItemClickListener;
        this.onLongItemClickListener = onLongItemClickListener;
    }

    public void update(List<T> payload){
        this.payload = payload;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull K holder, int position) {
        holder.bind(payload.get(position),position,onItemClickListener,onLongItemClickListener);
    }

    @Override
    public int getItemCount() {
        return payload.size();
    }

}
