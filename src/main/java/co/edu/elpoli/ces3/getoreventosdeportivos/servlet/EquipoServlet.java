package co.edu.elpoli.ces3.getoreventosdeportivos.servlet;

import co.edu.elpoli.ces3.getoreventosdeportivos.dao.EquipoDAO;
import co.edu.elpoli.ces3.getoreventosdeportivos.model.Equipo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


@WebServlet("/equipos")
public class EquipoServlet extends HttpServlet {
    private final EquipoDAO equipoDAO = new EquipoDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Equipo nuevoEquipo = gson.fromJson(reader, Equipo.class);

        // 🚨 Validar si ya existe un equipo con el mismo nombre y deporte
        boolean existeEquipo = equipoDAO.obtenerEquipos().stream()
                .anyMatch(equipo -> equipo.getNombre().equalsIgnoreCase(nuevoEquipo.getNombre()) &&
                        equipo.getDeporte().equalsIgnoreCase(nuevoEquipo.getDeporte()));

        if (existeEquipo) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write("Error: Ya existe un equipo con ese nombre y deporte.");
            return;
        }

        boolean agregado = equipoDAO.agregarEquipo(nuevoEquipo);
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(agregado ? nuevoEquipo : "Error al agregar equipo"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener valores de la URL y manejar valores por defecto
        String pageParam = request.getParameter("page");
        String sizeParam = request.getParameter("size");

        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        int size = (sizeParam != null) ? Integer.parseInt(sizeParam) : 10;

        List<Equipo> equipos = equipoDAO.obtenerEquipos();
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, equipos.size());

        if (fromIndex >= equipos.size()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error: Página fuera de rango.");
            return;
        }

        List<Equipo> paginatedEquipos = equipos.subList(fromIndex, toIndex);

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(paginatedEquipos));
    }

}
