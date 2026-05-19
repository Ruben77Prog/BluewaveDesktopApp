package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.Ciudad;
import com.ruben.bluewave.model.Direccion;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.Pais;
import com.ruben.bluewave.model.Provincia;
import com.ruben.bluewave.service.DireccionService;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.impl.DireccionServiceImpl;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;
import com.ruben.bluewave.ui.views.EmpleadoAltaView;

public class EmpleadoUpdateController extends AbstractController {

    private EmpleadoAltaView view;
    private EmpleadoService empleadoService;
    private DireccionService direccionService;
    private EmpleadoDTO empleadoDTO;

    public EmpleadoUpdateController(EmpleadoAltaView view, EmpleadoDTO empleadoDTO) {
        super(view);
        this.view = view;
        this.empleadoDTO = empleadoDTO;
        this.empleadoService = new EmpleadoServiceImpl();
        this.direccionService = new DireccionServiceImpl();
    }

    @Override
    public void doAction() {
        EmpleadoDTO empleado = view.getModel();

        if (empleado == null) {
            return;
        }

        if (empleadoDTO == null || empleadoDTO.getId() == null) {
            JOptionPane.showMessageDialog(view, "Error: empleado sin ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        empleado.setId(empleadoDTO.getId());

        try {
            if (empleadoDTO.getDireccionId() != null) {
                Direccion direccion = view.getDireccionFromForm();
                if (direccion != null) {
                    direccion.setId(empleadoDTO.getDireccionId());
                    direccionService.update(direccion);
                    empleado.setDireccionId(empleadoDTO.getDireccionId());
                } else {
                    empleado.setDireccionId(empleadoDTO.getDireccionId());
                }
            }

            boolean updated = empleadoService.update(empleado);

            if (updated) {
                JOptionPane.showMessageDialog(view, "Empleado actualizado correctamente.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                view.setEditable(false);
                view.setAgreeController(new EmpleadoSetEditableController(view));
                view.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(view, "Error al actualizar el empleado", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error en base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}