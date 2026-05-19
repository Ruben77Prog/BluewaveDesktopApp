package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.ui.views.EmpleadoAltaView;

public class EmpleadoSetEditableController extends AbstractController {

    private EmpleadoDTO empleadoDTO;

    public EmpleadoSetEditableController(EmpleadoAltaView view) {
        super(view);
        this.empleadoDTO = null;
    }

    public EmpleadoSetEditableController(EmpleadoAltaView view, EmpleadoDTO empleadoDTO) {
        super(view);
        this.empleadoDTO = empleadoDTO;
    }

    @Override
    public void doAction() {
        EmpleadoAltaView view = (EmpleadoAltaView) getView();
        if (empleadoDTO != null) {
            view.cargarEmpleado(empleadoDTO);
        }
        view.setEditable(true);
        view.setAgreeController(new EmpleadoUpdateController(view, empleadoDTO));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}