package com.ruben.bluewave.ui.controller;

import javax.swing.ImageIcon;

import com.ruben.bluewave.dao.criteria.ClienteCriteria;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;
import com.ruben.bluewave.ui.views.ClienteSearchView;

public class ClienteSearchController extends AbstractController {

	private ClienteSearchView view = null;
	private ClienteService ClienteService = null;

	public ClienteSearchController(ClienteSearchView view) {
		super("Buscar", new ImageIcon(ClienteSearchController.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		this.view = view;
		this.ClienteService = new ClienteServiceImpl();
	}

	public void doAction() {

		ClienteCriteria criteria = view.getCriteria();
		Results<ClienteDTO> resultados = null;
		try {
			resultados = ClienteService.findByCriteria(criteria, 1, Integer.MAX_VALUE);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		view.setModel(resultados);
	}

}
