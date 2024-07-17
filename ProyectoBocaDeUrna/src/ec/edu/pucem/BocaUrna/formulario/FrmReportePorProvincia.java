package ec.edu.pucem.BocaUrna.formulario;

import ec.edu.pucem.BocaUrna.dominio.Prefecto;
import ec.edu.pucem.BocaUrna.dominio.SistemaBocaDeUrna;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FrmReportePorProvincia extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextArea textAreaResultados;
    private JComboBox<String> comboProvincia;
    private SistemaBocaDeUrna sistema;

    public FrmReportePorProvincia(SistemaBocaDeUrna sistema) {
        this.sistema = sistema;
        setLayout(new BorderLayout());

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new FlowLayout());

        comboProvincia = new JComboBox<>(new String[] { "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo",
                "Cotopaxi", "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos",
                "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena",
                "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora Chinchipe" });
        comboProvincia.addActionListener(e -> mostrarResultados());

        panelNorte.add(new JLabel("Seleccione Provincia:"));
        panelNorte.add(comboProvincia);

        add(panelNorte, BorderLayout.NORTH);

        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);

        add(new JScrollPane(textAreaResultados), BorderLayout.CENTER);
    }

    public void mostrarResultados() {
        StringBuilder resultados = new StringBuilder();
        String provincia = (String) comboProvincia.getSelectedItem();
        List<Prefecto> prefectos = sistema.getPrefectosPorProvincia().get(provincia);

        if (prefectos != null) {
            for (Prefecto prefecto : prefectos) {
                resultados.append(String.format("Prefecto: %s, Partido: %s, Votos: %d%n",
                        prefecto.getNombre(), prefecto.getPartido(), prefecto.getVotos()));
            }
        } else {
            resultados.append("No hay datos disponibles para esta provincia.");
        }

        textAreaResultados.setText(resultados.toString());
    }
}
