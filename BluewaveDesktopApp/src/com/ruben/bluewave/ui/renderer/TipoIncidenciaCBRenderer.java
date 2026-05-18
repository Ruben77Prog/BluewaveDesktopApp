package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.TipoIncidencia;

public class TipoIncidenciaCBRenderer extends DefaultListCellRenderer {

	public TipoIncidenciaCBRenderer() {

	}

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		if (value instanceof TipoIncidencia) {
			TipoIncidencia t = (TipoIncidencia) value;
			setText(t.getId() == null ? "Seleccionar" : t.getNombre());
		} else
			setText("Seleccionar");
		return this;
	}
}
