package co.edu.elpoli.ces3.getoreventosdeportivos.model;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private Integer id;
    private String nombre;
    private String deporte;
    private String ciudad;
    private String fechaFundacion;
    private String logo;
    private List<Integer> jugadores;

    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    public Equipo(String nombre, String deporte, String ciudad, String fechaFundacion, String logo) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.ciudad = ciudad;
        this.fechaFundacion = fechaFundacion;
        this.logo = logo;
        this.jugadores = new ArrayList<>();
    }

    public Equipo(Integer id, String nombre, String deporte, String ciudad, String fechaFundacion, String logo, List<Integer> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.deporte = deporte;
        this.ciudad = ciudad;
        this.fechaFundacion = fechaFundacion;
        this.logo = logo;
        this.jugadores = jugadores;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDeporte() { return deporte; }
    public void setDeporte(String deporte) { this.deporte = deporte; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getFechaFundacion() { return fechaFundacion; }
    public void setFechaFundacion(String fechaFundacion) { this.fechaFundacion = fechaFundacion; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public List<Integer> getJugadores() { return jugadores; }
    public void setJugadores(List<Integer> jugadores) { this.jugadores = jugadores; }

    @Override
    public String toString() {
        return this.nombre + " (" + this.deporte + ")";
    }
}
