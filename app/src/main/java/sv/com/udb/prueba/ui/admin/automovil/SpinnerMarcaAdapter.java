package sv.com.udb.prueba.ui.admin.automovil;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import sv.com.udb.prueba.model.Color;
import sv.com.udb.prueba.model.Marca;
import sv.com.udb.prueba.model.TipoAutomovil;

public class SpinnerMarcaAdapter extends ArrayAdapter<Marca> {
    private Marca[] values;

    public SpinnerMarcaAdapter(@NonNull Context context, int resource, Marca[] values) {
        super(context, resource,values);
        this.values = values;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(values[position].getNombre());
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(values[position].getNombre());
        return label;
    }

    @Override
    public Marca getItem(int position) {
        return values[position];
    }
}
