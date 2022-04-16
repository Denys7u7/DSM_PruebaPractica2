package sv.com.udb.prueba.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "usuario")
public class Usuario {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String nombres;

    @DatabaseField
    private String apellidos;

    @DatabaseField
    private String email;

    @DatabaseField
    private String user;

    @DatabaseField
    private String password;

    @DatabaseField(columnName = "fk_usuario_role_idx",foreign = true,foreignAutoRefresh = true)
    private Role role;

    public Usuario() {
    }

    public Usuario(Integer id, String nombres, String apellidos, String email, String user, String password, Role role) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.user = user;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nombres, usuario.nombres) && Objects.equals(apellidos, usuario.apellidos) && Objects.equals(email, usuario.email) && Objects.equals(user, usuario.user) && Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombres, apellidos, email, user, password);
    }
}
