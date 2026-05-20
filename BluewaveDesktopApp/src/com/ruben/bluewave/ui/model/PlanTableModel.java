package com.ruben.bluewave.ui.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.ruben.bluewave.model.PlanDTO;

public class PlanTableModel extends AbstractTableModel {
	public static final String[] COLUMN_NAMES = { "ID", "Nombre", "Descripción", "Precio", "Duración", "Descuento",
			"Acciones" };
	private List<PlanDTO> planes;

	public PlanTableModel() {
		this.planes = new ArrayList<>();
	}

	public PlanTableModel(List<PlanDTO> planes) {
		this.planes = planes != null ? planes : new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return planes.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int col) {
		return COLUMN_NAMES[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return planes.get(row);
	}

	public void setPlanes(List<PlanDTO> planes) {
		this.planes = planes;
		fireTableDataChanged();
	}

	public PlanDTO getPlanAt(int row) {
		return row >= 0 && row < planes.size() ? planes.get(row) : null;
	}
}