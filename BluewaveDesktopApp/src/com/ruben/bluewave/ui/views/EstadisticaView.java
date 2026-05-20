package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.ruben.bluewave.ui.chart.IncidenciaPorTipoChart;

public class EstadisticaView extends AbstractView {

    private static final long serialVersionUID = 1L;
    
    private JComboBox<String> graficaCB;
    private JPanel chartPanel;
    private CardLayout cardLayout;

    public EstadisticaView() {
        setName("Estadísticas");
        initialize();
        postInitialize();
    }

    private void initialize() {
        setLayout(new BorderLayout(0, 0));

        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(toolbarPanel, BorderLayout.NORTH);

        graficaCB = new JComboBox<>(new String[]{
            "Incidencias por Tipo"
        });
        toolbarPanel.add(graficaCB);

        JButton refrescarButton = new JButton("Refrescar");
        refrescarButton.addActionListener(e -> cargarGrafica());
        toolbarPanel.add(refrescarButton);

        cardLayout = new CardLayout();
        chartPanel = new JPanel(cardLayout);
        add(chartPanel, BorderLayout.CENTER);
    }

    private void postInitialize() {
        graficaCB.addItemListener(e -> cargarGrafica());
        cargarGrafica();
    }

    private void cargarGrafica() {
        chartPanel.removeAll();
        String seleccion = (String) graficaCB.getSelectedItem();
        
        if ("Incidencias por Tipo".equals(seleccion)) {
            chartPanel.add(new IncidenciaPorTipoChart(), "tipo");
        }
        
        cardLayout.show(chartPanel, "tipo");
        chartPanel.revalidate();
        chartPanel.repaint();
    }
}