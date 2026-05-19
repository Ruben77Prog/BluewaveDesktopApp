package com.ruben.bluewave.ui.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.ruben.bluewave.model.EmpleadoDTO;

public class EmpleadoTableModel extends AbstractTableModel {
    
    public static final String[] COLUMN_NAMES = { 
        "ID", "Nombre", "Apellido1", "Apellido2", "DNI", "Teléfono", "Email", "Activo", "Rol", "Género", "Acciones" 
    };
    
    private List<EmpleadoDTO> empleados;

    public EmpleadoTableModel() {
        this.empleados = new ArrayList<>();
    }

    public EmpleadoTableModel(List<EmpleadoDTO> empleados) {
        this.empleados = empleados != null ? empleados : new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return empleados.size();
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
        return empleados.get(row);
    }

    public void setEmpleados(List<EmpleadoDTO> empleados) {
        this.empleados = empleados;
        fireTableDataChanged();
    }
    
    public EmpleadoDTO getEmpleadoAt(int row) {
        if (row >= 0 && row < empleados.size()) {
            return empleados.get(row);
        }
        return null;
    }
}