package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.service.IncidenciaService;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;
import com.ruben.bluewave.ui.views.NuevaIncidenciaView;

public class IncidenciaUpdateController extends AbstractController {

    private NuevaIncidenciaView view;
    private IncidenciaService incidenciaService;
    private IncidenciaDTO incidenciaDTO;

    public IncidenciaUpdateController(NuevaIncidenciaView view, IncidenciaDTO incidenciaDTO) {
        super(view);
        this.view = view;
        this.incidenciaDTO = incidenciaDTO;
        this.incidenciaService = new IncidenciaServiceImpl();
    }

    @Override
    public void doAction() {
        Incidencia incidencia = view.getModel();

        if (incidencia == null) {
            return;
        }

        if (incidenciaDTO == null || incidenciaDTO.getId() == null) {
            JOptionPane.showMessageDialog(view,
                    "Error: incidencia sin ID",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        incidencia.setId(incidenciaDTO.getId());

        try {
            boolean updated = incidenciaService.update(incidencia);

            if (updated) {
                JOptionPane.showMessageDialog(view,
                        "Incidencia actualizada correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                view.setEditable(false);
                view.setAgreeController(new IncidenciaSetEditableController(view));
            } else {
                JOptionPane.showMessageDialog(view,
                        "Error al actualizar la incidencia",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view,
                    "Error en base de datos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}