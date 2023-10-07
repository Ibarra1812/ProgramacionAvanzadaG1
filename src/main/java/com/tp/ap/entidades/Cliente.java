package com.tp.ap.entidades;
import com.tp.ap.entidades.Vehiculo;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "nombre")
    private String nombre;

    @Column(nullable = false, name = "apellido")
    private String apellido;

    @Column(nullable = false, name = "dni")
    private Integer dni;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private Long telefono;

    @OneToOne(targetEntity = Vehiculo.class)
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, Integer dni, Vehiculo vehiculo, String direccion, Long telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.vehiculo = vehiculo;
        this.direccion = direccion;
        this.telefono = telefono;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
}
