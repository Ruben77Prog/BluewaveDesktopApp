package com.ruben.bluewave.ui.renderer;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import com.ruben.bluewave.model.EstadoIncidencia;

public class EstadoIncidenciaCBRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof EstadoIncidencia) {
            EstadoIncidencia e = (EstadoIncidencia) value;
            setText(e.getId() == null ? "Seleccionar" : e.getNombre());
        } else {
            setText("Seleccionar");
        }
        return this;
    }
}