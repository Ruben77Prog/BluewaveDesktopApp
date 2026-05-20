package com.ruben.bluewave.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.ruben.bluewave.dao.criteria.PlanCriteria;
import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.PlanService;
import com.ruben.bluewave.service.impl.PlanServiceImpl;
import com.ruben.bluewave.ui.views.PlanSearchView;

public class PlanSearchController extends AbstractController {

	private PlanSearchView view = null;
	private PlanService planService = null;

	public PlanSearchController(PlanSearchView view) {
		super("Buscar", new ImageIcon(PlanSearchController.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		this.view = view;
		this.planService = new PlanServiceImpl();
	}

	@Override
	public void doAction() {
	    PlanCriteria criteria = view.getCriteria();
	    Results<PlanDTO> resultados = null;
	    try {
	      
	        resultados = planService.findByCriteria(criteria, 0, Integer.MAX_VALUE);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    view.setModel(resultados);
	}
}