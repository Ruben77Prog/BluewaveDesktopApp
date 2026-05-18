package com.ruben.bluewave.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ruben.bluewave.model.EmpleadoDTO;

public class EmpleadoTableModel extends AbstractTableModel {

    public static final String[] COLUMN_NAMES = new String[] { 
        "ID", "Nombre", "Apellido1", "Apellido2", "DNI", "Teléfono", "Email", "Activo", "Rol", "Género" 
    };

    private List<EmpleadoDTO> empleados;

    public EmpleadoTableModel() {
        this.empleados = new ArrayList<>();
    }

    public EmpleadoTableModel(List<EmpleadoDTO> empleados) {
        this.empleados = empleados != null ? empleados : new ArrayList<>();
        setEmpleados(empleados);
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
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return empleados.get(rowIndex);
    }

    public List<EmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDTO> empleados) {
        this.empleados = empleados;
        fireTableDataChanged();
    }
}