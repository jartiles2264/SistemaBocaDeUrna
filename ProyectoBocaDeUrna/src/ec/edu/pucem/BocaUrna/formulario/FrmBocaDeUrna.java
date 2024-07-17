package ec.edu.pucem.BocaUrna.formulario;

import ec.edu.pucem.BocaUrna.dominio.Prefecto;
import ec.edu.pucem.BocaUrna.dominio.SistemaBocaDeUrna;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FrmBocaDeUrna extends JPanel {
    private static final long serialVersionUID = 1L;

    private JLabel lblPrefecto, lblProvincia;
    private JComboBox<String> comboPrefecto, comboProvincia;
    private JButton btnVotar;
    private SistemaBocaDeUrna sistema;

    public FrmBocaDeUrna(SistemaBocaDeUrna sistema) {
        this.sistema = sistema;

        setLayout(new GridLayout(3, 2));

        lblPrefecto = new JLabel("Prefecto:");
        lblProvincia = new JLabel("Provincia:");
        comboPrefecto = new JComboBox<>();
        comboProvincia = new JComboBox<>(new String[] { "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo",
                "Cotopaxi", "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos",
                "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena",
                "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora Chinchipe" });
        btnVotar = new JButton("Votar");

        btnVotar.addActionListener(e -> registrarVoto());
        comboProvincia.addActionListener(e -> actualizarPrefectos());

        add(lblPrefecto);
        add(comboPrefecto);
        add(lblProvincia);
        add(comboProvincia);
        add(new JLabel()); // Placeholder
        add(btnVotar);

        actualizarPrefectos();
    }

    private void actualizarPrefectos() {
        comboPrefecto.removeAllItems();
        String provincia = (String) comboProvincia.getSelectedItem();
        List<Prefecto> prefectos = sistema.getPrefectosPorProvincia().get(provincia);

        if (prefectos != null) {
            for (Prefecto prefecto : prefectos) {
                comboPrefecto.addItem(prefecto.getNombre());
            }
        }
    }

    private void registrarVoto() {
        String nombrePrefecto = (String) comboPrefecto.getSelectedItem();
        String provincia = (String) comboProvincia.getSelectedItem();

        if (nombrePrefecto != null && provincia != null) {
            sistema.registrarVoto(provincia, nombrePrefecto);
            JOptionPane.showMessageDialog(this, "Voto registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un prefecto y una provincia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
