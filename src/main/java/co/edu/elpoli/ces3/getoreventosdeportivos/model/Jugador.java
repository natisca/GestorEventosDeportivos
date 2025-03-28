package co.edu.elpoli.ces3.getoreventosdeportivos.model;

import java.util.Date;

public class Jugador {
    private Integer id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String posicion;
    private int numero;
    private int equipoId;
    private boolean estadoActivo;

    public Jugador() {}

    public Jugador(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String posicion, int numero, int equipoId, boolean estadoActivo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.numero = numero;
        this.equipoId = equipoId;
        this.estadoActivo = estadoActivo;
    }

    public Jugador(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String posicion, int numero, int equipoId, boolean estadoActivo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.numero = numero;
        this.equipoId = equipoId;
        this.estadoActivo = estadoActivo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public int getEquipoId() { return equipoId; }
    public void setEquipoId(int equipoId) { this.equipoId = equipoId; }

    public boolean isEstadoActivo() { return estadoActivo; }
    public void setEstadoActivo(boolean estadoActivo) { this.estadoActivo = estadoActivo; }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido + " (" + this.posicion + ")";
    }
}
