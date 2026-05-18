package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.service.IncidenciaService;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;
import com.ruben.bluewave.ui.views.NuevaIncidenciaView;

public class IncidenciaCreateController extends AbstractController {

    private IncidenciaService incidenciaService = new IncidenciaServiceImpl();

    public IncidenciaCreateController(NuevaIncidenciaView view) {
        super(view);
    }

    @Override
    public void doAction() {
        NuevaIncidenciaView view = (NuevaIncidenciaView) getView();
        Incidencia incidencia = view.getModel();
        if (incidencia == null) return;

        try {
            Long id = incidenciaService.create(incidencia);
            JOptionPane.showMessageDialog(view, "Incidencia creada correctamente con ID: " + id);
            view.limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al crear la incidencia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}