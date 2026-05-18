package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.TipoIncidencia;

public class TipoIncidenciaRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, 
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        if (value instanceof TipoIncidencia) {
            TipoIncidencia tipo = (TipoIncidencia) value;
            if (tipo.getId() == null) {
                setText("Seleccionar");
            } else {
                setText(tipo.getNombre());
            }
        } else {
            setText("Seleccionar");
        }
        return this;
    }
}