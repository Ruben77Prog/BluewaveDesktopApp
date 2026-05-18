package com.ruben.bluewave.ui.renderer;

import java.awt.Component;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import com.ruben.bluewave.model.IncidenciaDTO;

public class IncidenciaTableCellRenderer implements TableCellRenderer {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private ImageIcon editIcon;
	private ImageIcon deleteIcon;

	public IncidenciaTableCellRenderer() {
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

		if (value instanceof IncidenciaDTO) {
			IncidenciaDTO i = (IncidenciaDTO) value;
			String text = "";
			switch (column) {
			case 0:
				text = i.getId() != null ? i.getId().toString() : "";
				break;
			case 1:
				text = i.getNumeroIncidencia() != null ? i.getNumeroIncidencia() : "";
				break;
			case 2:
				text = i.getTitulo() != null ? i.getTitulo() : "";
				break;
			case 3:
				text = i.getTipoIncidenciaNombre() != null ? i.getTipoIncidenciaNombre() : "";
				break;
			case 4:
				text = i.getEstadoIncidenciaNombre() != null ? i.getEstadoIncidenciaNombre() : "";
				break;
			case 5:
				text = i.getClienteNombre() != null ? i.getClienteNombre() : "";
				break;
			case 6:
				text = i.getFechaIncidencia() != null ? sdf.format(i.getFechaIncidencia()) : "";
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