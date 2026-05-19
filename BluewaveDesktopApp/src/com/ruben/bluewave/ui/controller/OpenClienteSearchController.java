package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.ClienteSearchView;
import com.ruben.bluewave.ui.views.View;

public class OpenClienteSearchController extends AbstractAction {

	private MainWindow window = null;

	public OpenClienteSearchController() {

		super("Buscar...", new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();

	}

	private void doAction() {
		System.out.println("OpenClienteSearchController: doAction");
		ClienteSearchView view = new ClienteSearchView();
		MainWindow.getInstance().addView(view.getName(), view);

	}

}
