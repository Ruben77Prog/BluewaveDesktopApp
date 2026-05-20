package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.ClienteDTO;

public class ClienteCBRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value instanceof ClienteDTO) {
			ClienteDTO cliente = (ClienteDTO) value;
			if (cliente.getId() == null) {
				setText("Seleccionar");
			} else {
				String nombre = cliente.getNombre() != null ? cliente.getNombre() : "";
				String apellido = cliente.getApellido1() != null ? cliente.getApellido1() : "";
				setText(nombre + " " + apellido);
			}
		} else {
			setText("Seleccionar");
		}
		return this;
	}
}