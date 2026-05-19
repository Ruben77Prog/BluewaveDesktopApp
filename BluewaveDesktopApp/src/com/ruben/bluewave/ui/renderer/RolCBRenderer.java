package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.Rol;

public class RolCBRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, 
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        if (value instanceof Rol) {
            Rol rol = (Rol) value;
            if (rol.getId() == null) {
                setText("Seleccionar");
            } else {
                setText(rol.getNombre());
            }
        } else {
            setText("Seleccionar");
        }
        return this;
    }
}