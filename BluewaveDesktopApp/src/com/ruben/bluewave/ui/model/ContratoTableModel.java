package com.ruben.bluewave.ui.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.ruben.bluewave.model.ContratoDTO;

public class ContratoTableModel extends AbstractTableModel {
	public static final String[] COLUMN_NAMES = { "ID", "Nº Contrato", "Cliente", "Plan", "Fecha Inicio", "Fecha Fin",
			"Estado", "Acciones" };
	private List<ContratoDTO> contratos;

	public ContratoTableModel() {
		this.contratos = new ArrayList<>();
	}

	public ContratoTableModel(List<ContratoDTO> contratos) {
		this.contratos = contratos != null ? contratos : new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return contratos.size();
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
		return contratos.get(row);
	}

	public void setContratos(List<ContratoDTO> contratos) {
		this.contratos = contratos;
		fireTableDataChanged();
	}

	public ContratoDTO getContratoAt(int row) {
		return row >= 0 && row < contratos.size() ? contratos.get(row) : null;
	}
}