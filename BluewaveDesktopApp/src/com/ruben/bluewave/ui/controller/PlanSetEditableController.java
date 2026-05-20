package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.ui.views.PlanAltaView;

public class PlanSetEditableController extends AbstractController {

    private PlanDTO planDTO;

    public PlanSetEditableController(PlanAltaView view) {
        super(view);
        this.planDTO = null;
    }

    public PlanSetEditableController(PlanAltaView view, PlanDTO planDTO) {
        super(view);
        this.planDTO = planDTO;
    }

    @Override
    public void doAction() {
        PlanAltaView view = (PlanAltaView) getView();
        if (planDTO != null) {
            view.cargarPlan(planDTO);
        }
        view.setEditable(true);
        view.setAgreeController(new PlanUpdateController(view, planDTO));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}