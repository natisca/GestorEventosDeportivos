package co.edu.elpoli.ces3.getoreventosdeportivos.dao;


import co.edu.elpoli.ces3.getoreventosdeportivos.model.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    private static final List<Evento> eventos = new ArrayList<>(); // Almacenamiento en memoria
    private static int idCounter = 1; // Contador de ID para eventos

    public EventoDAO() {}

    // ✅ Método para agregar un evento en memoria
    public boolean agregarEvento(Evento evento) {
        if (evento == null || evento.getEquiposParticipantes() == null || evento.getEquiposParticipantes().size() < 2) {
            return false;
        }
        evento.setId(idCounter++);
        eventos.add(evento);
        return true;
    }

    // ✅ Método para agregar un evento en memoria
    public boolean actualizarEvento(Evento evento) {
        if (evento == null || evento.getEquiposParticipantes() == null || evento.getEquiposParticipantes().size() < 2) {
            return false;
        }
        int index = 0;
        for(int  i = 0;  i <  eventos.size(); i ++) {
            if(eventos.get(i).getId().equals(evento.getId()) ) {
                index = i;
                break;
            }
            index++;
        }
        eventos.set(index,evento);
        return true;
    }

    // ✅ Obtener todos los eventos almacenados en memoria
    public List<Evento> obtenerEventos() {
        return new ArrayList<>(eventos);
    }

    // ✅ Método para vender entradas en un evento
    public boolean venderEntradas(int eventoId, int cantidad) {
        for (Evento evento : eventos) {
            if (evento.getId() == eventoId) {
                if (evento.getEntradasVendidas() + cantidad <= evento.getCapacidad()) {
                    evento.setEntradasVendidas(evento.getEntradasVendidas() + cantidad);
                    return true; // ✅ Venta realizada
                }
                return false; // ❌ No hay suficientes entradas disponibles
            }
        }
        return false; // ❌ Evento no encontrado
    }

    // ✅ Método para actualizar el estado de un evento
    public boolean actualizarEstado(int eventoId, String nuevoEstado) {
        for (Evento evento : eventos) {
            if (evento.getId() == eventoId) {
                evento.setEstado(nuevoEstado);
                return true;
            }
        }
        return false;
    }
}
