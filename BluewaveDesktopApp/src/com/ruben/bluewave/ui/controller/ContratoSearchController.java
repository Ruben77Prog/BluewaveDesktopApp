package com.ruben.bluewave.ui.controller;

import javax.swing.ImageIcon;

import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.ContratoService;
import com.ruben.bluewave.service.impl.ContratoServiceImpl;
import com.ruben.bluewave.ui.views.ContratoSearchView;

public class ContratoSearchController extends AbstractController {

	private ContratoSearchView view = null;
	private ContratoService contratoService = null;

	public ContratoSearchController(ContratoSearchView view) {
		super("Buscar", new ImageIcon(ContratoSearchController.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		this.view = view;
		this.contratoService = new ContratoServiceImpl();
	}

	@Override
	public void doAction() {
		ContratoCriteria criteria = view.getCriteria();
		Results<ContratoDTO> resultados = null;
		try {
			resultados = contratoService.findByCriteria(criteria, 0, Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.setModel(resultados);
	}
}