package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;
import com.ruben.bluewave.ui.views.EmpleadoSearchView;

public class EmpleadoDeleteController extends AbstractController {

    private EmpleadoService empleadoService;
    private EmpleadoSearchView searchView;
    private EmpleadoDTO empleado;

    public EmpleadoDeleteController(EmpleadoSearchView searchView, EmpleadoDTO empleado) {
        super(searchView);
        this.searchView = searchView;
        this.empleado = empleado;
        this.empleadoService = new EmpleadoServiceImpl();
    }

    @Override
    public void doAction() {
        int confirm = JOptionPane.showConfirmDialog(searchView,
                "¿Está seguro de eliminar al empleado " + empleado.getNombre() + " " + empleado.getApellido1() + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean eliminado = empleadoService.delete(empleado.getId());
                if (eliminado) {
                    JOptionPane.showMessageDialog(searchView, "Empleado eliminado correctamente");
                    searchView.buscar();
                } else {
                    JOptionPane.showMessageDialog(searchView, "Error al eliminar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(searchView, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}