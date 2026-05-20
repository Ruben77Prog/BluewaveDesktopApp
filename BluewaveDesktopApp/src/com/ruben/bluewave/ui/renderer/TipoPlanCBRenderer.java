package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.TipoPlan;

public class TipoPlanCBRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value instanceof TipoPlan) {
			TipoPlan tipoPlan = (TipoPlan) value;
			if (tipoPlan.getId() == null) {
				setText("Seleccionar");
			} else {
				setText(tipoPlan.getNombre());
			}
		} else {
			setText("Seleccionar");
		}
		return this;
	}
}