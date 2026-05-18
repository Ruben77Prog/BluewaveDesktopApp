package com.ruben.bluewave.ui.renderer;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.ruben.bluewave.model.ClienteDTO;

public class ClienteTableCellRenderer implements TableCellRenderer {

    private ImageIcon editIcon;
    private ImageIcon deleteIcon;

    public ClienteTableCellRenderer() {
        editIcon = new ImageIcon(getClass().getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png"));
        deleteIcon = new ImageIcon(getClass().getResource("/nuvola/16x16/1250_delete_delete.png"));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

       
        if (column == 9) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            panel.setOpaque(true);
            
            JLabel editLabel = new JLabel(editIcon);
            JLabel deleteLabel = new JLabel(deleteIcon);
            
            panel.add(editLabel);
            panel.add(deleteLabel);
            
            if (isSelected) {
                panel.setBackground(table.getSelectionBackground());
            } else {
                panel.setBackground(table.getBackground());
            }
            
            
            editLabel.putClientProperty("row", row);
            editLabel.putClientProperty("action", "edit");
            deleteLabel.putClientProperty("row", row);
            deleteLabel.putClientProperty("action", "delete");
            
            return panel;
        }

       
        JLabel c = new JLabel();
        c.setOpaque(true);

        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        } else {
            c.setBackground(table.getBackground());
            c.setForeground(table.getForeground());
        }

        if (value != null && value instanceof ClienteDTO) {
            ClienteDTO cliente = (ClienteDTO) value;
            String text = "";
            switch (column) {
                case 0: text = cliente.getId() != null ? cliente.getId().toString() : ""; break;
                case 1: text = cliente.getNombre() != null ? cliente.getNombre() : ""; break;
                case 2: text = cliente.getApellido1() != null ? cliente.getApellido1() : ""; break;
                case 3: text = cliente.getApellido2() != null ? cliente.getApellido2() : ""; break;
                case 4: text = cliente.getDni() != null ? cliente.getDni() : ""; break;
                case 5: text = cliente.getTelefono() != null ? cliente.getTelefono() : ""; break;
                case 6: text = cliente.getEmail() != null ? cliente.getEmail() : ""; break;
                case 7: text = cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString() : ""; break;
                case 8: text = cliente.getEstadoClienteNombre() != null ? cliente.getEstadoClienteNombre() : ""; break;
                default: text = "";
            }
            c.setText(text);
        } else {
            c.setText("");
        }
        return c;
    }
}