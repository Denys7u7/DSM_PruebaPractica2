package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.Objects;

@DatabaseTable(tableName = "Cita")
public class Cita {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private Date fecha;

    @DatabaseField
    private String cliente;

    @DatabaseField(columnName = "horario_id", foreign = true, foreignAutoRefresh = true)
    private Horario horario;

    @DatabaseField(columnName = "tratamiento_id", foreign = true, foreignAutoRefresh = true)
    private Tratamiento tratamiento;

    public Cita() {
    }

    public Cita(Integer id, Date fecha, String cliente, Horario horario, Tratamiento tratamiento) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.horario = horario;
        this.tratamiento = tratamiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return Objects.equals(id, cita.id) && Objects.equals(fecha, cita.fecha) && Objects.equals(cliente, cita.cliente) && Objects.equals(horario, cita.horario) && Objects.equals(tratamiento, cita.tratamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, cliente, horario, tratamiento);
    }
}
