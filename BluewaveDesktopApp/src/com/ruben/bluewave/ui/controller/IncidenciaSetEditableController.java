package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.ui.views.NuevaIncidenciaView;

public class IncidenciaSetEditableController extends AbstractController {

    private IncidenciaDTO incidenciaDTO;

    public IncidenciaSetEditableController(NuevaIncidenciaView view) {
        super(view);
        this.incidenciaDTO = null;
    }

    public IncidenciaSetEditableController(NuevaIncidenciaView view, IncidenciaDTO incidenciaDTO) {
        super(view);
        this.incidenciaDTO = incidenciaDTO;
    }

    @Override
    public void doAction() {
        NuevaIncidenciaView view = (NuevaIncidenciaView) getView();
        if (incidenciaDTO != null) {
            view.cargarIncidencia(incidenciaDTO);
        }
        view.setEditable(true);
        view.setAgreeController(new IncidenciaUpdateController(view, incidenciaDTO));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}