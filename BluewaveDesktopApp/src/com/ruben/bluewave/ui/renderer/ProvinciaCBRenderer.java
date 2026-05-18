package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.Provincia;

public class ProvinciaCBRenderer extends DefaultListCellRenderer {

	public ProvinciaCBRenderer() {
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		Provincia provincia = (Provincia) value;
		if (provincia != null) {
			this.setText(provincia.getNombre());
		} else {
			this.setText("Seleccionar");
		}
		return this;
	}
}