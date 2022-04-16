package sv.com.udb.prueba.ui.admin.automovil;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import sv.com.udb.prueba.model.Color;

public class SpinnerColorAdapter extends ArrayAdapter<Color> {
    private Color[] values;

    public SpinnerColorAdapter(@NonNull Context context, int resource, Color[] values) {
        super(context, resource,values);
        this.values = values;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(values[position].getDescripcion());
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(values[position].getDescripcion());
        return label;
    }

    @Override
    public Color getItem(int position) {
        return values[position];
    }
}
