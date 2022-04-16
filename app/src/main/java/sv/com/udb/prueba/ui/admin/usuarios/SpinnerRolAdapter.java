package sv.com.udb.prueba.ui.admin.usuarios;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import sv.com.udb.prueba.model.Role;
import sv.com.udb.prueba.model.TipoAutomovil;

public class SpinnerRolAdapter extends ArrayAdapter<Role> {
    private Role[] values;

    public SpinnerRolAdapter(@androidx.annotation.NonNull Context context, int resource, Role[] values) {
        super(context, resource,values);
        this.values = values;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(values[position].getRole());
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(values[position].getRole());
        return label;
    }

    @Override
    public Role getItem(int position) {
        return values[position];
    }
}
