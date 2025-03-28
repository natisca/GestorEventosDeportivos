package co.edu.elpoli.ces3.getoreventosdeportivos.dao;


import co.edu.elpoli.ces3.getoreventosdeportivos.model.Jugador;

import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {
    private static final List<Jugador> jugadores = new ArrayList<>();
    private static int idCounter = 1;

    public JugadorDAO() {}

    // Método para agregar un jugador
    public boolean agregarJugador(Jugador jugador) {
        // Verificar que no haya otro jugador con el mismo número en el mismo equipo
        for (Jugador j : jugadores) {
            if (j.getEquipoId() == jugador.getEquipoId() && j.getNumero() == jugador.getNumero()) {
                return false; // Número de camiseta ya ocupado
            }
        }
        jugador.setId(idCounter++);
        jugadores.add(jugador);
        return true;
    }

    // Método para obtener todos los jugadores
    public List<Jugador> obtenerJugadores() {
        return new ArrayList<>(jugadores);
    }

    // Buscar un jugador por ID
    public Jugador buscarPorId(int id) {
        return jugadores.stream().filter(j -> j.getId() == id).findFirst().orElse(null);
    }

    // Transferir un jugador a otro equipo
    public boolean transferirJugador(int jugadorId, int equipoDestino) {
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == jugadorId) {
                jugador.setEquipoId(equipoDestino);
                return true;
            }
        }
        return false;
    }

}
