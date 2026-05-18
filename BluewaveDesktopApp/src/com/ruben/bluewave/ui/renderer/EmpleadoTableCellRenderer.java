package com.ruben.bluewave.ui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.ruben.bluewave.model.EmpleadoDTO;

public class EmpleadoTableCellRenderer implements TableCellRenderer {

    public EmpleadoTableCellRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        JLabel c = new JLabel();

        if (isSelected) {
            c.setOpaque(true);
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        } else {
            c.setOpaque(true);
            c.setBackground(table.getBackground());
            c.setForeground(table.getForeground());
        }

        if (value != null && value instanceof EmpleadoDTO) {
            try {
                EmpleadoDTO empleado = (EmpleadoDTO) value;
                String text = "";

                switch (column) {
                    case 0:
                        text = empleado.getId() != null ? empleado.getId().toString() : "";
                        break;
                    case 1:
                        text = empleado.getNombre() != null ? empleado.getNombre() : "";
                        break;
                    case 2:
                        text = empleado.getApellido1() != null ? empleado.getApellido1() : "";
                        break;
                    case 3:
                        text = empleado.getApellido2() != null ? empleado.getApellido2() : "";
                        break;
                    case 4:
                        text = empleado.getDni() != null ? empleado.getDni() : "";
                        break;
                    case 5:
                        text = empleado.getTelefono() != null ? empleado.getTelefono() : "";
                        break;
                    case 6:
                        text = empleado.getEmail() != null ? empleado.getEmail() : "";
                        break;
                    case 7:
                       
                        Boolean activo = empleado.getActivo();
                        text = activo != null ? (activo ? "Sí" : "No") : "";
                        break;
                    case 8:
                        text = empleado.getRolNombre() != null ? empleado.getRolNombre() : "";
                        break;
                    case 9:
                        text = empleado.getGeneroNombre() != null ? empleado.getGeneroNombre() : "";
                        break;
                    default:
                        text = "";
                }
                c.setText(text);
            } catch (Exception ex) {
                System.out.println("ERROR en renderer Empleado: " + ex.getMessage());
                ex.printStackTrace();
                c.setText("Error");
            }
        } else {
            c.setText("");
        }
        return c;
    }
}