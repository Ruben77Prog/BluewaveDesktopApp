package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.service.PlanService;
import com.ruben.bluewave.service.impl.PlanServiceImpl;
import com.ruben.bluewave.ui.views.PlanSearchView;

public class PlanDeleteController extends AbstractController {

	private PlanService planService;
	private PlanSearchView searchView;
	private PlanDTO plan;

	public PlanDeleteController(PlanSearchView searchView, PlanDTO plan) {
		super(searchView);
		this.searchView = searchView;
		this.plan = plan;
		this.planService = new PlanServiceImpl();
	}

	@Override
	public void doAction() {
		int confirm = JOptionPane.showConfirmDialog(searchView,
				"¿Está seguro de eliminar el plan " + plan.getNombre() + "?", "Confirmar eliminación",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			try {
				boolean eliminado = planService.delete(plan.getId());
				if (eliminado) {
					JOptionPane.showMessageDialog(searchView, "Plan eliminado correctamente");
					searchView.buscar();
				} else {
					JOptionPane.showMessageDialog(searchView, "Error al eliminar el plan", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(searchView, "Error: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
}