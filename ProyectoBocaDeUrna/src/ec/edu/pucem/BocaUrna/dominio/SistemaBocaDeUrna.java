package ec.edu.pucem.BocaUrna.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaBocaDeUrna implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, List<Prefecto>> prefectosPorProvincia;

    public SistemaBocaDeUrna() {
        prefectosPorProvincia = new HashMap<>();
        inicializarDatos();
    }

    private void inicializarDatos() {
        String[] provincias = { "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro",
                                "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos", "Manabí",
                                "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena",
                                "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora Chinchipe" };

        String[][] nombresPartidos = {
                {"Carlos Pérez", "Movimiento Alianza PAIS"}, {"María García", "Partido Social Cristiano"},
                {"Luis Montalvo", "Movimiento CREO"}, {"Ana Ortiz", "Izquierda Democrática"},
                {"Juan Torres", "Movimiento SUMA"}, {"Patricia Herrera", "Partido Sociedad Patriótica"},
                {"Diego Álvarez", "Partido Avanza"}, {"Rosa Pérez", "Movimiento Centro Democrático"},
                {"Miguel Romero", "Partido Renovador Institucional Acción Nacional"},
                {"Carmen Sánchez", "Movimiento Democracia Sí"}, {"José Andrade", "Movimiento Fuerza Compromiso Social"},
                {"Lucía Rodríguez", "Movimiento Ecuatoriano Unido"}, {"Pedro Mendoza", "Partido Unidad Popular"},
                {"Verónica Flores", "Movimiento Juntos Podemos"}, {"Fernando Vela", "Partido Unión Ecuatoriana"},
                {"Elena Paredes", "Partido Podemos"}, {"Manuel Ramírez", "Partido Ecuatoriano Verde"},
                {"Gloria Benítez", "Movimiento Construye"}, {"Javier Correa", "Partido Sociedad Unida Más Acción"},
                {"Laura Castro", "Partido Democracia Sí"}, {"Ricardo Villacís", "Partido Cambio"},
                {"Susana Vega", "Movimiento Revolución Ciudadana"}, {"Andrés Salazar", "Partido Patriótico Nacional"},
                {"Mónica Jiménez", "Movimiento Político Minga"}
        };

        for (String provincia : provincias) {
            for (int i = 0; i < 2; i++) {
                int randomIndex = (int) (Math.random() * nombresPartidos.length);
                String nombre = nombresPartidos[randomIndex][0];
                String partido = nombresPartidos[randomIndex][1];
                agregarPrefecto(new Prefecto(nombre, partido, provincia));
            }
        }
    }

    public void agregarPrefecto(Prefecto prefecto) {
        String provincia = prefecto.getProvincia();
        prefectosPorProvincia.computeIfAbsent(provincia, k -> new ArrayList<>()).add(prefecto);
    }

    public void registrarVoto(String provincia, String nombrePrefecto) {
        List<Prefecto> prefectos = prefectosPorProvincia.get(provincia);
        if (prefectos != null) {
            for (Prefecto prefecto : prefectos) {
                if (prefecto.getNombre().equals(nombrePrefecto)) {
                    prefecto.incrementarVotos();
                    break;
                }
            }
        }
    }

    public Map<String, List<Prefecto>> getPrefectosPorProvincia() {
        return prefectosPorProvincia;
    }

    public List<Prefecto> getPrefectos() {
        List<Prefecto> todosPrefectos = new ArrayList<>();
        for (List<Prefecto> prefectos : prefectosPorProvincia.values()) {
            todosPrefectos.addAll(prefectos);
        }
        return todosPrefectos;
    }
}
