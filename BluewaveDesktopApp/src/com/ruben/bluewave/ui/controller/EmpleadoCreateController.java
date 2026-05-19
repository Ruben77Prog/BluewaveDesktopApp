package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.Direccion;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.service.DireccionService;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.impl.DireccionServiceImpl;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;
import com.ruben.bluewave.ui.views.EmpleadoAltaView;

public class EmpleadoCreateController extends AbstractController {

    private EmpleadoService empleadoService = new EmpleadoServiceImpl();
    private DireccionService direccionService = new DireccionServiceImpl();

    public EmpleadoCreateController(EmpleadoAltaView view) {
        super(view);
    }

    @Override
    public void doAction() {
        EmpleadoAltaView view = (EmpleadoAltaView) getView();
        
        try {
            Direccion direccion = view.getDireccionFromForm();
            if (direccion == null) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar país, provincia y ciudad.",
                        "Dirección incompleta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Direccion direccionGuardada = direccionService.create(direccion);
            if (direccionGuardada == null || direccionGuardada.getId() == null) {
                JOptionPane.showMessageDialog(view, "Error al guardar la dirección", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            EmpleadoDTO empleado = view.getModel();
            if (empleado == null) return;
            
            empleado.setDireccionId(direccionGuardada.getId());

            Long idEmpleado = empleadoService.create(empleado);
            if (idEmpleado != null) {
                JOptionPane.showMessageDialog(view, "Empleado creado correctamente con ID: " + idEmpleado);
                view.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(view, "Error al crear el empleado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}