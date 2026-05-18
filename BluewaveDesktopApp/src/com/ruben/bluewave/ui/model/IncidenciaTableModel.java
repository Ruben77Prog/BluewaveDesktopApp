package com.ruben.bluewave.ui.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.ruben.bluewave.model.IncidenciaDTO;

public class IncidenciaTableModel extends AbstractTableModel {
	public static final String[] COLUMN_NAMES = { "ID", "Número", "Título", "Tipo", "Estado", "Cliente", "Fecha","Acciones" };
	private List<IncidenciaDTO> incidencias;

	public IncidenciaTableModel() {
		this.incidencias = new ArrayList<>();
	}

	public IncidenciaTableModel(List<IncidenciaDTO> incidencias) {
		this.incidencias = incidencias != null ? incidencias : new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return incidencias.size();
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
		return incidencias.get(row);
	}

	public void setIncidencias(List<IncidenciaDTO> incidencias) {
		this.incidencias = incidencias;
		fireTableDataChanged();
	}

	public IncidenciaDTO getIncidenciaAt(int row) {
	    if (row >= 0 && row < incidencias.size()) {
	        return incidencias.get(row);
	    }
	    return null;
	}
}