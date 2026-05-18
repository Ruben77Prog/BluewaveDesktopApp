package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.EmpleadoDTO; 

public class EmpleadoCBRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof EmpleadoDTO) {
            EmpleadoDTO empleado = (EmpleadoDTO) value;
            if (empleado.getId() != null) {
                setText(empleado.getNombre() + " " + (empleado.getApellido1() != null ? empleado.getApellido1() : ""));
            } else {
                setText("Seleccionar");
            }
        } else {
            setText("Seleccionar");
        }
        return this;
    }
}