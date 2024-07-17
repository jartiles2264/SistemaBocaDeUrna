package ec.edu.pucem.BocaUrna.formulario;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ec.edu.pucem.BocaUrna.dominio.SistemaBocaDeUrna;

public class FrmMenuPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;

    private JMenuBar menuBar;
    private JMenu menuArchivo, menuPrefectos, menuProceso, menuReportes;
    private JMenuItem menuItemSalir, menuItemPrefectos, menuItemBocaDeUrna, menuItemReporteGeneral, menuItemReportePorProvincia;
    private SistemaBocaDeUrna sistema;
    private JPanel currentPanel;

    public FrmMenuPrincipal() {
        setTitle("Sistema de Boca de Urna");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        sistema = new SistemaBocaDeUrna();

        menuBar = new JMenuBar();

        menuArchivo = new JMenu("Archivo");
        menuPrefectos = new JMenu("Prefectos");
        menuProceso = new JMenu("Proceso");
        menuReportes = new JMenu("Reportes");

        menuItemSalir = new JMenuItem("Salir");
        menuItemPrefectos = new JMenuItem("Prefectos");
        menuItemBocaDeUrna = new JMenuItem("Boca de Urna");
        menuItemReporteGeneral = new JMenuItem("Resultados Generales");
        menuItemReportePorProvincia = new JMenuItem("Resultados por Provincia");

        menuArchivo.add(menuItemSalir);
        menuPrefectos.add(menuItemPrefectos);
        menuProceso.add(menuItemBocaDeUrna);
        menuReportes.add(menuItemReporteGeneral);
        menuReportes.add(menuItemReportePorProvincia);

        menuBar.add(menuArchivo);
        menuBar.add(menuPrefectos);
        menuBar.add(menuProceso);
        menuBar.add(menuReportes);

        setJMenuBar(menuBar);

        menuItemSalir.addActionListener(e -> System.exit(0));
        menuItemPrefectos.addActionListener(e -> mostrarPanel(new FrmPrefecto(sistema)));
        menuItemBocaDeUrna.addActionListener(e -> mostrarPanel(new FrmBocaDeUrna(sistema)));
        menuItemReporteGeneral.addActionListener(e -> {
            FrmReporteGeneral frmReporteGeneral = new FrmReporteGeneral(sistema);
            frmReporteGeneral.mostrarResultados();
            mostrarPanel(frmReporteGeneral);
        });
        menuItemReportePorProvincia.addActionListener(e -> {
            FrmReportePorProvincia frmReportePorProvincia = new FrmReportePorProvincia(sistema);
            frmReportePorProvincia.mostrarResultados();
            mostrarPanel(frmReportePorProvincia);
        });

        // Mostrar el formulario de Prefectos por defecto
        mostrarPanel(new FrmPrefecto(sistema));
    }

    private void mostrarPanel(JPanel panel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = panel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmMenuPrincipal frame = new FrmMenuPrincipal();
            frame.setVisible(true);
        });
    }
}