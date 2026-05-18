package com.ruben.bluewave.ui.views;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class IncidenciaPorTecnico extends AbstractView {

	public IncidenciaPorTecnico() {
		initialize();

	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JPanel ContentPane = new JPanel();
		add(ContentPane, BorderLayout.CENTER);

		JPanel ChartPanel = new JPanel();
		ContentPane.add(ChartPanel);
		
	}

}
