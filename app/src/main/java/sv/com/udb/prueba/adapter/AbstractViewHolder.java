package sv.com.udb.prueba.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sv.com.udb.prueba.interfaces.OnItemClickListener;
import sv.com.udb.prueba.interfaces.OnLongItemClickListener;

abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    public AbstractViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(T payload, int postion, OnItemClickListener<T> onItemClickListener,
                              OnLongItemClickListener<T> onLongItemClickListener);
}
