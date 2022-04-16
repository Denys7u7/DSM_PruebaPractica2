package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "automovil")
public class Automovil {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String modelo;

    @DatabaseField(columnName = "numero_vin")
    private String numeroVin;

    @DatabaseField(columnName = "numero_chasis")
    private String numeroChasis;

    @DatabaseField(columnName = "numero_motor")
    private String numeroMotor;

    @DatabaseField(columnName = "numero_asientos")
    private Integer numeroAsientos;

    @DatabaseField(columnName = "anio")
    private Integer anio;

    @DatabaseField(columnName = "capacidad_asientos")
    private Integer capacidadAsientos;

    @DatabaseField
    private Float precio;

    @DatabaseField(columnName = "uri_img")
    private String uri;

    @DatabaseField
    private String descripcion;

    @DatabaseField(columnName = "fk_automovil_marcas_idx",
            foreign = true,foreignAutoRefresh = true)
    private Marca marca;

    @DatabaseField(columnName = "fk_automovil_tipo_automovil",
            foreign = true,foreignAutoRefresh = true)
    private TipoAutomovil tipoAutomovil;

    @DatabaseField(columnName = "fk_automovil_colores",
            foreign = true,foreignAutoRefresh = true)
    private Color colores;

    public Automovil(){
    }

    public Automovil(Integer id, String modelo, String numeroVin, String numeroChasis,
                     String numeroMotor, Integer numeroAsientos, Integer anio, Integer capacidadAsientos,
                     Float precio, String uri, String descripcion, Marca marca, TipoAutomovil tipoAutomovil,
                     Color colores) {
        this.id = id;
        this.modelo = modelo;
        this.numeroVin = numeroVin;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
        this.numeroAsientos = numeroAsientos;
        this.anio = anio;
        this.capacidadAsientos = capacidadAsientos;
        this.precio = precio;
        this.uri = uri;
        this.descripcion = descripcion;
        this.marca = marca;
        this.tipoAutomovil = tipoAutomovil;
        this.colores = colores;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroVin() {
        return numeroVin;
    }

    public void setNumeroVin(String numeroVin) {
        this.numeroVin = numeroVin;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public Integer getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(Integer numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getCapacidadAsientos() {
        return capacidadAsientos;
    }

    public void setCapacidadAsientos(Integer capacidadAsientos) {
        this.capacidadAsientos = capacidadAsientos;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public TipoAutomovil getTipoAutomovil() {
        return tipoAutomovil;
    }

    public void setTipoAutomovil(TipoAutomovil tipoAutomovil) {
        this.tipoAutomovil = tipoAutomovil;
    }

    public Color getColores() {
        return colores;
    }

    public void setColores(Color colores) {
        this.colores = colores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automovil automovil = (Automovil) o;
        return id.equals(automovil.id) && Objects.equals(modelo, automovil.modelo) && Objects.equals(numeroVin, automovil.numeroVin) && Objects.equals(numeroChasis, automovil.numeroChasis) && Objects.equals(numeroMotor, automovil.numeroMotor) && Objects.equals(numeroAsientos, automovil.numeroAsientos) && Objects.equals(anio, automovil.anio) && Objects.equals(capacidadAsientos, automovil.capacidadAsientos) && Objects.equals(precio, automovil.precio) && Objects.equals(uri, automovil.uri) && Objects.equals(descripcion, automovil.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelo, numeroVin, numeroChasis, numeroMotor, numeroAsientos, anio, capacidadAsientos, precio, uri, descripcion);
    }

}

