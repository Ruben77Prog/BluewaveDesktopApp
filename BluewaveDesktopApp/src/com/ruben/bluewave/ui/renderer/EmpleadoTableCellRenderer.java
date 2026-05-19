package com.ruben.bluewave.ui.renderer;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import com.ruben.bluewave.model.EmpleadoDTO;

public class EmpleadoTableCellRenderer implements TableCellRenderer {
	private ImageIcon editIcon;
	private ImageIcon deleteIcon;

	public EmpleadoTableCellRenderer() {
		editIcon = new ImageIcon(getClass().getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png"));
		deleteIcon = new ImageIcon(getClass().getResource("/nuvola/16x16/1250_delete_delete.png"));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (column == 10) {
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

		if (value instanceof EmpleadoDTO) {
			EmpleadoDTO e = (EmpleadoDTO) value;
			String text = "";
			switch (column) {
			case 0:
				text = e.getId() != null ? e.getId().toString() : "";
				break;
			case 1:
				text = e.getNombre() != null ? e.getNombre() : "";
				break;
			case 2:
				text = e.getApellido1() != null ? e.getApellido1() : "";
				break;
			case 3:
				text = e.getApellido2() != null ? e.getApellido2() : "";
				break;
			case 4:
				text = e.getDni() != null ? e.getDni() : "";
				break;
			case 5:
				text = e.getTelefono() != null ? e.getTelefono() : "";
				break;
			case 6:
				text = e.getEmail() != null ? e.getEmail() : "";
				break;
			case 7:
				text = (e.getActivo() != null && e.getActivo()) ? "Sí" : "No";
				break;
			case 8:
				text = e.getRolNombre() != null ? e.getRolNombre() : "";
				break;
			case 9:
				text = e.getGeneroNombre() != null ? e.getGeneroNombre() : "";
				break;
			default:
				text = "";
			}
			c.setText(text);
		} else {
			c.setText("");
		}
		return c;
	}
}