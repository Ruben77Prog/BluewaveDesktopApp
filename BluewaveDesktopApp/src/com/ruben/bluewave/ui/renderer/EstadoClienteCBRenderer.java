package com.ruben.bluewave.ui.renderer;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.EstadoCliente;

public class EstadoClienteCBRenderer extends DefaultListCellRenderer {

    public EstadoClienteCBRenderer() {
    }

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value != null && value instanceof EstadoCliente) {
            EstadoCliente estado = (EstadoCliente) value;

            if (estado.getId() == null) {
                
               setText(estado.getNombre());
            } else {
                setText(estado.getNombre());
            }

        } else {
            setText("Seleccionar");
        }

        return this;
    }
}