package co.edu.elpoli.ces3.getoreventosdeportivos.servlet;

import co.edu.elpoli.ces3.getoreventosdeportivos.dao.EquipoDAO;
import co.edu.elpoli.ces3.getoreventosdeportivos.dao.EventoDAO;
import co.edu.elpoli.ces3.getoreventosdeportivos.dao.JugadorDAO;
import co.edu.elpoli.ces3.getoreventosdeportivos.model.Equipo;
import co.edu.elpoli.ces3.getoreventosdeportivos.model.Evento;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/estadisticas")
public class EstadisticasServlet extends HttpServlet {
    private final EventoDAO eventoDAO = new EventoDAO();
    private final EquipoDAO equipoDAO = new EquipoDAO();
    private final JugadorDAO jugadorDAO = new JugadorDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Evento> eventos = eventoDAO.obtenerEventos();
        List<Equipo> equipos = equipoDAO.obtenerEquipos();

        // 1️⃣ Cantidad de eventos por deporte
        Map<String, Long> eventosPorDeporte = eventos.stream()
                .collect(Collectors.groupingBy(Evento::getDeporte, Collectors.counting()));

        // 2️⃣ Promedio de jugadores por equipo
        double promedioJugadores = equipos.isEmpty() ? 0 : equipos.stream()
                .mapToInt(e -> e.getJugadores().size())
                .average()
                .orElse(0);

        // 3️⃣ Equipos con más eventos programados
        Map<Integer, Long> eventosPorEquipo = eventos.stream()
                .flatMap(e -> e.getEquiposParticipantes().stream())
                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));

        List<Integer> equiposMasEventos = eventosPorEquipo.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // Orden descendente
                .map(Map.Entry::getKey)
                .limit(3) // Top 3 equipos con más eventos
                .collect(Collectors.toList());

        // 4️⃣ Porcentaje de ocupación de cada evento
        Map<String, Double> ocupacionEventos = eventos.stream()
                .collect(Collectors.toMap(
                        Evento::getNombre,
                        e -> e.getCapacidad() > 0 ? (double) e.getEntradasVendidas() / e.getCapacidad() * 100 : 0
                ));

        // Crear objeto JSON con las estadísticas
        Map<String, Object> estadisticas = new HashMap<>();
        estadisticas.put("eventosPorDeporte", eventosPorDeporte);
        estadisticas.put("promedioJugadoresPorEquipo", promedioJugadores);
        estadisticas.put("equiposConMasEventos", equiposMasEventos);
        estadisticas.put("ocupacionEventos", ocupacionEventos);

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(estadisticas));
    }
}
