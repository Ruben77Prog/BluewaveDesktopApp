package com.ruben.bluewave.ui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.ruben.bluewave.model.PlanDTO;

public class PlanTableCellRenderer implements TableCellRenderer {
	private ImageIcon editIcon;
	private ImageIcon deleteIcon;

	public PlanTableCellRenderer() {
		editIcon = new ImageIcon(getClass().getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png"));
		deleteIcon = new ImageIcon(getClass().getResource("/nuvola/16x16/1250_delete_delete.png"));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (column == 6) {
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

			if (value instanceof PlanDTO) {
				PlanDTO plan = (PlanDTO) value;
				if (plan.getActivo() != null && !plan.getActivo()) {
					c.setForeground(Color.RED);
				}
			}
		}

		if (value instanceof PlanDTO) {
			PlanDTO p = (PlanDTO) value;
			String text = "";
			switch (column) {
			case 0:
				text = p.getId() != null ? p.getId().toString() : "";
				break;
			case 1:
				text = p.getNombre() != null ? p.getNombre() : "";
				break;
			case 2:
				text = p.getDescripcion() != null ? p.getDescripcion() : "";
				break;
			case 3:
				text = p.getPrecio() != null ? String.valueOf(p.getPrecio()) : "";
				break;
			case 4:
				text = p.getDuracionMeses() != null ? String.valueOf(p.getDuracionMeses()) : "";
				break;
			case 5:
				text = p.getDescuento() != null ? String.valueOf(p.getDescuento()) : "";
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