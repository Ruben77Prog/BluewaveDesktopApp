package com.ruben.bluewave.ui.controller;

import javax.swing.ImageIcon;

import com.ruben.bluewave.dao.criteria.EmpleadoCriteria;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;
import com.ruben.bluewave.ui.views.EmpleadoSearchView;

public class EmpleadoSearchController extends AbstractController {

    private EmpleadoSearchView view = null;
    private EmpleadoService empleadoService = null;

    public EmpleadoSearchController(EmpleadoSearchView view) {
        super("Buscar", new ImageIcon(EmpleadoSearchController.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
        this.view = view;
        this.empleadoService = new EmpleadoServiceImpl();
    }

    public void doAction() {
        EmpleadoCriteria criteria = view.getCriteria();
        Results<EmpleadoDTO> resultados = null;
        try {
           
            resultados = empleadoService.findByCriteria(criteria, 0, Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setModel(resultados);
    }
}