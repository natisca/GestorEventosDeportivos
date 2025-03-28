package co.edu.elpoli.ces3.getoreventosdeportivos.dao;


import co.edu.elpoli.ces3.getoreventosdeportivos.model.Equipo;

import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {
    private static final List<Equipo> equipos = new ArrayList<>();
    private static int idCounter = 1;

    public EquipoDAO() {}

    // Método para agregar un equipo
    public boolean agregarEquipo(Equipo equipo) {
        // Validar que no exista otro equipo con el mismo nombre y deporte
        for (Equipo e : equipos) {
            if (e.getNombre().equalsIgnoreCase(equipo.getNombre()) &&
                    e.getDeporte().equalsIgnoreCase(equipo.getDeporte())) {
                return false; // Equipo duplicado
            }
        }
        equipo.setId(idCounter++);
        equipos.add(equipo);
        return true;
    }

    // Método para obtener todos los equipos
    public List<Equipo> obtenerEquipos() {
        return new ArrayList<>(equipos); // Devolver una copia para evitar modificaciones externas
    }

    // Buscar un equipo por ID
    public Equipo buscarPorId(int id) {
        return equipos.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    // Agregar un jugador a un equipo
    public boolean agregarJugadorAEquipo(int equipoId, int jugadorId) {
        for (Equipo equipo : equipos) {
            if (equipo.getId() == equipoId) {
                equipo.getJugadores().add(jugadorId);
                return true;
            }
        }
        return false; // Equipo no encontrado
    }
}
