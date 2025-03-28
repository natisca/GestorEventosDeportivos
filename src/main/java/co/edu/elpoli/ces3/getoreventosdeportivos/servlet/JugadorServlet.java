package co.edu.elpoli.ces3.getoreventosdeportivos.servlet;


import co.edu.elpoli.ces3.getoreventosdeportivos.dao.EquipoDAO;
import co.edu.elpoli.ces3.getoreventosdeportivos.dao.JugadorDAO;
import co.edu.elpoli.ces3.getoreventosdeportivos.model.Jugador;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/jugadores")
public class JugadorServlet extends HttpServlet {
    private final JugadorDAO jugadorDAO = new JugadorDAO();
    private final EquipoDAO equipoDAO = new EquipoDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Jugador nuevoJugador = gson.fromJson(reader, Jugador.class);

        // 🚨 Validar si ya existe un jugador con el mismo número en el equipo
        boolean existeNumero = jugadorDAO.obtenerJugadores().stream()
                .anyMatch(jugador -> jugador.getEquipoId() == nuevoJugador.getEquipoId() &&
                        jugador.getNumero() == nuevoJugador.getNumero());

        if (existeNumero) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write("Error: Ya existe un jugador con ese número en el equipo.");
            return;
        }

        boolean agregado = jugadorDAO.agregarJugador(nuevoJugador);
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(agregado ? nuevoJugador : "Error al agregar jugador"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("transferir".equals(action)) {
            transferirJugador(request, response);
            return;
        }

        List<Jugador> jugadores = jugadorDAO.obtenerJugadores();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(jugadores));
    }

    // ✅ Endpoint para transferir jugadores entre equipos
    private void transferirJugador(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
        int equipoDestino = Integer.parseInt(request.getParameter("equipoDestino"));

        boolean transferido = jugadorDAO.transferirJugador(jugadorId, equipoDestino);
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(transferido ? "Transferencia realizada" : "Error en la transferencia"));
    }
}
