package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.service.ContratoService;
import com.ruben.bluewave.service.impl.ContratoServiceImpl;
import com.ruben.bluewave.ui.views.ContratoSearchView;

public class ContratoDeleteController extends AbstractController {

	private ContratoService contratoService;
	private ContratoSearchView searchView;
	private ContratoDTO contrato;

	public ContratoDeleteController(ContratoSearchView searchView, ContratoDTO contrato) {
		super(searchView);
		this.searchView = searchView;
		this.contrato = contrato;
		this.contratoService = new ContratoServiceImpl();
	}

	@Override
	public void doAction() {
		int confirm = JOptionPane.showConfirmDialog(searchView,
				"¿Está seguro de eliminar el contrato " + contrato.getNumeroContrato() + "?", "Confirmar eliminación",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			try {
				contratoService.delete(contrato.getId());
				JOptionPane.showMessageDialog(searchView, "Contrato eliminado correctamente");
				searchView.buscar();
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