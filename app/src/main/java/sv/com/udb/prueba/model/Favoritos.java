package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.Objects;

@DatabaseTable(tableName = "favoritos_automovil")
public class Favoritos {

    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField(columnName = "fk_favoritos_automovil_usuario_idx",
            foreign = true,foreignAutoRefresh = true)
    private Usuario usuario;

    @DatabaseField(columnName = "fk_favoritos_automovil_automovil_idx",
            foreign = true,foreignAutoRefresh = true)
    private Automovil automovil;

    @DatabaseField(columnName = "fecha_agregado")
    private Date date;

    public Favoritos() {
    }

    public Favoritos(Usuario usuario, Automovil automovil, Date date) {
        this.usuario = usuario;
        this.automovil = automovil;
        this.date = date;
    }

    public Favoritos(Integer id, Usuario usuario, Automovil automovil, Date date) {
        this.id = id;
        this.usuario = usuario;
        this.automovil = automovil;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favoritos favoritos = (Favoritos) o;
        return Objects.equals(id, favoritos.id) && Objects.equals(usuario, favoritos.usuario) && Objects.equals(automovil, favoritos.automovil) && Objects.equals(date, favoritos.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, automovil, date);
    }


}
