package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.AbstractAction;

import com.ruben.bluewave.dao.criteria.ClienteCriteria;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;
import com.ruben.bluewave.ui.views.ClienteSearchView;

public class ClienteSearchAction extends AbstractAction implements KeyListener {

	private ClienteSearchView view = null;
	private ClienteService service = null;

	public ClienteSearchAction(ClienteSearchView view) {
		this.view = view;
		this.service = new ClienteServiceImpl();
	}

	public void doAction() {

		ClienteCriteria criteria = view.getCriteria();
		Results<ClienteDTO> resultados = null;
		try {
			resultados = service.findByCriteria(criteria, 0, Integer.MAX_VALUE);
		} catch (Exception e) {

			e.printStackTrace();
		}
		view.setModel(resultados);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();

	}

	@Override
	public void keyTyped(KeyEvent e) {

		ClienteCriteria criteria = view.getCriteria();
		String nombre = criteria.getNombre();
		if (nombre != null && nombre.length() >= 3) {
			doAction();

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		doAction();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
