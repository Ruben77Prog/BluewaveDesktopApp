package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.service.ContratoService;
import com.ruben.bluewave.service.impl.ContratoServiceImpl;
import com.ruben.bluewave.ui.views.ContratoAltaView;

public class ContratoUpdateController extends AbstractController {

	private ContratoAltaView view;
	private ContratoService contratoService;
	private ContratoDTO contratoDTO;

	public ContratoUpdateController(ContratoAltaView view, ContratoDTO contratoDTO) {
		super(view);
		this.view = view;
		this.contratoDTO = contratoDTO;
		this.contratoService = new ContratoServiceImpl();
	}

	@Override
	public void doAction() {
		ContratoDTO contrato = view.getModel();

		if (contrato == null) {
			return;
		}

		if (contratoDTO == null || contratoDTO.getId() == null) {
			JOptionPane.showMessageDialog(view, "Error: contrato sin ID", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		contrato.setId(contratoDTO.getId());

		try {
			boolean updated = contratoService.update(contrato);

			if (updated) {
				JOptionPane.showMessageDialog(view, "Contrato actualizado correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				view.setEditable(false);
				view.setAgreeController(new ContratoSetEditableController(view));
				view.limpiarCampos();
			} else {
				JOptionPane.showMessageDialog(view, "Error al actualizar el contrato", "Error",
						JOptionPane.ERROR_MESSAGE);
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