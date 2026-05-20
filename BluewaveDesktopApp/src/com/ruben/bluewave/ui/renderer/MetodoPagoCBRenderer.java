package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.MetodoPago;

public class MetodoPagoCBRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value instanceof MetodoPago) {
			MetodoPago metodo = (MetodoPago) value;
			if (metodo.getId() == null) {
				setText("Seleccionar");
			} else {
				setText(metodo.getNombre());
			}
		} else {
			setText("Seleccionar");
		}
		return this;
	}
}