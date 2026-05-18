package com.ruben.bluewave.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ruben.bluewave.model.ClienteDTO;

public class ClienteTableModel extends AbstractTableModel {

	public static final String[] COLUMN_NAMES = new String[] { "ID", "Nombre", "Apellido1", "Apellido2", "DNI",
			"Teléfono", "Email", "Fecha Nac.", "Estado", "Acciones"  };

	private List<ClienteDTO> clientes;

	public ClienteTableModel() {
		this.clientes = new ArrayList<>();
	}

	public ClienteTableModel(List<ClienteDTO> clientes) {
		this.clientes = clientes != null ? clientes : new ArrayList<>();
		setClientes(clientes);
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return clientes.get(rowIndex);
	}

	public ClienteDTO getClienteAt(int row) {
	    return clientes.get(row);
	}

	public void setClientes(List<ClienteDTO> clientes) {
		this.clientes = clientes;
		fireTableDataChanged();

	}
}