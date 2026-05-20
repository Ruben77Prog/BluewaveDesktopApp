package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.EstadoContrato;

public class EstadoContratoCBRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value instanceof EstadoContrato) {
			EstadoContrato estado = (EstadoContrato) value;
			if (estado.getId() == null) {
				setText("Seleccionar");
			} else {
				setText(estado.getNombre());
			}
		} else {
			setText("Seleccionar");
		}
		return this;
	}
}