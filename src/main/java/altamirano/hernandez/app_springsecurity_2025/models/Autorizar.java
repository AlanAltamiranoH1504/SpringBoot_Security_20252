package altamirano.hernandez.app_springsecurity_2025.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "autorizar")
public class Autorizar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 100, message = "El maximo de caracteres para el nombre es 100")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    //Constructores
    public Autorizar(){

    }
    public Autorizar(int id){
        this.id = id;
    }
    public Autorizar(String nombre, Usuario usuario){
        this.nombre = nombre;
        this.usuario = usuario;
    }
    public Autorizar(int id, String nombre, Usuario usuario){
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
    }

    //Metodos GET y SET
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public @NotBlank(message = "El nombre no puede estar vacio") @Size(max = 100, message = "El maximo de caracteres para el nombre es 100") String getNombre() {
        return nombre;
    }
    public void setNombre(@NotBlank(message = "El nombre no puede estar vacio") @Size(max = 100, message = "El maximo de caracteres para el nombre es 100") String nombre) {
        this.nombre = nombre;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //toString
    @Override
    public String toString() {
        return "Autorizar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", usuario=" + usuario +
                '}';
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autorizar autorizar = (Autorizar) o;
        return id == autorizar.id && Objects.equals(nombre, autorizar.nombre) && Objects.equals(usuario, autorizar.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, usuario);
    }
}
