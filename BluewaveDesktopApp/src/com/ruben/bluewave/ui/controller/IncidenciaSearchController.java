package com.ruben.bluewave.ui.controller;

import javax.swing.ImageIcon;
import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.IncidenciaService;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;
import com.ruben.bluewave.ui.views.IncidenciaSearchView;

public class IncidenciaSearchController extends AbstractController {
    private IncidenciaSearchView view;
    private IncidenciaService service;

    public IncidenciaSearchController(IncidenciaSearchView view) {
        super("Buscar", new ImageIcon(IncidenciaSearchController.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
        this.view = view;
        this.service = new IncidenciaServiceImpl();
    }

    public void doAction() {
        IncidenciaCriteria criteria = view.getCriteria();
        Results<IncidenciaDTO> results = null;
        try {
            results = service.findByCriteria(criteria, 0, Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setModel(results);
    }
}