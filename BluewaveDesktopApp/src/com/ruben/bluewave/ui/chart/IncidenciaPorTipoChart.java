package com.ruben.bluewave.ui.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.ruben.bluewave.util.JDBCUtils;

public class IncidenciaPorTipoChart extends JPanel {

	private static final long serialVersionUID = 1L;

	public IncidenciaPorTipoChart() {
		setLayout(new BorderLayout());
		cargarGrafica();
	}

	private void cargarGrafica() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = JDBCUtils.getConnection();
			String sql = "SELECT ti.nombre as tipo, COUNT(i.id) as total " + "FROM incidencia i "
					+ "INNER JOIN tipo_incidencia ti ON i.tipo_incidencia_id = ti.id " + "GROUP BY ti.id, ti.nombre "
					+ "ORDER BY total DESC";
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String tipo = rs.getString("tipo");
				int total = rs.getInt("total");
				dataset.addValue(total, "Incidencias", tipo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
			JDBCUtils.close(c, false);
		}

		JFreeChart chart = ChartFactory.createBarChart("Incidencias por Tipo", "Tipo de Incidencia",
				"Número de Incidencias", dataset);

		CategoryPlot plot = chart.getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(54, 125, 217));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
		add(chartPanel, BorderLayout.CENTER);
	}
}