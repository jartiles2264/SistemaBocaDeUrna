package ec.edu.pucem.BocaUrna.formulario;

import ec.edu.pucem.BocaUrna.dominio.Prefecto;
import ec.edu.pucem.BocaUrna.dominio.SistemaBocaDeUrna;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FrmReporteGeneral extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextArea textAreaResultados;
    private SistemaBocaDeUrna sistema;

    public FrmReporteGeneral(SistemaBocaDeUrna sistema) {
        this.sistema = sistema;
        setLayout(new BorderLayout());

        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);

        add(new JScrollPane(textAreaResultados), BorderLayout.CENTER);
        mostrarResultados();
    }

    public void mostrarResultados() {
        StringBuilder resultados = new StringBuilder();
        String[] provincias = { "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro",
                "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos", "Manabí",
                "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena",
                "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora Chinchipe" };

        for (String provincia : provincias) {
            List<Prefecto> prefectos = sistema.getPrefectosPorProvincia().get(provincia);
            resultados.append("Provincia: ").append(provincia).append("\n");

            if (prefectos != null && !prefectos.isEmpty()) {
                for (Prefecto prefecto : prefectos) {
                    resultados.append(String.format("  Prefecto: %s, Partido: %s, Votos: %d%n",
                            prefecto.getNombre(), prefecto.getPartido(), prefecto.getVotos()));
                }
            } else {
                resultados.append("  No hay datos disponibles para esta provincia.\n");
            }
            resultados.append("\n"); // Espacio entre provincias
        }

        textAreaResultados.setText(resultados.toString());
    }
}
