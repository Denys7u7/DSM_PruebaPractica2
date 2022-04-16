package sv.com.udb.prueba.model;

import androidx.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "colores")
public class Color {

    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField
    private String descripcion;

    public Color(){}

    public Color(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    public Color(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Objects.equals(id, color.id) && Objects.equals(descripcion, color.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }


}
