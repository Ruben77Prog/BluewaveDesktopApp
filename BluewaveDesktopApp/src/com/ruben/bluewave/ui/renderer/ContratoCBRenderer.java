package com.ruben.bluewave.ui.renderer;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import com.ruben.bluewave.model.ContratoDTO;

public class ContratoCBRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof ContratoDTO) {
            ContratoDTO c = (ContratoDTO) value;
            if (c.getId() == null) {
                setText("Seleccionar");
            } else {
                String num = c.getNumeroContrato() != null ? c.getNumeroContrato() : "";
                String cliente = c.getClienteNombre() != null ? " - " + c.getClienteNombre() : "";
                setText(num + cliente);
            }
        } else {
            setText("Seleccionar");
        }
        return this;
    }
}