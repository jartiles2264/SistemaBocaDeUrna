package ec.edu.pucem.BocaUrna.formulario;

import ec.edu.pucem.BocaUrna.dominio.Prefecto;
import ec.edu.pucem.BocaUrna.dominio.SistemaBocaDeUrna;

import javax.swing.*;
import java.awt.*;

public class FrmPrefecto extends JPanel {
    private static final long serialVersionUID = 1L;

    private JLabel lblNombre, lblPartido, lblProvincia;
    private JTextField txtNombre, txtPartido;
    private JComboBox<String> comboProvincia;
    private JButton btnGuardar;
    private SistemaBocaDeUrna sistema;

    public FrmPrefecto(SistemaBocaDeUrna sistema) {
        this.sistema = sistema;

        setLayout(new GridLayout(4, 2));

        lblNombre = new JLabel("Nombre:");
        lblPartido = new JLabel("Partido:");
        lblProvincia = new JLabel("Provincia:");
        txtNombre = new JTextField();
        txtPartido = new JTextField();
        comboProvincia = new JComboBox<>(new String[] { "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo",
                "Cotopaxi", "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Ríos",
                "Manabí", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena",
                "Santo Domingo de los Tsáchilas", "Sucumbíos", "Tungurahua", "Zamora Chinchipe" });
        btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(e -> guardarPrefecto());

        add(lblNombre);
        add(txtNombre);
        add(lblPartido);
        add(txtPartido);
        add(lblProvincia);
        add(comboProvincia);
        add(new JLabel()); // Placeholder
        add(btnGuardar);
    }

    private void guardarPrefecto() {
        String nombre = txtNombre.getText();
        String partido = txtPartido.getText();
        String provincia = (String) comboProvincia.getSelectedItem();

        if (nombre.isEmpty() || partido.isEmpty() || provincia == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Prefecto prefecto = new Prefecto(nombre, partido, provincia);
        sistema.agregarPrefecto(prefecto);

        JOptionPane.showMessageDialog(this, "Prefecto guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        txtNombre.setText("");
        txtPartido.setText("");
        comboProvincia.setSelectedIndex(0);
    }
}
