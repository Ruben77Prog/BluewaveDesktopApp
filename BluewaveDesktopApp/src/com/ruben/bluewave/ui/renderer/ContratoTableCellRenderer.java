package com.ruben.bluewave.ui.renderer;

import java.awt.Component;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import com.ruben.bluewave.model.ContratoDTO;

public class ContratoTableCellRenderer implements TableCellRenderer {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private ImageIcon editIcon;
	private ImageIcon deleteIcon;

	public ContratoTableCellRenderer() {
		editIcon = new ImageIcon(getClass().getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png"));
		deleteIcon = new ImageIcon(getClass().getResource("/nuvola/16x16/1250_delete_delete.png"));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (column == 7) {
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
			editLabel.putClientProperty("action", "edit");
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
		if (value instanceof ContratoDTO) {
			ContratoDTO ct = (ContratoDTO) value;
			String text = "";
			switch (column) {
			case 0:
				text = ct.getId() != null ? ct.getId().toString() : "";
				break;
			case 1:
				text = ct.getNumeroContrato() != null ? ct.getNumeroContrato() : "";
				break;
			case 2:
				text = ct.getClienteNombre() != null ? ct.getClienteNombre() : "";
				break;
			case 3:
				text = ct.getPlanNombre() != null ? ct.getPlanNombre() : "";
				break;
			case 4:
				text = ct.getFechaInicio() != null ? sdf.format(ct.getFechaInicio()) : "";
				break;
			case 5:
				text = ct.getFechaFin() != null ? sdf.format(ct.getFechaFin()) : "";
				break;
			case 6:
				text = ct.getEstadoContratoNombre() != null ? ct.getEstadoContratoNombre() : "";
				break;
			default:
				text = "";
			}
			c.setText(text);
		}
		return c;
	}
}