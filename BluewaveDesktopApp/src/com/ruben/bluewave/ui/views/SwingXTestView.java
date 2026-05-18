package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class SwingXTestView extends AbstractView {
	
	
	private JComboBox paisCB = null;

	public SwingXTestView() {
		initialize();
		postInitialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		
		paisCB = new JComboBox(new String[] {"Ruben","Juan", "Paula", "Toñin","Sabic", "Alan"});
		centerPanel.add(paisCB);

	}

	private void postInitialize() {
		AutoCompleteDecorator.decorate(paisCB);

	}
}
