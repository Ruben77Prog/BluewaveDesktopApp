package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.service.PlanService;
import com.ruben.bluewave.service.impl.PlanServiceImpl;
import com.ruben.bluewave.ui.views.PlanAltaView;

public class PlanUpdateController extends AbstractController {

	private PlanAltaView view;
	private PlanService planService;
	private PlanDTO planDTO;

	public PlanUpdateController(PlanAltaView view, PlanDTO planDTO) {
		super(view);
		this.view = view;
		this.planDTO = planDTO;
		this.planService = new PlanServiceImpl();
	}

	@Override
	public void doAction() {
		PlanDTO plan = view.getModel();

		if (plan == null) {
			return;
		}

		if (planDTO == null || planDTO.getId() == null) {
			JOptionPane.showMessageDialog(view, "Error: plan sin ID", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		plan.setId(planDTO.getId());

		try {
			boolean updated = planService.update(plan);

			if (updated) {
				JOptionPane.showMessageDialog(view, "Plan actualizado correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				view.setEditable(false);
				view.setAgreeController(new PlanSetEditableController(view));
				view.limpiarCampos();
			} else {
				JOptionPane.showMessageDialog(view, "Error al actualizar el plan", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Error en base de datos: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
}