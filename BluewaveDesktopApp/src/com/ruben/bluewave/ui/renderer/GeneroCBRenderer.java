package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.Genero;

public class GeneroCBRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        Genero genero = (Genero) value;
        if (genero != null && genero.getId() != null) {
            setText(genero.getNombre());
        } else {
            setText("Seleccionar");
        }
        return this;
    }
}