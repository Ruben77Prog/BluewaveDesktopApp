package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.ruben.bluewave.model.PlanDTO;

public class PlanCBRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value instanceof PlanDTO) {
			PlanDTO plan = (PlanDTO) value;
			if (plan.getId() == null) {
				setText("Seleccionar");
			} else {
				setText(plan.getNombre());
			}
		} else {
			setText("Seleccionar");
		}
		return this;
	}
}