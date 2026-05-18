package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UsuarioSearchView extends JPanel {
	private JTextField emailTF;
	//private UsuarioServiceImpl usuarioService;

	public UsuarioSearchView() {
		initialize();
		initServices();

	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JPanel searchFormPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) searchFormPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(searchFormPanel);

		JLabel emailLabel = new JLabel("E-mail:");
		searchFormPanel.add(emailLabel);

		emailTF = new JTextField();
		searchFormPanel.add(emailTF);
		emailTF.setColumns(10);

		JButton searchButton = new JButton("Buscar:");
		searchFormPanel.add(searchButton);

		JPanel resultadosPanel = new JPanel();
		add(resultadosPanel);

		JTextArea textArea = new JTextArea();
		resultadosPanel.add(textArea);

	}

	private void initServices() {
		//usuarioService = new UsuariServiceImpl();

	}
}
