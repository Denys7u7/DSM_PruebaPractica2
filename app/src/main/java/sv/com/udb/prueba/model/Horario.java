package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "horario")
public class Horario {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "hora_inicio")
    private String horaInicio;

    @DatabaseField(columnName = "hora_fin")
    private String horaFin;

    public Horario() {
    }

    public Horario(Integer id, String horaInicio, String horaFin) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horario horario = (Horario) o;
        return Objects.equals(id, horario.id) && Objects.equals(horaInicio, horario.horaInicio) && Objects.equals(horaFin, horario.horaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, horaInicio, horaFin);
    }
}
